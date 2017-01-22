/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.emof.Stereotype;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.PackageableElementImpl;

/**
 *
 * @author jldeleage
 */
public class StereotypeImpl extends PackageableElementImpl implements Stereotype {

    @Override
    public MofPackage getProfile() {
        return profile;
    }

    @Override
    public void setProfile(MofPackage inProfile) {
        profile = inProfile;
    }
    
    private     MofPackage      profile;
}
