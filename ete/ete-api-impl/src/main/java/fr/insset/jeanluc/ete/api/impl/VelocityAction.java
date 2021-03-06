package fr.insset.jeanluc.ete.api.impl;



import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import static fr.insset.jeanluc.ete.api.impl.GenericTemplate.TEMPLATE;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.runtime.RuntimeConstants;



/**
 * A velocity template is applied to a single item or to each item in a
 * collection.<br>
 * The life cycle is :<pre>
 * initLoop
 *      (
 *      initIteration
 *      applyTemplate
 *      )*
 * endLoop</pre>
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
        ve.setProperty("runtime.log", "target/velocity.log");
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, getBaseUrl());
        Logger.getGlobal().log(Level.INFO, "Velocity template root : " + getBaseUrl());
        try {
            ve.init();
        } catch (Exception ex) {
            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        context = new VelocityContext();
        context.put("model", model);
        context.put("classes",model.getClasses());
        context.put("packages", model.getPackages());
        String dialectName = (String)getParameter("dialect");

        for (Map.Entry<String,Object> entry : getParameters().entrySet()) {
            logger.log(Level.FINE, "Passing parameter " + entry.getKey() + " = " + entry.getValue());
            context.put(entry.getKey(), entry.getValue());
        }
        if (dialectName == null) {
            dialectName = "fr.insset.jeanluc.el.dialect.BasicJavaDialect";
        }
        try {
            Class<?> dialectClass = Class.forName(dialectName);
            Object dialect = dialectClass.newInstance();
            context.put("_d", dialect);
            context.put("dialect", dialect);
            Logger.getGlobal().log(Level.INFO, "Adding dialect " + dialect);
            Logger.getGlobal().log(Level.INFO, "Added dialect " + context.get("dialect"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, "Unable to load dialect " + dialectName, ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(VelocityAction.class.getName()).log(Level.SEVERE, "Unable to instanciate dialect " + dialectName, ex);
        }

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
        ELEvaluator elEvaluator = new ELEvaluator(getModel(), getAllParameters());
        String evaluate = (String) elEvaluator.evaluate(result);
        return evaluate;
    }


    @Override
    protected void applyTemplate(String inTemplateUrl, String inTemplateEncoding, Writer inoutOutput) {
        try {
            Object[] keys = context.getKeys();
            Logger global = Logger.getGlobal();
            global.log(Level.INFO, "----------------------------------------");
            for (Object aKey : keys) {
                global.log(Level.INFO, aKey + " -> " + context.get(aKey.toString()));
            }
            global.log(Level.INFO, "----------------------------------------");
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



    @Override
    protected void endLoop() {
        super.endLoop();
    }



    private VelocityEngine ve;
    private VelocityContext context;


}
