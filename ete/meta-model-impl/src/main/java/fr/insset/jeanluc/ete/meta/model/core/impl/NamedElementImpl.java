package fr.insset.jeanluc.ete.meta.model.core.impl;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;

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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String inId) {
        id = inId;
    }


    @Override
    public String toString() {
        return getName();
    }


    private String      name;
    private String      id;


}
