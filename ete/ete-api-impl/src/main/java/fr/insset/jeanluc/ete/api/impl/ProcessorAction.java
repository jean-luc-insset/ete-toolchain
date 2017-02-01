package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.impl.util.InitStandardActions;
import fr.insset.jeanluc.ete.api.ActionReader;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
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
public class ProcessorAction extends ModuleCallAction implements Runnable {


    public ProcessorAction() throws InstantiationException {
        this("src/main/mda/ete-config.xml");
    }


    public ProcessorAction(String pathToConfigFile) throws InstantiationException {
        int     index = pathToConfigFile.lastIndexOf('/');
//        addParameter("base-url", index==-1?"":pathToConfigFile.substring(0, index+1));
        if (index == -1) {
            addParameter(SRC, pathToConfigFile);
            addParameter(BASE_DIR, "./");
        }
        else {
            String      configFileName = pathToConfigFile.substring(index+1);
            addParameter(SRC, configFileName);
            String basedir = pathToConfigFile.substring(0, index+1);
            addParameter(BASE_DIR, basedir);
        }

        Logger.getGlobal().log(Level.FINE, "base-url : " + getParameter(BASE_DIR));
        Logger.getGlobal().log(Level.FINE, "config file : " + getParameter(SRC));
        // Register standard actions and readers
        InitStandardActions.init();

        EteModel     model = (EteModel) FactoryRegistry.newInstance(EteModel.MODEL);
        addParameter(EteModel.MODEL, model);
        PrimitiveDataTypes.init(model);

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
            process(getModel());
        } catch (EteException ex) {
            Logger.getLogger(ProcessorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       // run




}       // Processor
