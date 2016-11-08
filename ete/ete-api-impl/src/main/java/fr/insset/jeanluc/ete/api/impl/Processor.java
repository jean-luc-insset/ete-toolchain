/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.ActionReader;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.util.factory.FactoryRegistry.FACTORY_REGISTRY;
import fr.insset.jeanluc.util.service.ServiceRegistry;
import fr.insset.jeanluc.util.service.ServiceRegistryImpl;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * A processor runs a pipeline made of actions.<br>
 * The pipeline is described in a configuration file, just as modules are.<br>
 * So a processor is a special kind of module call. The only difference is the
 * way the parameters are passed&nbsp;:<ul>
 * <li>through attributes and sub-elements in a plain module call</li>
 * <li>through program in a processor</li>
 * </ul>
 *
 * @author jldeleage
 */
public class Processor extends ModuleCallAction implements Runnable {


    public Processor() throws InstantiationException {
        this("src/main/mda/ete-config.xml");
    }


    public Processor(String pathToConfigFile) throws InstantiationException {
        addParameter("url", pathToConfigFile);
        int     index = pathToConfigFile.lastIndexOf('/');
//        addParameter("base-url", index==-1?"":pathToConfigFile.substring(0, index+1));
        if (index == -1) {
            addParameter("config-file-name", pathToConfigFile);
            addParameter("base-url", "");
        }
        else {
            String      configFileName = pathToConfigFile.substring(index+1);
            addParameter("config-file-name", configFileName);   
            addParameter("url", configFileName); 
            addParameter("base-url", pathToConfigFile.substring(0, index+1));
        }

        // Register standard actions and readers
        InitStandardActions.init();

        // Start with an empty model.
        // It will be populated by later <model> instructions.
        // The model factory must have been registered
        FactoryRegistry     registry = FactoryRegistry.getRegistry();
        // Initialize the registry chain to allow local contexts
        addParameter(FACTORY_REGISTRY, registry);
        AbstractFactory     modelFactory = registry.getFactory(EteModel.MODEL);
        model = (EteModel) modelFactory.newInstance();

        // TODO : register other services than XML for configuration files
        ServiceRegistry services = ServiceRegistryImpl.getRegistry();
        index = pathToConfigFile.lastIndexOf(".");
        String  extension = pathToConfigFile.substring(index);
        Object service = services.getService(extension);
        setReader((ActionReader) service);

        Logger.getGlobal().log(Level.INFO, "Processor instantiated with " + pathToConfigFile);
    }       // constructor



    public void run() {
        try {
            model = process(model);
        } catch (EteException ex) {
            Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       // run



//    @Override
//    public Iterable<Action> getChildren() {
//        return null;
//    }


//    public void addParameter(String inName, Object inValue) {
//        if (parameters == null) {
//            parameters = new HashMap<>();
//        }
//        parameters.put(inName, inValue);
//    }
//
//
//    public void addParameters(Map<String, Object> inParameters) {
//        if (parameters == null) {
//            parameters = new HashMap<>();
//        }
//        parameters.putAll(inParameters);
//    }


    public EteModel getModel() {
        return model;
    }



    private EteModel                model;

}       // Processor
