/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.core.impl;

import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.MofPackageImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;

/**
 *
 * @author jldeleage
 */
public abstract class FactoriesInitializer {
    
    public  static void registerFactories() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerFactory(MODEL, MofPackageImpl.class);
        registry.registerFactory(PACKAGE, MofPackageImpl.class);
    }

}
