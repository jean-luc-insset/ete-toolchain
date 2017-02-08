package fr.insset.jeanluc.ete.as.impl;


import fr.insset.jeanluc.ete.as.api.*;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public class LoopImpl  extends StatementImpl  implements Loop {






    //========================================================================//


    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> inValue) {
        body = inValue;
    }

    public void addBody(Statement inValue) {
        body.add(inValue);
    }

    public void removeBody(Statement inValue) {
        body.remove(inValue);
    }
    public GelExpression getCondition() {
        return condition;
    }

    public void setCondition(GelExpression inValue) {
        condition = inValue;
    }



    //========================================================================//




    //========================================================================//


    private List<Statement>  body;
    private GelExpression  condition;


}