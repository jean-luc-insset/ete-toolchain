package fr.insset.jeanluc.ete.meta.model.core.impl;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author jldeleage
 */
public abstract class NamedElementImpl implements NamedElement {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String inName) {
        name = inName;
    }


    private  String      name;


}
