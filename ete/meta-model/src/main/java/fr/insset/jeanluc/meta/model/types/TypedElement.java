/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.types;

import fr.insset.jeanluc.meta.model.core.NamedElement;

/**
 *
 * @author jldeleage
 */
public interface TypedElement extends NamedElement {
    

    public  MofType     getType();
    public  void        setType(MofType inType);

}
