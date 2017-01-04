package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import static fr.insset.jeanluc.ete.api.impl.GenericTemplate.TEMPLATE;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.io.File;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.runtime.RuntimeConstants;



/**
 * A velocity template is applied to a single item or to each item in a
 * collection.<br>
 * The life cycle is :<pre>
 * initEngine
 *      (
 *      initGeneration
 *      applyTemplate
 *      )*
 * closeEngine</pre>
 *
 * @author jldeleage
 */
public class VelocityAction extends GenericTemplate {


    public final static String      VELOCITY_ACTION     = "velocity-action";


    @Override
    protected void initLoop() {
        Logger  logger = Logger.getGlobal();
        logger.log(Level.INFO, "Initializing Velocity");
        MofPackage    model = getModel();
        ve = new VelocityEngine();
//        ve.setProperty("resource.loader", "file");
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, getBaseUrl());
        System.out.println("Velocity template root : " + getBaseUrl());
//        Object property = ve.getProperty("resource.loader");
//        System.out.println("Velocity Resource Loader" + property);
        try {
            ve.init();
        } catch (Exception ex) {
            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        context = new VelocityContext();
        ExtendedProperties eprops = new ExtendedProperties();
        eprops.put("model", model);
        context.put("model", model);
//        for (Map.Entry<String,Object> entry : getParameters().entrySet()) {
//            logger.log(Level.FINE, "Passage du parametre " + entry.getKey() + " = " + entry.getValue());
//            context.put(entry.getKey(), entry.getValue());
//        }

//        String  outputBase = (String) getParameter("output-base");
//        String racine = outputBase == null ? "." : outputBase;
//        if (racine.length() > 0 && ! racine.endsWith("/")) {
//            racine += "/";
//        }
//        logger.log(Level.INFO, "   velocity.output-base : " + racine);
//
//        Map<Object,Object> variables = new HashMap<>();
//        variables.put("model", model);
//        variables.put("classes",model.getClasses());
//        variables.put("packages", model.getPackages());
    }


    /**
     * Evaluates the "template" parameter which can be an expression. The
     * evaluation can use any parameter of the action.<br>
     * The overridden method injects getBaseUrl() at the beginning of the
     * url. In some cases this does not work with Velocity since the
     * FileResourceLoader must load its templates in a subtree with
     * TEMPLATE_ROOT as root.
     * 
     * @return the result of the evaluation of the "template" parameter
     */
    protected   String  getTemplateUrl() {
        String  result = (String) getParameter(TEMPLATE);
        ELEvaluator elEvaluator = new ELEvaluator(getModel(), getParameters());
        String evaluate = elEvaluator.evaluate(result, String.class);
        return evaluate;
    }


    @Override
    protected void applyTemplate(String inTemplateUrl, String inTemplateEncoding, Writer inoutOutput) {
        System.out.println("TODO : applyTemplate. WD : " + new File(".").getAbsolutePath());
        try {
            ve.mergeTemplate(inTemplateUrl, context, inoutOutput);
        } catch (ParseErrorException | MethodInvocationException ex) {
            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initIteration(NamedElement inElement) {
        Object varName = getParameter("var");
        context.put((String)varName, inElement);
    }




    @Override
    protected void copyAParameter(String inKey, Object inValue) {
//        ve.addProperty(inKey, inValue);
        context.put(inKey, inValue);
    }       // copyParameters


//    @Override
//    protected void applyTemplate(String templateUrl, String targetFile,
//                        EteModel inModel, NamedElement inItem) {
//        InputStream     input = null;
//        Logger      logger = Logger.getGlobal();
//
//        // TODO : throw an error
//        if (targetFile == null || targetFile == "") {
//            targetFile = "test.txt";
//        }
//        try {
//            input = new FileInputStream(templateUrl);
//            String  targetEncoding = getTargetEncoding();
//            String  baseTarget     = getTargetBase();
//            Writer writer = openTargetUrl(targetFile, inModel, inItem, targetEncoding);
//            String  templateEncoding = getTemplateEncoding();
//
//            ve.addProperty("current", inItem);
//            context.put("current", inItem);
//            Object localParameter = getLocalParameter("var");
//            if (localParameter != null) {
//                ve.addProperty(localParameter.toString(), inItem);
//            }
//            logger.log(Level.INFO, "application du template " + templateUrl + " avec en item courant " + inItem.getName() + " dans le fichier " + targetFile);
//            logger.log(Level.INFO, "working directory : " + new File(".").getAbsolutePath());
//
//            ve.mergeTemplate(templateUrl, templateEncoding, context, writer);
//            writer.flush();
//            writer.close();
//            System.out.println("Template : " + writer);
////            StringWriter    out = new StringWriter();
////            ve.evaluate(context, out, templateUrl, templateUrl);
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseErrorException ex) {
//            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MethodInvocationException ex) {
//            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                input.close();
//            } catch (IOException ex) {
//                Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }       // finally
//    }       // applyTemplate



    @Override
    protected void endLoop() {
        super.endLoop();
    }



    private VelocityEngine ve;
    private VelocityContext context;


}
