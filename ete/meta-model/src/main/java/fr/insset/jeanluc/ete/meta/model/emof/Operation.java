/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof;

import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.TypedElement;
import java.util.Collection;

/**
 * WARNING : in Mof, an operation is not a typed element. In Ete, it is.
 *
 * @author jldeleage
 */
public interface Operation extends NamedElement, MultiplicityElement, TypedElement {


    public final static String      OPERATION = "operation";


    public  MofClass                getMofClass();
    public  void                    setMofClass(MofClass inMofClass);

    public  Collection<Parameter>   getOwnedParameter();
    public  void                    addOwnedParameter(Parameter inParameter);
    public  void                    removeOwnedParameter(Parameter inParameter);

    public  Collection<MofType>     getRaisedException();
    public  void                    addRaisedException(MofType inMofType);
    public  void                    removeRaisedException(MofType inMofType);

}
