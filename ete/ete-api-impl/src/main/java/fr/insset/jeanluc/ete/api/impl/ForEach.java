package fr.insset.jeanluc.ete.api.impl;


import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.util.Collection;



/**
 *
 * @author jldeleage
 */
public class ForEach extends ActionSupport {


    @Override
    public MofPackage doProcess(MofPackage inModel) {
        String itemsExpression = (String)getParameter("items");
        String varName = (String)getParameter("var");
        MofPackage aux = inModel;
//        ELEvaluator elEvaluator = new ELEvaluator();
//        Collection evaluate = elEvaluator.evaluate(itemsExpression, Collection.class);
//        for (Object obj : evaluate) {
//            // We don't need to read the attributes again
//            // the parameter "varName" is local and its value is updated
//            // at each iteration
//            addParameter(varName, obj);
//            aux = preProcess(aux);
//            aux = process(aux);
//            aux = postProcess(aux);
//        }
//        return aux;
        return aux;
    }       // process


}       // ForEach

