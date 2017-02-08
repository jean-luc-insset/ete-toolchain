package fr.insset.jeanluc.ete.as.impl;


import fr.insset.jeanluc.ete.as.api.*;
import fr.insset.jeanluc.gel.api.GelExpression;


public class AssignmentImpl  extends StatementImpl  implements Assignment {






    //========================================================================//


    public GelExpression getValue() {
        return value;
    }

    public void setValue(GelExpression inValue) {
        value = inValue;
    }

    public VariableDeclaration getVariable() {
        return variable;
    }

    public void setVariable(VariableDeclaration inValue) {
        variable = inValue;
    }



    //========================================================================//




    //========================================================================//


    private GelExpression  value;
    private VariableDeclaration  variable;


}