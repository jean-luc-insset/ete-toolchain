package fr.insset.jeanluc.ete.as.impl;


import fr.insset.jeanluc.ete.as.api.*;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public class VariableDeclarationImpl  extends StatementImpl  implements VariableDeclaration {






    //========================================================================//


    public MofType getType() {
        return type;
    }

    public void setType(MofType inValue) {
        type = inValue;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String inValue) {
        identifier = inValue;
    }



    //========================================================================//




    //========================================================================//


    private MofType  type;
    private String  identifier;


}