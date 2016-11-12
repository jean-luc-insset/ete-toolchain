package fr.insset.jeanluc.ete.api.impl;


import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.util.Collection;



/**
 *
 * @author jldeleage
 */
public class ForEach extends ActionSupport {


    @Override
    public MofPackage doProcess(MofPackage inModel) throws EteException {
        Collection<NamedElement> evaluate = getItems(inModel);
        MofPackage aux = inModel;
        String varName = (String)getParameter("var");
        initLoop();
        for (NamedElement obj : evaluate) {
            // We don't need to read the attributes again
            // the parameter "varName" is local and its value is updated
            // at each iteration
            addParameter(varName, obj);
            initIteration(obj);
            processAnItem(inModel, obj);
        }
        endLoop();
        return aux;
    }       // process



    protected   Collection<NamedElement>    getItems(MofPackage inModel) {
        String itemsExpression = (String)getParameter("items");
        ELEvaluator elEvaluator = new ELEvaluator(inModel, getParameters());
        Collection evaluate = elEvaluator.evaluate(itemsExpression, Collection.class);
        return evaluate;
    }


    protected   void initLoop() {
        
    }


    protected   void initIteration(NamedElement inElement) {
        
    }



    protected   MofPackage  processAnItem(MofPackage inModel, NamedElement elt) {
        return inModel;
    }


    protected void endLoop() {
        
    }

}       // ForEach

