package fr.insset.jeanluc.ete.as.impl;


import fr.insset.jeanluc.ete.as.api.*;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public class ConditionalImpl  extends StatementImpl  implements Conditional {






    //========================================================================//


    public GelExpression getCondition() {
        return condition;
    }

    public void setCondition(GelExpression inValue) {
        condition = inValue;
    }

    public Statement getThenPart() {
        return thenPart;
    }

    public void setThenPart(Statement inValue) {
        thenPart = inValue;
    }

    public Statement getElsePart() {
        return elsePart;
    }

    public void setElsePart(Statement inValue) {
        elsePart = inValue;
    }



    //========================================================================//




    //========================================================================//


    private GelExpression  condition;
    private Statement  thenPart;
    private Statement  elsePart;


}