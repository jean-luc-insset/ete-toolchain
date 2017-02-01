package fr.insset.jeanluc.gel.impl;


import fr.insset.jeanluc.gel.api.*;
import java.util.List;



public class LambdaImpl  extends BinaryOperationImpl  implements Lambda {


    public LambdaImpl() {
    }


    //========================================================================//


    public String getSymbol() {
        return "|";
    }



    //========================================================================//
    //                A C C E S S O R S   and   M U T A T O R S               //
    //========================================================================//



    
    public List<VariableDeclaration> getVariables() {
        return variables;
    }

    public void setVariables(List<VariableDeclaration> inValue) {
        variables = inValue;
    }

        public void addVariables(VariableDeclaration inValue) {
        variables.add(inValue);
    }
    
        
    public GelExpression getBody() {
        return body;
    }

    public void setBody(GelExpression inValue) {
        body = inValue;
    }

    
    

    //========================================================================//
    //                           O P E R A T I O N S                          //
    //========================================================================//




    //========================================================================//
    //                   I N S T A N C E   V A R I A B L E S                  //
    //========================================================================//


    private List<VariableDeclaration> variables;
    private GelExpression body;


}


