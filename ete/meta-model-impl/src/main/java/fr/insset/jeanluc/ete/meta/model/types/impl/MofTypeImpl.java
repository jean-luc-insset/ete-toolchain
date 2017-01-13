/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.types.impl;

import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.PackageableElementImpl;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public class MofTypeImpl extends PackageableElementImpl implements MofType {

    @Override
    public Collection<MofType> getSuperTypes() {
        if (superTypes == null) {
            try {
                superTypes = FactoryMethods.newSet(MofType.class);
            } catch (InstantiationException ex) {
                Logger.getLogger(MofTypeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return superTypes;
    }

    @Override
    public void setSuperTypes(Collection<MofType> inSuperTypes) {
        superTypes = inSuperTypes;
    }

    @Override
    public void addSuperType(MofType inSuperType) {
        if (superTypes == null) {
            try {
                superTypes = FactoryMethods.newSet(MofType.class);
            } catch (InstantiationException ex) {
                Logger.getLogger(MofTypeImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        superTypes.add(inSuperType);
    }

    private     Collection<MofType>     superTypes;

}
