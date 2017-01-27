package fr.insset.jeanluc.ete.api.impl;


import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;


/**
 *
 * @author jldeleage
 */
public class IfAction extends ActionSupport {


    public final static String  IF_ACTION = "if-action";


    @Override
    public boolean shouldIProcess(MofPackage inModel) {
        ELEvaluator evaluateur = new ELEvaluator(inModel, getParameters());
        String test = (String) getParameter("test");
        return (Boolean)evaluateur.evaluate(test);
    }       // process

}       // If

