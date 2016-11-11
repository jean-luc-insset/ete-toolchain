package fr.insset.jeanluc.ete.api.impl;


import fr.insset.jeanluc.el.evaluator.ELEvaluator;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;


/**
 *
 * @author jldeleage
 */
public class If extends ActionSupport {


    @Override
    public boolean shouldIProcess(MofPackage inModel) {
        ELEvaluator evaluateur = new ELEvaluator(inModel, getParameters());
        String test = (String) getParameter("test");
        return evaluateur.evaluateBoolean(test);
    }       // process

}       // If

