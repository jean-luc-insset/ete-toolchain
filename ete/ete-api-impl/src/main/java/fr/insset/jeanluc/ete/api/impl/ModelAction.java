package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.meta.model.io.ModelReader;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Reads an XMI model.<br>
 * Every pipeline in the configuration file should start with such an
 * action.
 *
 * @author jldeleage
 */
public class ModelAction extends ActionSupport {

    /**
     * Name of factory. This value must be used as a key with the
     * getFactory(String inFactoryName) method
     */
    public final static String  MODEL_ACTION     = "model-action";
    public final static String  MODEL_READER     = "model-reader";

    /**
     * Adds the content model specified by the "model" attribute to the
     * received model.
     * 
     * @param inModel
     * @return the result of the preprocessing (maybe inModel)
     */
    @Override
    public MofPackage preProcess(MofPackage inModel) throws EteException {
        MofPackage    result = inModel;
        String parameter = (String) getParameter("url");
        Logger.getGlobal().log(Level.INFO, "Reading model... {0}", parameter);
        InputStream resource = getResource(parameter);
        try {
            // obtenir le "reader" par une fabrique abstraite
            ModelReader   reader = (ModelReader) FactoryRegistry.newInstance(MODEL_READER);
            result = reader.readModel(resource, (EteModel) inModel);
        } catch (EteException e) {
            throw e;

        } catch (Exception ex) {
            Logger.getLogger(ModelAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
        Logger.getGlobal().log(Level.INFO, "Lecture -> {0}", inModel);
        return result;
    }

    public String   getUrl() {
        return (String) getParameter("url");
    }

}
