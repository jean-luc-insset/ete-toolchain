/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.core;

import fr.insset.jeanluc.ete.meta.model.emof.TagValueDeclaration;
import fr.insset.jeanluc.ete.meta.model.emof.Stereotype;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author jldeleage
 */
public interface NamedElement extends MofElement {
    
    public  String      getName();
    public  void        setName(String inName);

    public Collection<Stereotype>           getStereotypes();
    public void                             setStereotypes(Collection<Stereotype> inStereotypes);
    public default void                     addStereotype(Stereotype inStereotype) {
        getStereotypes().add(inStereotype);
    }

    public Map<TagValueDeclaration, Object> getTagValues();
    public void                             setTagValues(Map<TagValueDeclaration, Object> inTagValues);
    public void                             addTagValue(TagValueDeclaration inDeclaration, Object inValue);


    /**
     * WARNING : should be overridden by items able to inherit their
     * stereotypes.
     * 
     * @param inStereotypeName
     * @return true if this is marked with a stereotype named inStereotypeName.
     */
    public default boolean          hasStereotype(String inStereotypeName) {
        for (Stereotype stereotype : getStereotypes()) {
            if (inStereotypeName.equals(stereotype.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * WARNING : should be overridden by items able to inherit their
     * stereotypes.
     * 
     * @param inStereotypeName
     * @return the first stereotype named inStereotypeName and marking this.
     */
    public default Stereotype       getStereotype(String inStereotypeName) {
        for (Stereotype stereotype : getStereotypes()) {
            if (inStereotypeName.equals(stereotype.getName())) {
                return stereotype;
            }
        }
        return null;
    }

}
