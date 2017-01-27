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
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.el.BeanNameResolver;
import javax.el.ELContext;
import javax.el.ELManager;
import javax.el.ELProcessor;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.StandardELContext;
import javax.el.ValueExpression;
import javax.el.VariableMapper;


/**
 *
 * @author jldeleage
 */
public class ELEvaluator {



    public ELEvaluator() {
    }


    public ELEvaluator(MofPackage model) {
        this.model = model;
        initBeanResolver();
    }


    public ELEvaluator(MofPackage inModel, Map<String, Object> inParameters) {
        parameters  = inParameters;
        model       = inModel;
        initBeanResolver();
        for (Map.Entry<String, Object> entry : inParameters.entrySet()) {
//            processor.setValue(entry.getKey(), entry.getValue());
            addParameter(entry.getKey(), entry.getValue());
        }

    }


    private void initBeanResolver() {
        StandardELContext elContext = manager.getELContext();
        elContext.addELResolver(new ELResolver() {
            @Override
            public Object getValue(ELContext elc, Object o, Object o1) {
                if (o == null) {
                    String propertyName = o1.toString();
                    String postfix = propertyName.substring(0,1).toUpperCase() + propertyName.substring(1);
                    Object result = getValue("get" + postfix);
                    if (result != null) {
                        elc.setPropertyResolved(true);
                        return result;
                    }
                    result = getValue("is" + postfix);
                    if (result != null) {
                        elc.setPropertyResolved(true);
                    }
                    return result;
                }
                
                return null;
            }

            private Object getValue(String inAccessorName) {
                try {
                    Method method = model.getClass().getMethod(inAccessorName, new Class[0]);
                    Object result = method.invoke(model, new Object[0]);
                    return result;
                } catch (NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException ex) {
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
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Class<?> getCommonPropertyType(ELContext elc, Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });    }


    protected Object    applyAccessor(Object inObj, Class inClass, String inAccessorName) {
        try {
            Method method = inClass.getMethod(inAccessorName, new Class[0]);
            return method.invoke(inObj, new Object[0]);
        } catch (NoSuchMethodException | SecurityException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
            return null;
        }
    }

    public Object  evaluate(String inExpression, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
        return evaluate(inExpression);
    }


    public Object   evaluate(String inExpression) {
        Pattern         pattern     = Pattern.compile("[#$]\\{[^\\}]*\\}");
        Matcher         matcher     = pattern.matcher(inExpression);
        Object          result      = null;
        StringBuilder   builder     = new StringBuilder();
        int             previousEnd = 0;
        int             end         = inExpression.length();

        while (matcher.find()) {
            String group = matcher.group();
            result = processor.eval(group.substring(2,group.length()-1));
            int     start   = matcher.start();
            end     = matcher.end();
            if (start == 0 && end == inExpression.length()) {
                return result;
            }
            builder.append(inExpression.substring(previousEnd, start));
//            System.out.println("I found the text " + group
//                    + " in the text " + inExpression
//                    + " starting at index " + matcher.start()
//                    + " and ending at index " + matcher.end());
            builder.append(result.toString());
            previousEnd = end;
        }
        builder.append(inExpression.substring(previousEnd, inExpression.length()));
        
        return builder.toString();
    }       // evaluate


    public void addParameter(String inName, Object inValue) {
        try {
            processor.setValue(inName, inValue);
        }
        catch (Exception ex) {
            Logger.getGlobal().info("Unable to set parameter " + inName + " to " + inValue);
        }
    }


    public void addBeanNameResolver(BeanNameResolver inResolver) {
        manager.addBeanNameResolver(inResolver);
    }

//    public void defineBean(String inName, Object inValue) {
//        
//    }


    ELProcessor         processor   = new ELProcessor();
    ELManager           manager     = processor.getELManager();
    Map<String, Object> parameters;
    MofPackage          model;

}

