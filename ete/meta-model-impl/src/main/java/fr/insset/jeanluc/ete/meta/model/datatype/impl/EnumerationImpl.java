/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.datatype.impl;

import fr.insset.jeanluc.ete.meta.model.datatype.Enumeration;
import fr.insset.jeanluc.ete.meta.model.instance.EnumerationLiteral;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jldeleage
 */
public class EnumerationImpl extends DataTypeImpl implements Enumeration {

    @Override
    public List<EnumerationLiteral> getOwnedLiteral() {
        return ownedLiteral;
    }

    @Override
    public void addOwnedLiteral(EnumerationLiteral inEnumerationLiteral) {
        ownedLiteral.add(inEnumerationLiteral);
    }

    @Override
    public void removeOwnedLiteral(EnumerationLiteral inEnumerationLiteral) {
        ownedLiteral.remove(inEnumerationLiteral);
    }


    List<EnumerationLiteral>    ownedLiteral = new LinkedList<>();


}
