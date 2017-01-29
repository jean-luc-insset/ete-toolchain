/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.core.impl.NamedElementImpl;
import fr.insset.jeanluc.ete.meta.model.emof.TagValueDeclaration;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import java.util.Map;

/**
 *
 * @author jldeleage
 */
public class TagValueDeclarationImpl extends NamedElementImpl implements TagValueDeclaration {

    @Override
    public MofType getValueType() {
        return valueType;
    }

    @Override
    public void setValueType(MofType inType) {
        valueType = inType;
    }

    MofType valueType;

}
