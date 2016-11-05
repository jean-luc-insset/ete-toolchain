/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.emof;

import fr.insset.jeanluc.meta.model.types.MofType;
import fr.insset.jeanluc.meta.model.types.TypedElement;
import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface Parameter extends MultiplicityElement, TypedElement {

    public  Collection<Parameter>       getOwnedParameter();
    public  void                        addOwnedParameter(Parameter inParameter);
    public  void                        removeOwnedParameter(Parameter inParameter);

    public  Collection<MofType>         getRaisedException();
    public  void                        addRaisedException(MofType inMofType);
    public  void                        removeRaisedException(MofType inMofType);

}
