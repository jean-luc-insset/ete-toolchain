package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * <div>
 * Every template action should perform the same basic actions :
 * <ol>
 * <li>get and resolve the template Url</li>
 * <li>get and resolve the collection of items the template should be applied
 * to</li>
 * <li>for each item in that collection :<ol>
 *   <li>pass the parameters</li>
 *   <li>resolve the target url</li>
 *   <li>and apply the template</li>
 *   <li>close everything</li>
 * </ol></li>
* </ol>
 * Eventually, the action should perform additional tasks such as create
 * directories (during the target path resolution).
 * </div>
 * <div>
 * 
 * </div>
 *
 * @author jldeleage
 */
public abstract class GenericTemplate extends ForEachAction {

    
    public final static String  TEMPLATE            = "template";
    public final static String  ITEMS               = "items";
    public final static String  TARGET              = "target";

    public final static String  TEMPLATE_ENCODING   = "template-encoding";
    public final static String  TARGET_ENCODING     = "target-encoding";


    @Override
    public void initIteration(NamedElement inElement) {
        String varName = (String)getParameter("var");
        copyAParameter(varName, inElement);
    }



    @Override
    public MofPackage   performAnIteration(MofPackage inPackage, NamedElement inElement) {
        String targetUrl = getTargetUrl();
        try {
            String templateUrl = getTemplateUrl();
            Logger.getGlobal().log(Level.INFO, "Template used on " + inElement +  " -> " + targetUrl);
            Writer output = openTargetUrl(targetUrl, (EteModel) inPackage, inElement, "UTF-8");
            applyTemplate(templateUrl, getTemplateEncoding(), output);
            output.flush();
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(GenericTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inPackage;
    }


    protected abstract void applyTemplate(String inTemplateUrl,
            String inTemplateEncoding,
            Writer inoutOutput);


    /**
     * Evaluates the "template" parameter which can be an expression. The
     * evaluation can use any parameter of the aciton.
     * 
     * @return the result of the evaluation of the "template" parameter
     */
    protected   String  getTemplateUrl() {
        Logger logger = Logger.getGlobal();
        String  result = (String) getParameter(TEMPLATE);
        logger.log(Level.FINER, "Template path : " + TEMPLATE);
        ELEvaluator elEvaluator = new ELEvaluator(getModel(), getAllParameters());
        String evaluate = (String)elEvaluator.evaluate(result);
        String  baseUrl = getBaseUrl();
        logger.log(Level.FINER, "BASE_URL : " + baseUrl);
        return baseUrl + evaluate;
    }


    public String getTemplateEncoding() {
        String encoding = (String) getParameter(TEMPLATE_ENCODING);
        return encoding == null ? "UTF-8" : encoding;
    }

    //========================================================================//
    //                               T A R G E T                              //
    //========================================================================//


    protected   String  getTargetBase() {
//        String result = (String)getParameter(OUTPUT_BASE);
        String result =  getConcatenatedParameter(OUTPUT_BASE);
        Logger.getGlobal().log(Level.INFO, "TargetBase : {0}", result);
        return result;
    }


    /**
     * TODO : should eventually resolve any variable
     * 
     * @return the resolver Url (after applying the variables)
     */
    protected   String  getTargetUrl() {
        String targetBase = getTargetBase();
        Logger  logger = Logger.getGlobal();
        logger.log(Level.FINER, "TargetBase : {0}", targetBase);
        if (targetBase == null) {
            targetBase = "./";
        }
        if (! targetBase.endsWith("/")) {
            targetBase += '/';
        }
        String target = (String) getParameter(TARGET);
        logger.log(Level.FINER, "Target : {0}", target);
        return targetBase + getParameter(TARGET);
    }


    public String getTargetEncoding() {
        String encoding = (String) getParameter("targetEncoding");
        return encoding == null ? "UTF-8" : encoding;
    }


    protected   Writer     openTargetUrl(String inTarget, EteModel inModel, NamedElement inContext, String inEncoding) throws IOException {
        Map<String, Object> localParameters = getAllParameters();
        Logger  logger = Logger.getGlobal();
        logger.log(Level.FINE, "Parameters : ");
        for (String key : localParameters.keySet()) {
            logger.log(Level.FINE, "{0} : {1}", new Object[]{key, localParameters.get(key)});
        }
        Object localVar = getLocalParameter("var");
        if (localVar != null) {
            localParameters.put((String)localVar, inContext);
        }
        logger.log(Level.FINE, "current : " + inContext.getName());
        localParameters.put("current", inContext);
        localParameters.put("model", inModel);
        ELEvaluator evaluator = new ELEvaluator(inModel, localParameters);
        String  evaluateString = (String) evaluator.evaluate(inTarget);
        int     slashIndex = evaluateString.lastIndexOf('/');
        if (slashIndex >= 0) {
            String  dirPath = evaluateString.substring(0, slashIndex);
            File dirs = new File(dirPath);
            if (! dirs.exists()) {
                logger.log(Level.INFO, "Creation of " + dirs.getAbsolutePath());
                dirs.mkdirs();
            }
        }
        logger.log(Level.INFO, "Generated file : " + evaluateString);
        return new OutputStreamWriter(new FileOutputStream(evaluateString), inEncoding);
    }


    //========================================================================//
    //                           P A R A M E T E R S                          //
    //========================================================================//
    // A template engine may accept parameters in order to customize its      //
    // output                                                                 //
    // In such a case, the action should pass all its parameters to the       //
    // engine
    //========================================================================//


    /**
     * Copy the parameters of the action to the engine.<br>
     * Could be overridden by subclasses.
     */
    protected void    copyParameters() {
        Map<String, Object> parameters = getParameters();
        Logger logger = Logger.getGlobal();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            logger.info("*** Copy of parameter " + entry.getKey() + " = " + entry.getValue());
            copyAParameter(entry.getKey(), entry.getValue());
        }
    }


    /**
     * 
     * @param inKey   the name of the variable
     * @param inValue the value of the variable
     */
    protected abstract void  copyAParameter(String inKey, Object inValue);



//    protected void    applyTemplates(Iterable<NamedElement> items) {
//        EteModel    model = getModel();
//        if (! items.iterator().hasNext()) {
//            List<NamedElement> liste = new LinkedList<NamedElement>();
//            liste.add(model);
//            items = new DefaultMapWrappedCollection<>(liste);
//        }
//        String target = getTargetUrl();
//        String template = getTemplateUrl();
//        Logger logger = Logger.getGlobal();
//        logger.log(Level.INFO, "Au moins un item \u00e0 traiter par velocity : {0}", items.iterator().hasNext());
//        for (NamedElement element : items) {            
//            initGeneration(element);
//            logger.log(Level.INFO, "Appel du template pour {0} {1}", new Object[]{target, element.getName()});
//            applyTemplate(template, target, model, element);
//        }
//    }
//
//
//    protected   abstract void    applyTemplate(String templateUrl, String targetFile,
//                                    EteModel inModel, NamedElement item);
//
//
//    protected   String      getTarget() {
//        return (String) getParameter("target");
//    }


}       // GenericTemplate
