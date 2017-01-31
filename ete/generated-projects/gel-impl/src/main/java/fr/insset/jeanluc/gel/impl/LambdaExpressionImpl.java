package fr.insset.jeanluc.gel.impl;


import fr.insset.jeanluc.gel.api.*;

public class LambdaExpressionImpl  extends BinaryOperationImpl  implements LambdaExpression {

    public LambdaExpressionImpl() {
    }


    //========================================================================//

    public String getSymbol() {
        return "|";
    }



    //========================================================================//


            public GelExpression getbody() {
        return body;
    }

    public void setbody(GelExpression inValue) {
        body = inValue;
    }
    

    //========================================================================//




    //========================================================================//


            private GelExpression body;
    

}


