/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allow dynamic registration of action.<br>
 * A dynamically registered action can override another action already registered.<br>
 * In turn, it can be overridden by a further registration.
 *
 * @author jldeleage
 */
public class Register extends ActionSupport {

    @Override
    public EteModel doProcess(EteModel inModel) {
        String actionName = (String) getParameter("name");
        String className = (String) getParameter("action");
        try {
            Class<?> actionClass = Class.forName(className);
            FactoryRegistry.getRegistry().registerFactory(actionName, actionClass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inModel;
    }
    


}
