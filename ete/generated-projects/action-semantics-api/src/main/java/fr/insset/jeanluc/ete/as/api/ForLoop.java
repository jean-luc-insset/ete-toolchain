package fr.insset.jeanluc.ete.as.api;


import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public interface ForLoop  extends Loop  {





    //========================================================================//


    public List<Statement> getInitialization();
    public void setInitialization(List<Statement> inValue);
    public void addInitialization(Statement inValue);
    public void removeInitialization(Statement inValue);
    public List<Statement> getIncrementation();
    public void setIncrementation(List<Statement> inValue);
    public void addIncrementation(Statement inValue);
    public void removeIncrementation(Statement inValue);


    //========================================================================//





}