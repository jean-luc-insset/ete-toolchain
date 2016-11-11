/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.el.evaluator;

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
        return (String)evaluate(inExpression, String.class);
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

        @Override
        public Object getValue(ELContext elc, Object o, Object o1) {
            if (o == null) {
                String variableName = (String) o1;
                return parameters.get(variableName);
            }
            else {
                String  propertyName = o1.toString();
                String  methodName = "get" + propertyName.substring(0, 1).toUpperCase()
                                    + propertyName.substring(1);
                try {
                    Method method = o.getClass().getMethod(methodName, new Class[0]);
                    Object invoke = method.invoke(o, new Object[0]);
                    elc.setPropertyResolved(true);
                    return invoke;
                } catch (NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException ex) {
                    Logger.getLogger(ELEvaluator.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (o instanceof Map) {
                    Object get = ((Map)o).get(o1);
                    return get;
                }
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




    private class EteFunctionMapper extends FunctionMapper {

        @Override
        public Method resolveFunction(String string, String string1) {
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
