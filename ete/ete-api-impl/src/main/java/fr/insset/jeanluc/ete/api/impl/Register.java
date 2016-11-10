/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import static fr.insset.jeanluc.util.factory.FactoryRegistry.getRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Allow dynamic registration of action.<br>
 * A dynamically registered action can override another action already
 * registered.<br>
 * In turn, it can be overridden by a further registration.
 *
 * @author jldeleage
 */
public class Register extends ActionSupport {


    public static void  register(String inActionName, String inClassName) {
        try {
            Class<?> actionClass = Class.forName(inClassName);
            FactoryRegistry.getRegistry().registerFactory(inActionName  + "-action", actionClass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public MofPackage doProcess(MofPackage inModel) {
        String actionName = (String) getParameter("name");
        String className = (String) getParameter("action");
        register(actionName, className);
        return inModel;
    }
    


}
