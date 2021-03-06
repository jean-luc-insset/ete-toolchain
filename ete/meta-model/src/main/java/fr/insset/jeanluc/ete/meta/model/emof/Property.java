/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof;

import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface Property extends StructuralFeature {


    public final static String      PROPERTY = "property";


    public  AggregationKind         getAggregationKind();
    public  void                    setAggregationKind(AggregationKind inAggregationKind);

    public  Collection<String>      getDefault();

    public  boolean                 isComposite();

    public  boolean                 isDerived();
    public  void                    setDerived(boolean inDerived);

    public  Association             getOwningAssociation();
    public  void                    setOwningAssociation(Association inAssociation);

    public  Association             getAssociation();
    public  void                    setAssociation(Association inAssociation);

    public  Property                getOpposite();
    public  void                    setOpposite(Property inOpposite);
    
}
