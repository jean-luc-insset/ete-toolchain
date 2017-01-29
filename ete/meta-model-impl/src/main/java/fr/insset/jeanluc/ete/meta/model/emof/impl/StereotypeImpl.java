/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.emof.impl;

import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.emof.Stereotype;
import fr.insset.jeanluc.ete.meta.model.emof.TagValueDeclaration;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.PackageableElementImpl;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public class StereotypeImpl extends PackageableElementImpl implements Stereotype {

    @Override
    public MofPackage getProfile() {
        return profile;
    }

    @Override
    public void setProfile(MofPackage inProfile) {
        profile = inProfile;
    }

    @Override
    public Collection<TagValueDeclaration> getTagValueDeclarations() {
        return tagValueDeclarations;
    }

    @Override
    public void setTagValueDeclarations(Collection<TagValueDeclaration> inDeclarations) {
        tagValueDeclarations = inDeclarations;
    }

    /**
     * TODO : should we throw an exception in case of inserting a tag value with
     * the same name than another one previously added&nbsp;?
     *
     * @param inDeclaration : the tag value declaration to add
     * @return inDeclaration or anoter instance with the same name and same
     * value type it such an instance has been previously added
     */
    @Override
    public TagValueDeclaration addTagValueDeclaration(TagValueDeclaration inDeclaration) {
        if (tagValueDeclarations == null) {
            try {
                tagValueDeclarations = FactoryMethods.newSet(TagValueDeclaration.class);
            } catch (InstantiationException ex) {
                // This should never happen but who knows ?
                Logger.getLogger(StereotypeImpl.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        TagValueDeclaration previousDeclaration = getTagValueDeclaration(inDeclaration.getName());
        if (previousDeclaration != null) {
            if (previousDeclaration.getValueType().equals(inDeclaration.getValueType())) {
                return previousDeclaration;
            }
            removeTagValueDeclaration(previousDeclaration);
        }
        tagValueDeclarations.add(inDeclaration);
        return inDeclaration;
    }

    @Override
    public void removeTagValueDeclaration(TagValueDeclaration inDeclaration) {
        tagValueDeclarations.remove(inDeclaration);
    }

    @Override
    public TagValueDeclaration getTagValueDeclaration(String inName) {
        for (TagValueDeclaration declaration : tagValueDeclarations) {
            if (declaration.getName().equals(inName)) {
                return declaration;
            }
        }
        return null;
    }

    private MofPackage profile;
    private Collection<TagValueDeclaration> tagValueDeclarations;

}
