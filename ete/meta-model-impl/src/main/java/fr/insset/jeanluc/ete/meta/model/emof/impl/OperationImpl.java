/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Parameter;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jldeleage
 */
public class OperationImpl implements Operation {


    @Override
    public MofClass getMofClass() {
        return mofClass;
    }

    @Override
    public void setMofClass(MofClass inMofClass) {
        mofClass = inMofClass;
    }



    @Override
    public Collection<Parameter> getOwnedParameter() {
        return ownedParameter;
    }

    @Override
    public void addOwnedParameter(Parameter inParameter) {
        ownedParameter.add(inParameter);
    }

    @Override
    public void removeOwnedParameter(Parameter inParameter) {
        ownedParameter.remove(inParameter);
    }



    @Override
    public Collection<MofType> getRaisedException() {
        return raisedException;
    }

    @Override
    public void addRaisedException(MofType inMofType) {
        raisedException.add(inMofType);
    }

    @Override
    public void removeRaisedException(MofType inMofType) {
        raisedException.remove(inMofType);
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
    public UnlimitedNatural getUpper() {
        return upper;
    }



    private     MofClass            mofClass;
    private     List<Parameter>     ownedParameter  = new LinkedList<>();
    private     Collection<MofType> raisedException = new LinkedList<>();
    private     boolean             ordered;
    private     boolean             unique;
    private     int                 lower;
    private     UnlimitedNatural    upper;

}
