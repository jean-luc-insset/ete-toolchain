/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.emof;

/**
 *
 * @author jldeleage
 */
public interface Operation extends MultiplicityElement {
    
    public  MofClass        getMofClass();
    public  void            setMofClass(MofClass inMofClass);

}
