package fr.insset.jeanluc.ete.maven.plugin;

import static fr.insset.jeanluc.ete.api.Action.OUTPUT_BASE;
import fr.insset.jeanluc.ete.api.impl.ProcessorAction;
import fr.insset.jeanluc.ete.api.impl.util.InitStandardActions;
import fr.insset.jeanluc.ete.meta.model.core.impl.FactoriesInitializer;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import fr.insset.jeanluc.util.factory.FactoryRegistryImpl;
import java.io.File;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author @version
 */
@Mojo(name = "ete", requiresProject = true, threadSafe = true)
public class EteMojo
        extends AbstractMojo {

    /**
     * If <code>true</code>, display all settable properties for each goal.
     *
     */
    @Parameter(property = "config-file", defaultValue = "src/main/mda/ete-config.xml")
    private String configFilePath;

 
    @Parameter(property = "output-base", defaultValue = "")
    private String  outputBase;


    /**
     * {@inheritDoc}
     */
    public void execute()
            throws MojoExecutionException {
        System.out.println("Ete plugin running");

        Logger logger = Logger.getGlobal();
        logger.log(Level.INFO, "Working directory : " + new File(".").getAbsolutePath());
        logger.log(Level.INFO, "ConfigFilePath : " + configFilePath);

//        FactoryRegistry registry = FactoryRegistry.getRegistry();
//        registry.registerFactory(MODEL, EteModelImpl.class);
        // Registers default factories
        FactoriesInitializer.registerFactories();
        // Registers default actions
        InitStandardActions.init();;

//        Map pluginContext = this.getPluginContext();
//        if (pluginContext != null) {
//            for (Object obj : pluginContext.entrySet()) {
//                Map.Entry entry = (Map.Entry) obj;
//                logger.log(Level.INFO, entry.getKey() + " -> " + entry.getValue());
//            }
//        }

        ProcessorAction instance;
        try {
            instance = new ProcessorAction(configFilePath);
            instance.addParameter(OUTPUT_BASE, outputBase);
            System.out.println("Output-base : " + outputBase);
        } catch (InstantiationException ex) {
            Logger.getLogger(EteMojo.class.getName()).log(Level.SEVERE, null, ex);
            throw new MojoExecutionException("Unable to run ete plugin", ex);
        }
        instance.run();
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public void setOutputBase(String outputBase) {
        this.outputBase = outputBase;
    }

}
