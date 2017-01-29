/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.mofpackage.impl;

import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.impl.NamedElementImpl;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.ete.meta.model.types.impl.TypedElementImpl;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author jldeleage
 */
public class MofPackageImpl extends PackageableElementImpl implements MofPackage {

    public MofPackageImpl() throws EteException {
        try {
            this.packagedElement = FactoryMethods.newSet(PackageableElement.class);
        } catch (InstantiationException ex) {
            Logger.getLogger(MofPackageImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }

    //========================================================================//
    @Override
    public Collection<PackageableElement> getPackagedElementAsCollection() {
        return packagedElement;
    }

    @Override
    public void addPackagedElement(PackageableElement inPackageableElement) {
        packagedElement.add(inPackageableElement);
    }

    @Override
    public void removePackagedElement(PackageableElement inPackageableElement) {
        packagedElement.remove(inPackageableElement);
    }

    @Override
    public PackageableElement getElementByName(String inName) {
        for (PackageableElement element : getPackagedElementAsCollection()) {
            if (inName.equals(element.getName())) {
                return element;
            }
        }
        return null;
    }

    //========================================================================//
    @Override
    public Stream<PackageableElement> getPackagedElement() {
        return packagedElement.stream();
    }

    //========================================================================//
    private Collection<PackageableElement> packagedElement;

}
