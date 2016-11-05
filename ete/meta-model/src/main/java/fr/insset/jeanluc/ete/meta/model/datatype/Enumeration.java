/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.datatype;

import fr.insset.jeanluc.ete.meta.model.instance.EnumerationLiteral;
import java.util.List;


/**
 *
 * @author jldeleage
 */
public interface Enumeration extends DataType {
    
    public  List<EnumerationLiteral>    getOwnedLiteral();
    public  void                        addOwnedLiteral(EnumerationLiteral inEnumerationLiteral);
    public  void                        removeOwnedLiteral(EnumerationLiteral inEnumerationLiteral);

}
