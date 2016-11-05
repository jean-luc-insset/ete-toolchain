/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.emof;

import fr.insset.jeanluc.meta.model.types.Classifier;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jldeleage
 */
public interface MofClass extends Classifier {
    
    public  List<Property>          getOwnedAttribute();
    public  void                    addOwnedAttribute(Property inProperty);
    public  void                    removeOwnedAttribute(Property inProperty);

    public  List<Operation>         getOwnedOperation();
    public  void                    addOwnedOperation(Operation inOperation);
    public  void                    removeOwnedOperation(Operation inOperation);

    public  Collection<MofClass>    getSuperClass();
    public  void                    addSuperClass(MofClass inMofClass);
    public  void                    removeSuperClass(MofClass inMofClass);

}
