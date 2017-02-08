package fr.insset.jeanluc.ete.as.api;


import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public interface VariableDeclaration  extends Statement  {





    //========================================================================//


    public MofType getType();
    public void setType(MofType inValue);
    public String getIdentifier();
    public void setIdentifier(String inValue);


    //========================================================================//





}