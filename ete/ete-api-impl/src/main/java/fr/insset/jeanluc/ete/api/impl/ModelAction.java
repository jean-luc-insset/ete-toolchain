package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.meta.model.io.ModelReader;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
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
     * {@link fr.insset.jeanluc.util.factory.FactoryRegistry#getParameter(String)
     * getFactory(String inFactoryName) method}
     */
    public final static String  MODEL            = "model-action";
    public final static String  MODEL_READER     = "model-reader";

    /**
     * Adds the content model specified by the "model" attribute to the
     * received model.
     * 
     * @param inModel
     * @return 
     */
    @Override
    public MofPackage preProcess(MofPackage inModel) throws EteException {
        MofPackage    result = inModel;
        Object parameter = getParameter("model");
        Logger.getGlobal().log(Level.INFO, "Lecture du mod\u00e8le... {0}", parameter);
        if (parameter instanceof String) {
            String  baseUrl = getBaseUrl();
            try {
                // TODO : obtenir le "reader" par une fabrique abstraite
                ModelReader   reader = (ModelReader) FactoryRegistry.newInstance(MODEL_READER);
                result = reader.readModel(baseUrl, (EteModel) inModel);
            } catch (InstantiationException ex) {
                Logger.getLogger(ModelAction.class.getName()).log(Level.SEVERE, null, ex);
                throw new EteException(ex);
            }
            Logger.getGlobal().log(Level.INFO, "Lecture -> {0}", inModel);
        }
        return result;
    }


}
