/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof;

import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;

/**
 *
 * @author jldeleage
 */
public interface Stereotype extends PackageableElement {

    public final static String      STEREOTYPE = "stereotype";

    public MofPackage   getProfile();
    public void         setProfile(MofPackage inProfile);

}
