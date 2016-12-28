/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.types.collections;

/**
 *
 * @author jldeleage
 */
public interface MofSet extends MofCollection {

    public final static String      MOF_SET = "mof-set";
    
    @Override
    public default boolean isDistinct() {
        return false;
    }

    public default boolean isOrdered() {
        return false;
    }

}
