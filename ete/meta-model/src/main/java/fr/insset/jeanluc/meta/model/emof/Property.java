/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.emof;

import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface Property extends StructuralFeature {

    public  AggregationKind         getAggregationKind();
    public  void                    setAggregationKind(AggregationKind inAggregationKind);

    public  Collection<String>      getDefault();

    public  boolean                 isComposite();

    public  boolean                 isDerived();
    public  void                    setDerived(boolean inDerived);

    public  Collection<Association> getOwningAssociation();
    public  void                    addOwningAssociation(Association inAssociation);
    public  void                    removeOwningAssociation(Association inAssociation);

    public  Collection<Association> getAssociation();
    public  void                    addAssociation(Association inAssociation);
    public  void                    removeAssociation(Association inAssociation);

    public  Property                getOpposite();
    public  void                    setOpposite(Property inOpposite);
    
}
