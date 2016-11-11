/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof;

import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.TypedElement;
import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface Parameter extends MultiplicityElement, TypedElement {


    public final static String  PARAMETER = "parameter";


    public  ParameterDirectionKind  getDirection();
    public  void                    setDirection(ParameterDirectionKind inDirection);

}
