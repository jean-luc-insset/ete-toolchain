/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.velocity;

import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.collections.MofCollection;
import fr.insset.jeanluc.ete.meta.model.types.collections.MofSet;

/**
 *
 * @author jldeleage
 */
public class Util {

    public final static String  toBeanSuffix(String inName) {
        return inName.substring(0,1) + inName.substring(1);
    }

    public final static String  getTypeName(MofType inType) {
        if (inType instanceof MofCollection) {
            MofCollection coll = (MofCollection) inType;
            if (inType instanceof MofSet) {
                return "Set<" + getTypeName(coll.getBaseType()) + ">";
            } else {
                return "List    <" + getTypeName(coll.getBaseType()) + ">";
            }
        }
        return inType.getName();
    }

}
