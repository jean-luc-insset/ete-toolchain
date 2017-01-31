package fr.insset.jeanluc.gel.api;



public interface LambdaExpression  extends BinaryOperation  {




    public default String getSymbol() {
        return "|";
    }




    //========================================================================//


            public GelExpression getbody();
    public void setbody(GelExpression inValue);
    


}


