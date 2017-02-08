package fr.insset.jeanluc.ete.as.api;


import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public interface Loop  extends Statement  {





    //========================================================================//


    public List<Statement> getBody();
    public void setBody(List<Statement> inValue);
    public void addBody(Statement inValue);
    public void removeBody(Statement inValue);
    public GelExpression getCondition();
    public void setCondition(GelExpression inValue);


    //========================================================================//





}