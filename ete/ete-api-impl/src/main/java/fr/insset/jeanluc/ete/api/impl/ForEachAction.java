package fr.insset.jeanluc.ete.api.impl;


import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.ete.api.Action;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
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


    @Override
    public MofPackage processChildren(MofPackage inModel) throws EteException {
        Collection<NamedElement> evaluate = getItems(inModel);
        String varName = (String)getParameter("var");
        Object previousValue = getParameter(varName);
        initLoop();
        for (NamedElement obj : evaluate) {
            // We don't need to read the attributes again
            // the parameter "varName" is local and its value is updated
            // at each iteration
            addParameter(varName, obj);
            initIteration(obj);
            inModel = performAnIteration(inModel, obj);
            endIteration(obj);
        }
        endLoop();
        if (previousValue != null) {
            addParameter(varName, previousValue);
        }
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
            ELEvaluator elEvaluator = new ELEvaluator(inModel, getParameters());
            Collection evaluate = elEvaluator.evaluate(itemsExpression, Collection.class);
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

