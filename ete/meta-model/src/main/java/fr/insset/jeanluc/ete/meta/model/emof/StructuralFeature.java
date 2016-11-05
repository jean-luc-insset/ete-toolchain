/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof;

import fr.insset.jeanluc.ete.meta.model.types.TypedElement;

/**
 *
 * @author jldeleage
 */
public interface StructuralFeature extends Feature, TypedElement, MultiplicityElement {
    
    public boolean  isReadOnly();
    public void     setReadOnly(boolean inReadOnly);

}
