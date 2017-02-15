package fr.insset.jeanluc.action.semantics.dialect;

import fr.insset.jeanluc.ete.as.api.Assignment;
import fr.insset.jeanluc.ete.as.api.Conditional;
import fr.insset.jeanluc.ete.as.api.Instanciation;
import fr.insset.jeanluc.ete.as.api.Loop;
import fr.insset.jeanluc.ete.as.api.VariableDeclaration;
import fr.insset.jeanluc.ete.as.api.WhileDoLoop;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import fr.insset.jeanluc.el.dialect.JavaDialect;

/**
 * This Java Dialect is able to translate GEL abstract trees to different
 * flavours of Java (JPA selecting code, checking code)
 *
 * @author jldeleage
 */
public class JavaASGenerator extends DynamicVisitorSupport implements JavaDialect {

    public  final   static  String      INDENTATION = "    ";


    public JavaASGenerator() {
        this.register("visit", "fr.insset.jeanluc.ete.as.api");
    }


    public Object visitAssignment(Assignment inAssignment, Object... inParameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PrintWriter     output = (PrintWriter) inParameters[0];
        String          indentation = (String) inParameters[1];
        output.print(indentation);
        output.print(inAssignment.getVariable().getIdentifier());
        output.print(" = ");
        genericVisit(inAssignment.getValue(), inParameters);
        output.print(";\n");
        return inAssignment;
    }       // visitAssignement


    public Object visitConditional(Conditional inConditional, Object... inParameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PrintWriter     output = (PrintWriter) inParameters[0];
        String          indentation = (String) inParameters[1];
        output.print(indentation);
        output.print("if (");
        output.print(") {");
        genericVisit(inConditional.getThenPart(), output, indentation + INDENTATION); 
        output.print(indentation);
        output.print("}");
        return inConditional;
    }


    public Object visitInstanciation(Instanciation inInstanciation, Object... inParameters) {
        PrintWriter     output = (PrintWriter) inParameters[0];
        output.print("new ");
        output.print(inInstanciation.getMofClass().getName());
        output.print("()");
        return inInstanciation;
    }


    public Object visitFor(Loop inLoop, Object... inParameters) {
        return inLoop;
    }


    public Object visitWhileDoLoop(WhileDoLoop inWhileDo, Object... inParameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PrintWriter     output = (PrintWriter) inParameters[0];
        String          indentation = (String) inParameters[1];
        output.print(indentation);
        output.print("if (");
        genericVisit(inWhileDo.getCondition());
        output.print(") {");
        genericVisit(inWhileDo.getBody(), output, indentation + INDENTATION);
        output.print(indentation);
        output.print("}\n");
        return inWhileDo;
    }


    public Object visitVariableDeclaration(VariableDeclaration inVariableDeclaration, Object... inParameters) {
        PrintWriter     output = (PrintWriter) inParameters[0];
        String          indentation = (String) inParameters[1];
        output.print(indentation);
        output.print(inVariableDeclaration.getType().getName());
        output.print("\t");
        output.print(inVariableDeclaration.getIdentifier());
        output.print(";\n");
        return inVariableDeclaration;
    }




//    public String toString() {
//        return "Java Generator";
//    }



    //========================================================================//



}       // JavaASGenerator

