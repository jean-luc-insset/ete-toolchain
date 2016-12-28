/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.core;

import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import static fr.insset.jeanluc.ete.meta.model.types.MofType.MOF_TYPE;
import fr.insset.jeanluc.util.factory.FactoryRegistry;

/**
 *
 * @author jldeleage
 */
public abstract class PrimitiveDataTypes {
    

    /**
     * MUST be called after initializing factories.
     */
    public static void init(EteModel inoutModel) throws InstantiationException {
        init(inoutModel, "String");
        init(inoutModel, "boolean");
        init(inoutModel, "int");
        init(inoutModel, "date");
        init(inoutModel, "float");
    }

    protected static void init(EteModel inoutModel, String inName) throws InstantiationException {
        PackageableElement newInstance =  (PackageableElement) FactoryRegistry.newInstance(MOF_TYPE);
        newInstance.setName(inName);
        inoutModel.addElement(newInstance);
        inoutModel.addPackagedElement(newInstance);
    }

}