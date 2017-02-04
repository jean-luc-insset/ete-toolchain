package fr.insset.jeanluc.gel.impl;



import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.api.Literal;
import fr.insset.jeanluc.gel.api.Operation;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;



public class GelEvaluator extends DynamicVisitorSupport {

    public GelEvaluator() {
        register(Operation.class, "visitOperation");
        register(Literal.class, "visitLiteral");
    }


    public Object visitOperation(Operation inExpression, MofPackage inContext) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 1- Collect the values of the operands
        System.out.println("Visiting " + inExpression);
        List<Object>    operandValues = new LinkedList<>();
        for (GelExpression operand : inExpression.getOperand()) {
            Object operandValue = genericVisit(operand, inContext);
            operandValues.add(operandValue);
        }
        // 2- Build an UELExpression from these values
        StringBuilder   builder = new StringBuilder();
        switch (operandValues.size()) {
            case 1:
                break;
            case 2:                
                break;
        }
        // 3- Evaluate the UELExpression and return the value
        return null;
    }

    public Object visitLiteral(Literal inLiteral, MofPackage inContexte) {
        System.out.println("Visiting " + inLiteral);
        return null;
    }

}


