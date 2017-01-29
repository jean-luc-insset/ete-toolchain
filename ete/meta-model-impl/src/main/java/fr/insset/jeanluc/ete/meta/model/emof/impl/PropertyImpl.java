/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural;
import static fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural.UNLIMITED_NATURAL;
import fr.insset.jeanluc.ete.meta.model.emof.AggregationKind;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.types.impl.TypedElementImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public class PropertyImpl extends TypedElementImpl implements Property {

    @Override
    public AggregationKind getAggregationKind() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAggregationKind(AggregationKind inAggregationKind) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> getDefault() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isComposite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDerived() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDerived(boolean inDerived) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //========================================================================//
    @Override
    public Association getOwningAssociation() {
        return owningAssociation;
    }

    @Override
    public void setOwningAssociation(Association inAssociation) {
        owningAssociation = inAssociation;
    }

    @Override
    public Association getAssociation() {
        return association;
    }

    @Override
    public void setAssociation(Association inAssociation) {
        association = inAssociation;
    }

    @Override
    public Property getOpposite() {
        return opposite;
    }

    @Override
    public void setOpposite(Property inOpposite) {
        if (inOpposite == opposite) {
            return;
        }
        opposite = inOpposite;
        inOpposite.setOpposite(this);
    }

    //========================================================================//
    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(boolean inReadOnly) {
        readOnly = inReadOnly;
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

    //========================================================================//
    @Override
    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    @Override
    public UnlimitedNatural getUpper() {
        return upper;
    }

    public void setUpper(UnlimitedNatural upper) {
        this.upper = upper;
    }

    @Override
    public void setUpper(int inUpper) {
        try {
            UnlimitedNatural upper = (UnlimitedNatural) FactoryRegistry.newInstance(UNLIMITED_NATURAL);
            upper.setValue("" + inUpper);
            setUpper(upper);
        } catch (InstantiationException ex) {
            Logger.getLogger(PropertyImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //========================================================================//
    private Association association;
    private Association owningAssociation;
    private Property opposite;
    private boolean readOnly;
    private boolean ordered;
    private boolean unique;
    private int lower;
    private UnlimitedNatural upper;
}
