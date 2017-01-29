/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural;
import fr.insset.jeanluc.ete.meta.model.emof.Parameter;
import fr.insset.jeanluc.ete.meta.model.emof.ParameterDirectionKind;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.impl.TypedElementImpl;
import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public class ParameterImpl extends TypedElementImpl implements Parameter {

    @Override
    public ParameterDirectionKind getDirection() {
        return direction;
    }

    @Override
    public void setDirection(ParameterDirectionKind inDirection) {
        direction = inDirection;
    }

    @Override
    public boolean isOrdered() {
        return ordered;
    }

    @Override
    public void setOrdered(boolean inOrdered) {
        ordered = inOrdered;
    }

    @Override
    public boolean isUnique() {
        return unique;
    }

    @Override
    public void setUnique(boolean inUnique) {
        unique = inUnique;
    }

    @Override
    public int getLower() {
        return lower;
    }

    @Override
    public void setLower(int inLower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpper(UnlimitedNatural inUpper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpper(int inUpper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnlimitedNatural getUpper() {
        return upper;
    }

    private ParameterDirectionKind direction;
    private boolean ordered;
    private boolean unique;
    private int lower;
    private UnlimitedNatural upper;

}
