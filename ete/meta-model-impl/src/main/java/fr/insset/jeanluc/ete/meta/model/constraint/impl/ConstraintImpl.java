/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.constraint.impl;

import fr.insset.jeanluc.ete.meta.model.constraint.Constraint;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.core.impl.NamedElementImpl;

/**
 *
 * @author jldeleage
 */
public class ConstraintImpl extends NamedElementImpl implements Constraint {

    @Override
    public NamedElement getContext() {
        return context;
    }

    @Override
    public void setContext(NamedElement inContext) {
        context = inContext;
    }


    @Override
    public String getSpecification() {
        return specification;
    }

    @Override
    public void setSpecification(String inSpecification) {
        specification = inSpecification;
    }


    private     NamedElement        context;
    private     String              specification;

}
