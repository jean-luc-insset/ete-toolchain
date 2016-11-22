/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.impl.util;

import static fr.insset.jeanluc.ete.meta.model.emof.Association.ASSOCIATION;
import static fr.insset.jeanluc.ete.meta.model.emof.MofClass.MOF_CLASS;
import static fr.insset.jeanluc.ete.meta.model.emof.Operation.OPERATION;
import static fr.insset.jeanluc.ete.meta.model.emof.Property.PROPERTY;
import fr.insset.jeanluc.ete.meta.model.emof.impl.AssociationImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.MofClassImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.OperationImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.PropertyImpl;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage.PACKAGE;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.MofPackageImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;

/**
 *
 * @author jldeleage
 */
public abstract class MetaModelInit {
    

    public static void init() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerDefaultFactory(MODEL, EteModelImpl.class);
        registry.registerDefaultFactory(PACKAGE, MofPackageImpl.class);
        registry.registerDefaultFactory(MOF_CLASS, MofClassImpl.class);
        registry.registerDefaultFactory(ASSOCIATION, AssociationImpl.class);
        registry.registerDefaultFactory(PROPERTY, PropertyImpl.class);
        registry.registerDefaultFactory(OPERATION, OperationImpl.class);
    }

}
