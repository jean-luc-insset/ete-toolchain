/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.mofpackage;

import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.Collection;
import java.util.stream.Stream;


/**
 * An EteModel is a MofPackage but may have a "parent" model. This is different
 * from an owning package.
 *
 * @author jldeleage
 */
public interface EteModel extends MofPackage {


    public  final static String     MODEL = "model";


    public EteModel getParent();
    public void     setParent(EteModel inParent);

    public default EteModel createModel() throws InstantiationException {
        FactoryRegistry     registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(MODEL);
        EteModel result = (EteModel) factory.newInstance();
        result.setParent(this);
        return result;
    }

}

