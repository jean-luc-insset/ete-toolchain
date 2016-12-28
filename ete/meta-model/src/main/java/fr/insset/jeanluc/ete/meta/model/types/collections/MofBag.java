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
public interface MofBag extends MofCollection {

    public final static String      MOF_BAG = "mof-bag";

    @Override
    public default boolean isOrdered() {
        return false;
    }

    @Override
    public default boolean isDistinct() {
        return true;
    }

}
