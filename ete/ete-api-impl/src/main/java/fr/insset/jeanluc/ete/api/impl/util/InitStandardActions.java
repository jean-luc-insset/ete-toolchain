/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl.util;

import fr.insset.jeanluc.ete.api.impl.Register;

/**
 *
 * @author jldeleage
 */
public class InitStandardActions {

    /**
     * Registers standard actions
     */
    public static void init() {
        Register.register("dump", "fr.insset.jeanluc.ete.api.impl.Dump");
        Register.register("for-each", "fr.insset.jeanluc.ete.api.impl.ForEach");
        Register.register("if", "fr.insset.jeanluc.ete.api.impl.If");
        Register.register("model", "fr.insset.jeanluc.ete.api.impl.Model");
        Register.register("module", "fr.insset.jeanluc.ete.api.impl.ModuleCall");
        Register.register("transformation-set", "fr.insset.jeanluc.ete.api.impl.Module");
        Register.register("mda", "fr.insset.jeanluc.ete.api.impl.Processor");
        Register.register("velocity", "fr.insset.jeanluc.ete.api.impl.Velocity");

    }

}
