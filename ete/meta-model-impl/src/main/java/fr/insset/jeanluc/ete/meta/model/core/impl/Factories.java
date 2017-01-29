package fr.insset.jeanluc.ete.meta.model.core.impl;

import static fr.insset.jeanluc.ete.meta.model.constraint.Invariant.INVARIANT;
import static fr.insset.jeanluc.ete.meta.model.constraint.Postcondition.POSTCONDITION;
import static fr.insset.jeanluc.ete.meta.model.constraint.Precondition.PRECONDITION;
import fr.insset.jeanluc.ete.meta.model.constraint.impl.InvariantImpl;
import fr.insset.jeanluc.ete.meta.model.constraint.impl.PostconditionImpl;
import fr.insset.jeanluc.ete.meta.model.constraint.impl.PreconditionImpl;
import static fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural.UNLIMITED_NATURAL;
import fr.insset.jeanluc.ete.meta.model.datatype.impl.UnlimitedNaturalImpl;
import static fr.insset.jeanluc.ete.meta.model.emof.Association.ASSOCIATION;
import static fr.insset.jeanluc.ete.meta.model.emof.MofClass.MOF_CLASS;
import static fr.insset.jeanluc.ete.meta.model.emof.Operation.OPERATION;
import static fr.insset.jeanluc.ete.meta.model.emof.Parameter.PARAMETER;
import static fr.insset.jeanluc.ete.meta.model.emof.Property.PROPERTY;
import static fr.insset.jeanluc.ete.meta.model.emof.Stereotype.STEREOTYPE;
import static fr.insset.jeanluc.ete.meta.model.emof.TagValueDeclaration.TAG_VALUE_DECLARATION;
import fr.insset.jeanluc.ete.meta.model.emof.impl.AssociationImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.MofClassImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.OperationImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.ParameterImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.PropertyImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.StereotypeImpl;
import fr.insset.jeanluc.ete.meta.model.emof.impl.TagValueDeclarationImpl;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.MofPackageImpl;
import fr.insset.jeanluc.ete.meta.model.types.Generalization;
import static fr.insset.jeanluc.ete.meta.model.types.Generalization.GENERALIZATION;
import static fr.insset.jeanluc.ete.meta.model.types.MofType.MOF_TYPE;
import fr.insset.jeanluc.ete.meta.model.types.collections.impl.MofBagImpl;
import fr.insset.jeanluc.ete.meta.model.types.collections.impl.MofOrderedSetImpl;
import fr.insset.jeanluc.ete.meta.model.types.collections.impl.MofSequenceImpl;
import fr.insset.jeanluc.ete.meta.model.types.collections.impl.MofSetImpl;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.ete.meta.model.types.collections.MofSequence.MOF_SEQUENCE;
import static fr.insset.jeanluc.ete.meta.model.types.collections.MofBag.MOF_BAG;
import static fr.insset.jeanluc.ete.meta.model.types.collections.MofOrderedSet.MOF_ORDERED_SET;
import static fr.insset.jeanluc.ete.meta.model.types.collections.MofSet.MOF_SET;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage.MOF_PACKAGE;

/**
 *
 * @author jldeleage
 */
public abstract class Factories {

    public static void init() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerDefaultFactory(MOF_TYPE, MofTypeImpl.class);
        registry.registerDefaultFactory(MODEL, EteModelImpl.class);
        registry.registerDefaultFactory(MOF_PACKAGE, MofPackageImpl.class);
        registry.registerDefaultFactory(MOF_CLASS, MofClassImpl.class);
        registry.registerDefaultFactory(OPERATION, OperationImpl.class);
        registry.registerDefaultFactory(PARAMETER, ParameterImpl.class);
        registry.registerDefaultFactory(PROPERTY, PropertyImpl.class);
        registry.registerDefaultFactory(ASSOCIATION, AssociationImpl.class);
        registry.registerDefaultFactory(GENERALIZATION, Generalization.class);
        registry.registerDefaultFactory(UNLIMITED_NATURAL, UnlimitedNaturalImpl.class);
        registry.registerDefaultFactory(MOF_SEQUENCE, MofSequenceImpl.class);
        registry.registerDefaultFactory(MOF_BAG, MofBagImpl.class);
        registry.registerDefaultFactory(MOF_SET, MofSetImpl.class);
        registry.registerDefaultFactory(MOF_ORDERED_SET, MofOrderedSetImpl.class);
        registry.registerDefaultFactory(INVARIANT, InvariantImpl.class);
        registry.registerDefaultFactory(PRECONDITION, PreconditionImpl.class);
        registry.registerDefaultFactory(POSTCONDITION, PostconditionImpl.class);
        registry.registerDefaultFactory(STEREOTYPE, StereotypeImpl.class);
        registry.registerDefaultFactory(TAG_VALUE_DECLARATION, TagValueDeclarationImpl.class);
    }

}
