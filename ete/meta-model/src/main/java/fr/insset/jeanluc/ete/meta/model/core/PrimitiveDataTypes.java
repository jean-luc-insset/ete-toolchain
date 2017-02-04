/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.core;

import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import static fr.insset.jeanluc.ete.meta.model.types.MofType.MOF_TYPE;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public abstract class PrimitiveDataTypes {

    public final    static String   TYPE_SUFFIX     = "";

    public final    static String   STRING_TYPE     = "String";
    public final    static String   BOOLEAN_TYPE    = "boolean";
    public final    static String   INT_TYPE        = "int";
    public final    static String   DATE_TYPE       = "date";
    public final    static String   FLOAT_TYPE      = "float";

    /**
     * MUST be called after initializing factories.
     */
    public static void init(EteModel inoutModel) throws InstantiationException {
        init(inoutModel, STRING_TYPE);
        init(inoutModel, BOOLEAN_TYPE);
        init(inoutModel, INT_TYPE);
        init(inoutModel, DATE_TYPE);
        init(inoutModel, FLOAT_TYPE);
    }


    protected static void init(EteModel inoutModel, String inName) throws InstantiationException {
        PackageableElement newInstance =  (PackageableElement) FactoryRegistry.newInstance(MOF_TYPE);
        newInstance.setType(typeType);
        // The name is used by the model to register the instance. To prevent
        // any collision with another "integer" or "String" object, we add
        // a suffix to the name.
        newInstance.setName(inName + TYPE_SUFFIX);
        inoutModel.addElement(newInstance);
        inoutModel.addPackagedElement(newInstance);
        // But we set the name back to its true value after registering.
        newInstance.setName(inName);
    }



    public static MofType getTypeType() {
        return typeType;
    }



    private static MofType  typeType;

    static {
        try {
            typeType = (MofType)FactoryRegistry.newInstance(MOF_TYPE);
            typeType.setName("TypeType");
            typeType.setType(typeType);
        } catch (InstantiationException ex) {
            Logger.getLogger(PrimitiveDataTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
