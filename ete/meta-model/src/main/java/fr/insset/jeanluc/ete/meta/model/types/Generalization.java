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
public interface Generalization {


    public final static String      GENERALIZATION  = "generalization";


    public  Classifier  getGeneral();
    public  void        setGeneral(Classifier inGeneral);


    public  Classifier  getSpecific();
    public  void        setSpecific(Classifier inGeneral);


}
