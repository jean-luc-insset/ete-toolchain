/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author jldeleage
 */
public class AssociationImpl implements Association {

    public AssociationImpl() throws InstantiationException {
        this.ownedEnd = FactoryMethods.newList(Property.class);
        this.memberEnd = FactoryMethods.newList(Property.class);
    }


    @Override
    public Collection<Property> getMemberEnd() {
        return memberEnd;
    }

    @Override
    public void addMemberEnd(Property inProperty) {
        Collection<Property>    localMemberEnd = getMemberEnd();
        if (localMemberEnd.contains(inProperty)) {
            return;
        }
        memberEnd.add(inProperty);
        inProperty.setAssociation(this);
    }

    @Override
    public void removeMemberEnd(Property inProperty) {
        memberEnd.remove(inProperty);
        inProperty.setAssociation(null);
    }


    @Override
    public Collection<Property> getOwnedEnd() {
        return ownedEnd;
    }

    @Override
    public void addOwnedEnd(Property inProperty) {
        Collection<Property>    localOwnedEnd = getOwnedEnd();
        if (localOwnedEnd.contains(this)) {
            return;
        }
        ownedEnd.add(inProperty);
    }

    @Override
    public void removeOwnedEnd(Property inProperty) {
        ownedEnd.remove(inProperty);
    }


    private     Collection<Property>    memberEnd;
    private     Collection<Property>    ownedEnd;


}
