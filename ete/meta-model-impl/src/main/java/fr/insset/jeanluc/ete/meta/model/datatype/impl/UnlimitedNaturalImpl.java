/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.datatype.impl;

import fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural;

/**
 *
 * @author jldeleage
 */
public class UnlimitedNaturalImpl extends DataTypeImpl implements UnlimitedNatural {

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String inValue) {
        value = inValue;
    }

    private String value;

}
