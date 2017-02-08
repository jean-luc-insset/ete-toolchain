package fr.insset.jeanluc.ete.as.impl;


import fr.insset.jeanluc.ete.as.api.*;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public class ForLoopImpl  extends LoopImpl  implements ForLoop {






    //========================================================================//


    public List<Statement> getInitialization() {
        return initialization;
    }

    public void setInitialization(List<Statement> inValue) {
        initialization = inValue;
    }

    public void addInitialization(Statement inValue) {
        initialization.add(inValue);
    }

    public void removeInitialization(Statement inValue) {
        initialization.remove(inValue);
    }
    public List<Statement> getIncrementation() {
        return incrementation;
    }

    public void setIncrementation(List<Statement> inValue) {
        incrementation = inValue;
    }

    public void addIncrementation(Statement inValue) {
        incrementation.add(inValue);
    }

    public void removeIncrementation(Statement inValue) {
        incrementation.remove(inValue);
    }


    //========================================================================//




    //========================================================================//


    private List<Statement>  initialization;
    private List<Statement>  incrementation;


}