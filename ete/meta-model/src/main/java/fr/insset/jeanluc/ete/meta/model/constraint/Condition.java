/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.constraint;

import fr.insset.jeanluc.ete.meta.model.emof.Operation;

/**
 *
 * @author jldeleage
 */
public interface Condition extends Constraint {


    @Override
    public Operation    getContext();

}
