/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.types;

/**
 *
 * @author jldeleage
 */
public interface Classifier extends MofType {
    
    public  boolean     isAbstract();
    public  void        setAbstract(boolean inBoolean);

}
