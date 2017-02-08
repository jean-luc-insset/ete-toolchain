package fr.insset.jeanluc.ete.as.api;


import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public interface Conditional  extends Statement  {





    //========================================================================//


    public GelExpression getCondition();
    public void setCondition(GelExpression inValue);
    public Statement getThenPart();
    public void setThenPart(Statement inValue);
    public Statement getElsePart();
    public void setElsePart(Statement inValue);


    //========================================================================//





}