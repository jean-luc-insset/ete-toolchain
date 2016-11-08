package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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


//    @Override
//    protected void initEngine() {
//        Logger  logger = Logger.getGlobal();
//        logger.log(Level.INFO, "initialisation de Velocity");
//        EteModel    model = getModel();
//        ve = new VelocityEngine();
//        try {
//            ve.init();
//        } catch (Exception ex) {
//            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        context = new VelocityContext();
//        ExtendedProperties eprops = new ExtendedProperties();
//        eprops.put("model", model);
//        context.put("model", model);
//        for (Map.Entry<String,Object> entry : getParameters().entrySet()) {
//            logger.log(Level.FINE, "Passage du parametre " + entry.getKey() + " = " + entry.getValue());
//            context.put(entry.getKey(), entry.getValue());
//        }
//
//        String  outputBase = (String) getParameter("output-base");
//        String racine = outputBase == null ? "." : outputBase;
//        if (racine.length() > 0 && ! racine.endsWith("/")) {
//            racine += "/";
//        }
//        logger.log(Level.INFO, "   velocity.output-base : " + racine);
//
//        Map<Object,Object> variables = new HashMap<>();
//        variables.put("model", model);
//        variables.put("classes",model.getClassesAsMap());
//        variables.put("packages", model.getPackagesAsMap());
//    }


    /**
     * Called before each generation.
     * 
     * @param inElement 
     */
    @Override
    protected void initGeneration(NamedElement inElement) {
        super.initGeneration(inElement);
        Logger.getGlobal().log(Level.INFO, "    velocity : initGeneration");
    }


    @Override
    protected void copyAParameter(String inKey, Object inValue) {
        ve.addProperty(inKey, inValue);
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
    protected void closeEngine() {
        super.closeEngine();
    }



    private VelocityEngine ve;
    private VelocityContext context;


}
