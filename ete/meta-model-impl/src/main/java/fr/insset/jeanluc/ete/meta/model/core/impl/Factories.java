package fr.insset.jeanluc.ete.meta.model.core.impl;


import static fr.insset.jeanluc.ete.meta.model.emof.Association.ASSOCIATION;
import static fr.insset.jeanluc.ete.meta.model.emof.MofClass.MOF_CLASS;
import static fr.insset.jeanluc.ete.meta.model.emof.Operation.OPERATION;
import static fr.insset.jeanluc.ete.meta.model.emof.Property.PROPERTY;
import fr.insset.jeanluc.ete.meta.model.emof.impl.AssociationImpl;
import        fr.insset.jeanluc.ete.meta.model.emof.impl.MofClassImpl;
import        fr.insset.jeanluc.ete.meta.model.emof.impl.OperationImpl;
import        fr.insset.jeanluc.ete.meta.model.emof.impl.PropertyImpl;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage.PACKAGE;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import        fr.insset.jeanluc.ete.meta.model.mofpackage.impl.MofPackageImpl;
import        fr.insset.jeanluc.ete.meta.model.types.Generalization;
import static fr.insset.jeanluc.ete.meta.model.types.Generalization.GENERALIZATION;
import static fr.insset.jeanluc.ete.meta.model.types.MofType.MOF_TYPE;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import        fr.insset.jeanluc.util.factory.FactoryRegistry;

/**
 *
 * @author jldeleage
 */
public abstract class Factories {
    

    public  static void init() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerFactory(MOF_TYPE, MofTypeImpl.class);
        registry.registerFactory(MODEL, EteModelImpl.class);
        registry.registerFactory(PACKAGE, MofPackageImpl.class);
        registry.registerFactory(MOF_CLASS, MofClassImpl.class);
        registry.registerFactory(OPERATION, OperationImpl.class);
        registry.registerFactory(PROPERTY, PropertyImpl.class);
        registry.registerFactory(ASSOCIATION, AssociationImpl.class);
        registry.registerFactory(GENERALIZATION, Generalization.class);
    }


}
