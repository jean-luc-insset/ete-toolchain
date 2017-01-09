package fr.insset.jeanluc.ete.meta.model.core.impl;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.Stereotype;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public Collection<Stereotype> getStereotypes() {
        if (stereotypes == null) {
            try {
                stereotypes = FactoryMethods.newSet(Stereotype.class);
            } catch (InstantiationException ex) {
                Logger.getLogger(NamedElementImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stereotypes;
    }

    @Override
    public void setStereotypes(Collection<Stereotype> inStereotypes) {
        stereotypes = inStereotypes;
    }



    private String                  name;
    private String                  id;
    private Collection<Stereotype>  stereotypes;


}
