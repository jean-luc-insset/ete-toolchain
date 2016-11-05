/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jldeleage
 */
public class MofClassImpl extends MofTypeImpl implements MofClass {

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
    public boolean isAbstract() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAbstract(boolean inBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MofType getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setType(MofType inType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    List<Property>          ownedAttribute  = new LinkedList<>();
    List<Operation>         ownedOperation  = new LinkedList<>();
    Collection<MofClass>    superClass      = new LinkedList<>();

}
