/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.xmi.io.impl;

import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;



/**
 *
 * @author jldeleage
 */
public class XmlModelReaderVisitor extends DynamicVisitorSupport {

    public XmlModelReaderVisitor() {
        this.register("visit",
                    "fr.insset.jeanluc.ete.meta.model.emof",
                    "fr.insset.jeanluc.ete.meta.model.mofpackage");
    }


    public Object   visitPackageableElement(PackageableElement inElement, Object... inParam) {
        PackageableElement packageable = (PackageableElement) inElement;
        NamedElement       parentElement = (NamedElement) inParam[0];
        if (parentElement instanceof MofPackage) {
            MofPackage parentPackage = (MofPackage) parentElement;
            parentPackage.addPackagedElement(packageable);
            packageable.setOwningPackage(parentPackage);
        }
        EteModel            inModel = (EteModel) inParam[1];
        inModel.addPackagedElement(packageable);
        return inElement;
    }

    public Object   visitProperty(Property inProperty, Object... inParam) {
        MofClass    parentClass = (MofClass) inParam[0];
        parentClass.addOwnedAttribute((Property) inProperty);
        return inProperty;
    }

    public Object   visitOperation(Operation inOperation, Object... inParam) {
        MofClass    parentClass = (MofClass) inParam[0];
        parentClass.addOwnedOperation(inOperation);
        return inOperation;
    }

}
