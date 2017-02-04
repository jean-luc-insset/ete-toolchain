package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface Lambda  extends BinaryOperation  {




    public default String getSymbol() {
        return "|";
    }




    //========================================================================//


    public List<VariableDeclaration> getVariables();
    public void setVariables(List<VariableDeclaration> inValue);
    public void addVariables(VariableDeclaration inValue);
    public GelExpression getBody();
    public void setBody(GelExpression inValue);


}