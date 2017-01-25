/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.el.evaluator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.el.BeanNameResolver;
import javax.el.ELContext;
import javax.el.ELManager;
import javax.el.ELProcessor;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.ValueExpression;
import javax.el.VariableMapper;


/**
 *
 * @author jldeleage
 */
public class SimpleEvaluator {

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
        processor.setValue(inName, inValue);
    }


    public void addBeanNameResolver(BeanNameResolver inResolver) {
        manager.addBeanNameResolver(inResolver);
    }

//    public void defineBean(String inName, Object inValue) {
//        
//    }


    ELProcessor     processor   = new ELProcessor();
    ELManager       manager     = processor.getELManager();

}

