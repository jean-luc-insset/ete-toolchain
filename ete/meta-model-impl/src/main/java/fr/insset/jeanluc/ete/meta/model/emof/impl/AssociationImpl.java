/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author jldeleage
 */
public class AssociationImpl implements Association {


    @Override
    public Collection<Property> getMemberEnd() {
        return memberEnd;
    }

    @Override
    public void addMemberEnd(Property inProperty) {
        memberEnd.add(inProperty);
    }

    @Override
    public void removeMemberEnd(Property inProperty) {
        memberEnd.remove(inProperty);
    }


    @Override
    public Collection<Property> getOwnedEnd() {
        return ownedEnd;
    }

    @Override
    public void addOwnedEnd(Property inProperty) {
        ownedEnd.add(inProperty);
    }

    @Override
    public void removeOwnedEnd(Property inProperty) {
        ownedEnd.remove(inProperty);
    }


    private     Collection<Property>    memberEnd   = new LinkedList<>();
    private     Collection<Property>    ownedEnd    = new LinkedList<>();


}
