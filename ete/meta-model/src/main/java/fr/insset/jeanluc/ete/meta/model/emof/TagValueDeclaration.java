/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof;

import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.types.MofType;

/**
 *
 * @author jldeleage
 */
public interface TagValueDeclaration extends NamedElement {
    
    public final static String      TAG_VALUE_DECLARATION = "tag_value_declaration";

    public  MofType     getValueType();
    public  void        setValueType(MofType inType);

}
