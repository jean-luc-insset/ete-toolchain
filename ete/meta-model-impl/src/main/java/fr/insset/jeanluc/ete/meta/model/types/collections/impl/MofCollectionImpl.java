/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.types.collections.impl;

import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.collections.MofCollection;
import fr.insset.jeanluc.ete.meta.model.types.impl.MofTypeImpl;
import java.util.Objects;

/**
 *
 * @author jldeleage
 */
public abstract class MofCollectionImpl extends MofTypeImpl implements MofCollection {

    @Override
    public MofType getBaseType() {
        return baseType;
    }

    @Override
    public void setBaseType(MofType inType) {
        baseType = inType;
    }

 
   @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.getBaseType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MofCollectionImpl other = (MofCollectionImpl) obj;
        if (!Objects.equals(this.baseType, other.baseType)) {
            return false;
        }
        return true;
    }

    private     MofType     baseType;
    
}
