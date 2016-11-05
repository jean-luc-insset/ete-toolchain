/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.types.impl;

import fr.insset.jeanluc.ete.meta.model.types.Classifier;
import fr.insset.jeanluc.ete.meta.model.types.Generalization;

/**
 *
 * @author jldeleage
 */
public class GeneralizationImpl implements Generalization {

    @Override
    public Classifier getGeneral() {
        return general;
    }

    @Override
    public void setGeneral(Classifier inGeneral) {
        general = inGeneral;
    }

    @Override
    public Classifier getSpecific() {
        return specific;
    }

    @Override
    public void setSpecific(Classifier inSpecific) {
        specific = inSpecific;
    }
    
    private     Classifier      general;
    private     Classifier      specific;

}
