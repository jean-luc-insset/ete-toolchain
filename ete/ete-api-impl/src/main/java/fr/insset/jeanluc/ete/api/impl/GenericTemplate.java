package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.util.coll.DefaultMapWrappedCollection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Every template action should perform the same basic actions :
 * <ul>
 * <li>get the template</li>
 * <li>get and resolve the collection of items the template should be applied
 * to</li>
 * <li>for each item in that collection, pass the parameters</li>
 * <li>resolve the path</li>
 * <li>and apply the template</li>
 * <li>close everything</li>
 * </ul>
 * Eventually, the action should perform additional tasks such as create
 * directories (during the path resolution).
 *
 * @author jldeleage
 */
public abstract class GenericTemplate extends ForEach {

//    @Override
//    public EteModel preProcess(EteModel inModel) {
//        addParameter("model", inModel);
//        return inModel;
//    }
//
//
//
//    @Override
//    public EteModel doProcess(EteModel inModel) {
//        initEngine();
//        List<NamedElement> items = getItems();
//        copyParameters();
//        applyTemplates(items);
//        closeEngine();
//        return inModel;
//    }




    protected   String  getTemplate() {
        return (String) getParameter("template");
    }


    /**
     * TODO : should eventually resolve any variable
     * 
     * @return 
     */
    protected   String  getTemplateUrl() {
        String  result = (String) getParameter("template");
        String  baseUrl = getBaseUrl();
        return baseUrl + result;
    }

    public String getTemplateEncoding() {
        String encoding = (String) getParameter("templateEncoding");
        return encoding == null ? "UTF-8" : encoding;
    }


    protected   String  getTargetBase() {
        return (String)getParameter("output-base");
    }

    /**
     * TODO : should eventually resolve any variable
     * 
     * @return 
     */
    protected   String  getTargetUrl() {
        String targetBase = getTargetBase();
        if (targetBase == null) {
            targetBase = "./";
        }
        if (! targetBase.endsWith("/")) {
            targetBase += '/';
        }
        return targetBase + getParameter("target");
    }

    public String getTargetEncoding() {
        String encoding = (String) getParameter("targetEncoding");
        return encoding == null ? "UTF-8" : encoding;
    }


//    protected   Writer     openTargetUrl(String inTarget, EteModel inModel, NamedElement inContext, String inEncoding) throws IOException {
//        Map<String, Object> localParameters = new HashMap<>();
//        localParameters.putAll(getParameters());
//        Object localVar = getLocalParameter("var");
//        if (localVar != null) {
//            localParameters.put((String)localVar, inContext);
//        }
//        Logger logger = Logger.getGlobal();
//        logger.log(Level.INFO, "passage de current : " + inContext.getName());
//        localParameters.put("current", inContext);
//        localParameters.put("model", inModel);
//        ELEvaluator evaluator = new ELEvaluator(inModel, localParameters);
//        String  evaluateString = evaluator.evaluateString(inTarget);
//        int     slashIndex = evaluateString.lastIndexOf('/');
//        if (slashIndex >= 0) {
//            String  dirPath = evaluateString.substring(0, slashIndex);
//            File dirs = new File(dirPath);
//            logger.log(Level.INFO, "Creation de " + dirs.getAbsolutePath());
//            dirs.mkdirs();
//        }
//        else {
//            logger.log(Level.INFO, "Pas de creation de dossier, generation dans le dossier de travail");
//        }
//        return new OutputStreamWriter(new FileOutputStream(evaluateString), inEncoding);
//    }
//
//
//    // TODO
//    // This method should resolve an eventual expression
//    protected   List<NamedElement>    getItems() {
//        Object parameter = getParameter("items");
//        if (parameter == null) {
//            return new LinkedList<NamedElement>();
//        }
//        ELEvaluator evaluator = new ELEvaluator(getModel());
//        return evaluator.evaluateList((String)parameter);
//    }



    /**
     * Copy the parameters of the action to the engine.<br>
     * Could be overridden by subclasses.<br>
     * This is an optional method.
     */
    protected void    copyParameters() {
        Map<String, Object> parameters = getParameters();
        Logger logger = Logger.getGlobal();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            logger.info("*** Copie du paramètre " + entry.getKey() + " = " + entry.getValue());
            copyAParameter(entry.getKey(), entry.getValue());
        }
    }


    /**
     * 
     * @param inKey
     * @param inValue 
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
