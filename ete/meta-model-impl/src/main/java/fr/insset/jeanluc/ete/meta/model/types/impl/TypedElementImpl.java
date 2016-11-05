/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.types.impl;

import fr.insset.jeanluc.ete.meta.model.core.impl.NamedElementImpl;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.TypedElement;

/**
 *
 * @author jldeleage
 */
public class TypedElementImpl extends NamedElementImpl implements TypedElement {

    @Override
    public MofType getType() {
        return mofType;
    }

    @Override
    public void setType(MofType inType) {
        mofType = inType;
    }


    private     MofType     mofType;

}
