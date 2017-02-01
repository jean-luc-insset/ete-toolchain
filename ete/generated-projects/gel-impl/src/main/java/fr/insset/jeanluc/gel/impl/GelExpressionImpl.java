package fr.insset.jeanluc.gel.impl;


import fr.insset.jeanluc.gel.api.*;
import java.util.List;



public class GelExpressionImpl  implements GelExpression {


    public GelExpressionImpl() {
    }


    //========================================================================//





    //========================================================================//
    //                A C C E S S O R S   and   M U T A T O R S               //
    //========================================================================//



    
    public List<GelExpression> getOperand() {
        return operand;
    }

    public void setOperand(List<GelExpression> inValue) {
        operand = inValue;
    }

        public void addOperand(GelExpression inValue) {
        operand.add(inValue);
    }
    
    

    //========================================================================//
    //                           O P E R A T I O N S                          //
    //========================================================================//




    //========================================================================//
    //                   I N S T A N C E   V A R I A B L E S                  //
    //========================================================================//


    private List<GelExpression> operand;


}


