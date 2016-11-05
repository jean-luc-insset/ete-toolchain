/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.datatype.impl;

import fr.insset.jeanluc.ete.meta.model.datatype.DataType;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;

/**
 *
 * @author jldeleage
 */
public class DataTypeImpl extends MofTypeImpl implements DataType {

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public void setAbstract(boolean inAbstract) {
        isAbstract = inAbstract;
    }
    
    private     boolean     isAbstract;

}
