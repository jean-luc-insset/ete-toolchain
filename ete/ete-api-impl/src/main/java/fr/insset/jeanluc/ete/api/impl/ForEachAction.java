package fr.insset.jeanluc.ete.api.impl;


//import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.ete.api.Action;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author jldeleage
 */
public class ForEachAction extends ActionSupport {


    public  final static String     FOR_EACH_ACTION = "for-each-action";
    public  final static String     FOREACH_ACTION  = "foreach-action";


    @Override
    public MofPackage processChildren(MofPackage inModel) throws EteException {
        Collection<NamedElement> items = getItems(inModel);
        String varName = (String)getParameter("var");
        if (varName == null) {
            varName = "current";
            addParameter("var", varName);
        }
        Logger global = Logger.getGlobal();
        initLoop();
        for (NamedElement obj : items) {
            // We don't need to read the attributes again
            // the parameter "varName" is local and its value is updated
            // at each iteration
            global.log(Level.INFO, "Adding " + varName + " parameter with value " + obj);
            addParameter(varName, obj);
            initIteration(obj);
            inModel = performAnIteration(inModel, obj);
            endIteration(obj);
        }
        endLoop();
        return inModel;
    }       // process



    protected   Collection<NamedElement>    getItems(MofPackage inModel) throws EteException {
        try {
            String itemsExpression = (String)getParameter("items");
            if (itemsExpression == null) {
                List<NamedElement> items = FactoryMethods.newList(NamedElement.class);
                items.add(inModel);
                return items;
            }
//            ELEvaluator elEvaluator = new ELEvaluator(inModel, getParameters());
            ELEvaluator elEvaluator = new ELEvaluator(inModel);
            Collection evaluate = (Collection)elEvaluator.evaluate(itemsExpression);
            return evaluate;
        } catch (InstantiationException ex) {
            Logger.getLogger(ForEachAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }


    protected   void initLoop() {
    }


    protected   void initIteration(NamedElement inElement) {

    }


    protected   MofPackage performAnIteration(MofPackage inPackage, NamedElement inElement) throws EteException {
        for (Action child : getChildren()) {
            inPackage = child.process(inPackage);
        }
        return inPackage;
    }

    protected   void endIteration(NamedElement inElement) {
        
    }

    protected void endLoop() {
        
    }

}       // ForEach

