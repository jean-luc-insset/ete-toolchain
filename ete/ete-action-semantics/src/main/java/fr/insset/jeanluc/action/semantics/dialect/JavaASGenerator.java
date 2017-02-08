/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.action.semantics.dialect;


import  fr.insset.jeanluc.ete.as.api.Assignment;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;

/**
 *
 * @author jldeleage
 */
public class JavaASGenerator extends DynamicVisitorSupport {

    public JavaASGenerator() {
        register("visit", "fr.insset.jeanluc.ete.as.api");
    }
    
    public Object visitAssignment(Assignment x, Object... params){
        return x;
    }


}
