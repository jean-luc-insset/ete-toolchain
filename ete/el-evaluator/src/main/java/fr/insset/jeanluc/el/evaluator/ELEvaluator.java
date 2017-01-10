package fr.insset.jeanluc.el.evaluator;



import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.beans.FeatureDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static java.util.Collections.EMPTY_LIST;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

/**
 *
 * @author jldeleage
 */
public class ELEvaluator {


    public ELEvaluator(MofPackage inModel) {
        this(inModel, new HashMap<>());
    }


    public ELEvaluator(MofPackage inModel, Map<String, Object> inParameters) {
        model = inModel;
        parameters = inParameters;

        functionMapper = new EteFunctionMapper();
        variableMapper = new EteVariableMapper();

        compositeELResolver = new CompositeELResolver();
        compositeELResolver.add(new ArrayELResolver());
        compositeELResolver.add(new ListELResolver());
        compositeELResolver.add(new BeanELResolver());
        compositeELResolver.add(new MapELResolver());
        resolver = new EteELResolver();
        compositeELResolver.add(resolver);

        context = new EteELContext();

    }


    //========================================================================//
    //                  C O N V E N I E N C E   M E T H O D S                 //
    //========================================================================//


    public Boolean evaluateBoolean(String inExpression) {
        return (Boolean)evaluate(inExpression, Boolean.class);
    }


    public Integer evaluateInt(String inExpression) {
        return (Integer)evaluate(inExpression, Integer.class);
    }


    public Double evaluateDouble(String inExpression) {
        return (Double)evaluate(inExpression, Double.class);
    }


    public String evaluateString(String inExpression) {
        String name = (String)evaluate(inExpression, String.class);
        return name;
    }



    public List evaluateList(String inExpression) {
        return (List)evaluate(inExpression, List.class);
    }


    public <T> T evaluate(String inExpression, Class<T> inType) {
        ExpressionFactory factory = ExpressionFactory.newInstance();
        ValueExpression valueExpression = factory.createValueExpression(context, inExpression, inType);
        Object value = valueExpression.getValue(context);
        return (T)value;
    }


    //========================================================================//
    //                            E L C O N T E X T                           //
    //========================================================================//


    private class EteELContext extends ELContext {

        @Override
        public ELResolver getELResolver() {
            return resolver;
        }

        @Override
        public FunctionMapper getFunctionMapper() {
            return functionMapper;
        }

        @Override
        public VariableMapper getVariableMapper() {
            return variableMapper;
        }
        
    }       // EteElContext


    //========================================================================//
    //                             R E S O L V E R                            //
    //========================================================================//


    private class EteELResolver extends ELResolver {

        /**
         * TODO : Should we give precedence to map values rather than
         * properties&nbsp;?
         * 
         * @param elc
         * @param o
         * @param o1 : property name or key for the map
         * @return  the value of o.o1 or o[o1]
         */
        @Override
        public Object getValue(ELContext elc, Object o, Object o1) {
            if (o == null) {
                String variableName = (String) o1;
                return parameters.get(variableName);
            }
            else {
                try {
                    String  propertyName = o1.toString();

                    String  methodName = propertyName.substring(0, 1).toUpperCase()
                            + propertyName.substring(1);
                    Method  method = null;
                    try {
                        method = o.getClass().getMethod("get" + methodName, new Class[0]);
                    }
                    catch (NoSuchMethodException ex) {
                        try {
                            method = o.getClass().getMethod("is" + methodName, new Class[0]);
                        }
                        catch (NoSuchMethodException ex2) {
                            // The is no accessor, whether with "get" or
                            // "is" prefix. Maybe the base object is a
                            // map...
                        }
                    }
                    if (method != null) {
                        Object invoke = method.invoke(o, new Object[0]);
                        elc.setPropertyResolved(true);
                        return invoke;
                    }
                    if (o instanceof Map) {
                        Object get = ((Map)o).get(o1);
                        return get;
                    }
                }
                catch (IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException ex) {
                    Logger.getLogger(ELEvaluator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
        }


        /**
         * Calls a compatible method if any exists.
         * 
         * WARNING : ambiquities are not detected
         * 
         * @param context : EL context to resolve any variables if needed
         * @param base : object to use as "this"
         * @param methodName : name of the method to call
         * @param paramTypes : types of parameters
         * @param params
         * @return 
         */
        @Override
        public Object invoke(ELContext context, Object base, Object methodName, Class<?>[] paramTypes, Object[] params) {
            try {
                Class<?> baseClass = base.getClass();
                paramTypes = new Class[params.length];
                for (int i=0 ; i<params.length ; i++) {
                    paramTypes[i] = params[i].getClass();
                }
                Method method = null;
                try {
                    method = baseClass.getMethod((String)methodName, paramTypes);
                } catch (NoSuchMethodException ex) {
                    // this exception is not harmful. The exact method does
                    // not exist but maybe there is one the actual parameters
                    // can be promoted for.
                }
                if (method == null) {
                    Method[] methods = baseClass.getMethods();
                    methodLoop : for (int i=0 ; i<methods.length ; i++) {
                        if (methodName.equals(methods[i].getName())) {
                            // Check parameters
                            method = methods[i];
                            int parameterCount = method.getParameterCount();
                            if (parameterCount != params.length) {
                                method = null;
                                continue;
                            }
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            for (int j=0 ; j<parameterCount ; j++) {
                                if (! parameterTypes[j].isAssignableFrom(params[j].getClass())) {
                                    method = null;
                                    continue methodLoop;
                                }
                            }
                            break methodLoop;
                        }       // method name OK
                    }       // method loop
                }       // method == null
                Object result = method.invoke(base, params);
                return result;
            } catch (SecurityException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                Logger.getLogger(ELEvaluator.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

        @Override
        public Class<?> getType(ELContext elc, Object o, Object o1) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setValue(ELContext elc, Object o, Object o1, Object o2) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isReadOnly(ELContext elc, Object o, Object o1) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext elc, Object o) {
            return EMPTY_LIST.iterator();
        }

        @Override
        public Class<?> getCommonPropertyType(ELContext elc, Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }       // EteELResolver


    //========================================================================//
    //                              M A P P E R S                             //
    //========================================================================//


    private class EteVariableMapper extends VariableMapper {

        @Override
        public ValueExpression resolveVariable(String inString) {
            Object parameter = parameters.get(inString);
            if (parameter != null) {
                ValueExpression valueExpression = factory.createValueExpression(parameter, parameter.getClass());
                return valueExpression;
            }
            // Variable not found. Maybe "string" is a property of the
            // model
            String  accessorName = "get"
                    + inString.substring(0,1).toUpperCase()
                    + inString.substring(1);
            try {
                Method method = model.getClass().getMethod(accessorName, new Class[0]);
                Object invoke = method.invoke(model, new Object[0]);
                ValueExpression valueExpression = factory.createValueExpression(invoke, invoke.getClass());
                return valueExpression;
            } catch (NoSuchMethodException | SecurityException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                Logger.getLogger(ELEvaluator.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }       // resolveVariable

        @Override
        public ValueExpression setVariable(String string, ValueExpression ve) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }       // EteVariableMapper




    /**
     * This class has not been implemented.<br>
     * TODO : create a dependance injection mechanism to allow new custom
     * functions
     */
    private class EteFunctionMapper extends FunctionMapper {

        @Override
        public Method resolveFunction(String string, String string1) {
            System.out.println("Resolution de la fonction : " + string + " - " + string1);
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }       // EteFunctionMapper



    //========================================================================//


    private     MofPackage          model;
    private     EteFunctionMapper   functionMapper;
    private     EteVariableMapper   variableMapper;
    private     EteELResolver       resolver;
    private     EteELContext        context;
    private     CompositeELResolver compositeELResolver;
    private     Map<String, Object> parameters;
    private     ExpressionFactory   factory = ExpressionFactory.newInstance();


}       // ELEvaluator
