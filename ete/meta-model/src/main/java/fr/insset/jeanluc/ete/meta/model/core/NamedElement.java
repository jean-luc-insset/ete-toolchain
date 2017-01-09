/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.core;

import fr.insset.jeanluc.ete.meta.model.emof.Stereotype;
import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface NamedElement extends MofElement {
    
    public  String      getName();
    public  void        setName(String inName);

    public String       getId();
    public void         setId(String inId);

    public Collection<Stereotype>   getStereotypes();
    public void                     setStereotypes(Collection<Stereotype> inStereotypes);
    public default void             addStereotype(Stereotype inStereotype) {
        getStereotypes().add(inStereotype);
    }

}
