/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.constraint;

import fr.insset.jeanluc.ete.meta.model.emof.MofClass;

/**
 *
 * @author jldeleage
 */
public interface Invariant extends Constraint {


    public final static String      INVARIANT = "invariant";


    @Override
    public MofClass getContext();

}
