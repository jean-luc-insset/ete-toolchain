/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.constraint.Postcondition;
import fr.insset.jeanluc.ete.meta.model.constraint.Precondition;
import fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Parameter;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.impl.TypedElementImpl;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jldeleage
 */
public class OperationImpl extends TypedElementImpl implements Operation {

    public OperationImpl() throws InstantiationException {
        this.ownedParameter = FactoryMethods.newList(Parameter.class);
        this.raisedException = FactoryMethods.newSet(MofType.class);
        this.preconditions = FactoryMethods.newList(Precondition.class);
        this.postconditions = FactoryMethods.newList(Postcondition.class);
    }

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
    public void setLower(int inLower) {
        lower = inLower;
    }

    @Override
    public void setUpper(UnlimitedNatural inUpper) {
        upper = inUpper;
    }

    @Override
    public void setUpper(int inUpper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnlimitedNatural getUpper() {
        return upper;
    }

    @Override
    public Collection<Precondition> getPreconditions() {
        return preconditions;
    }

    @Override
    public void setPreconditions(Collection<Precondition> inPreconditions) {
        preconditions = inPreconditions;
    }

    @Override
    public void addPrecondition(Precondition inPrecondition) {
        preconditions.add(inPrecondition);
    }

    @Override
    public Collection<Postcondition> getPostconditions() {
        return postconditions;
    }

    @Override
    public void setPostconditions(Collection<Postcondition> inPostconditions) {
        postconditions = inPostconditions;
    }

    @Override
    public void addPostcondition(Postcondition inPostcondition) {
        postconditions.add(inPostcondition);
    }

    private MofClass mofClass;
    private List<Parameter> ownedParameter;
    private Collection<MofType> raisedException;
    private boolean ordered;
    private boolean unique;
    private int lower;
    private UnlimitedNatural upper;

    private Collection<Precondition> preconditions;
    private Collection<Postcondition> postconditions;
}
