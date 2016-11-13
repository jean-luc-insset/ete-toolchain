/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author jldeleage
 */
public class MofClassImpl extends MofTypeImpl implements MofClass {



    public MofClassImpl() throws EteException {
        try {
            this.superClass = FactoryMethods.newList(MofClass.class);
            this.ownedOperation = FactoryMethods.newList(Operation.class);
            this.ownedAttribute = FactoryMethods.newList(Property.class);
        } catch (InstantiationException ex) {
            Logger.getLogger(MofClassImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }


    @Override
    public List<Property> getOwnedAttribute() {
        return ownedAttribute;
    }


    @Override
    public void addOwnedAttribute(Property inProperty) {
        ownedAttribute.add(inProperty);
    }


    @Override
    public void removeOwnedAttribute(Property inProperty) {
        ownedAttribute.remove(inProperty);
    }


    @Override
    public Stream<Property> getOwnedAttributeAsStream() {
        return ownedAttribute.stream();
    }



    @Override
    public List<Operation> getOwnedOperation() {
        return ownedOperation;
    }

    @Override
    public void addOwnedOperation(Operation inOperation) {
        ownedOperation.add(inOperation);
    }

    @Override
    public void removeOwnedOperation(Operation inOperation) {
        ownedOperation.remove(inOperation);
    }

    @Override
    public Stream<Operation> getOwnedOperationAsStream() {
        return ownedOperation.stream();
    }



    @Override
    public Collection<MofClass> getSuperClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSuperClass(MofClass inMofClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSuperClass(MofClass inMofClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Stream<MofClass> getSuperClassAsStream() {
        return superClass.stream();
    }




    @Override
    public boolean isAbstract() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAbstract(boolean inBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    
    List<Property>          ownedAttribute;
    List<Operation>         ownedOperation;
    Collection<MofClass>    superClass;

}
