/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api;

import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;

/**
 * A Step is like an {@link Action Action} but can work at the package level
 * too
 *
 * @author jldeleage
 */
public interface Step extends Action {
    
    public  default MofPackage  process(MofPackage inPackage) {
        inPackage = preProcess(inPackage);
        inPackage = doProcess(inPackage);
        inPackage = postProcess(inPackage);
        return inPackage;
    }

    public default MofPackage preProcess(MofPackage inPackage) {
        return inPackage;
    }

    public default MofPackage doProcess(MofPackage inPackage) {
        return inPackage;
    }

    public default MofPackage postProcess(MofPackage inPackage) {
        return inPackage;
    }


}
