/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.mofpackage.impl;

import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;

/**
 *
 * @author jldeleage
 */
public class EteModelImpl extends MofPackageImpl implements EteModel {

    public EteModelImpl() throws EteException {
    }


    @Override
    public EteModel getParent() {
        return parent;
    }


    @Override
    public void setParent(EteModel inParent) {
        parent = inParent;
    }

    private     EteModel        parent;

}
