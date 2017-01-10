package fr.insset.jeanluc.ete.maven.plugin;

import static fr.insset.jeanluc.ete.api.Action.BASE_DIR;
import static fr.insset.jeanluc.ete.api.Action.OUTPUT_BASE;
import fr.insset.jeanluc.ete.api.impl.ProcessorAction;
import fr.insset.jeanluc.ete.api.impl.util.InitStandardActions;
import fr.insset.jeanluc.ete.meta.model.core.impl.Factories;
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

 
    @Parameter(property = "output-base", defaultValue = "target/generated-sources/ete/")
    private String  outputBase;

    @Parameter(property="basedir")
    private String  basedir;

    /**
     * {@inheritDoc}
     */
    public void execute()
            throws MojoExecutionException {
        Logger.getGlobal().log(Level.INFO, "Ete plugin running");

        Logger logger = Logger.getGlobal();
        logger.log(Level.INFO, "Working directory : " + new File(".").getAbsolutePath());
        logger.log(Level.INFO, "BaseDir : " + basedir);
        // If we run outside of maven, the property may be unset
        if (basedir == null) {
            basedir = ".";
        }
        logger.log(Level.INFO, "ConfigFilePath : " + configFilePath);

        // Registers default factories
        Factories.init();
        // Registers default actions
        InitStandardActions.init();;

        ProcessorAction instance;
        try {
            File    baseDirFile = new File(basedir);
            basedir = baseDirFile.getAbsolutePath();
            if (! basedir.endsWith("/")) {
                basedir += '/';
            }
            outputBase = basedir + outputBase;
            int index = configFilePath.lastIndexOf('/');
            if (index >= 0) {
                basedir += configFilePath.substring(0, index+1);
                configFilePath = configFilePath.substring(index+1);
            }
            instance = new ProcessorAction(basedir + configFilePath);
            instance.addParameter(BASE_DIR, basedir);
//            instance = new ProcessorAction(configFilePath);
            instance.addParameter(OUTPUT_BASE, outputBase);
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

    public void setBasedir(String basedir) {
        this.basedir = basedir;
    }

}
