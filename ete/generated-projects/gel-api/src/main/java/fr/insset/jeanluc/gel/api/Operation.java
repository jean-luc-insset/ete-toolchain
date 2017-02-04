package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface Operation  extends GelExpression  {








    //========================================================================//


    public List<GelExpression> getOperand();
    public void setOperand(List<GelExpression> inValue);
    public void addOperand(GelExpression inValue);



}


