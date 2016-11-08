/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.mofpackage.impl;

import fr.insset.jeanluc.ete.meta.model.core.impl.NamedElementImpl;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 *
 * @author jldeleage
 */
public class MofPackageImpl extends NamedElementImpl implements MofPackage {



    public MofPackageImpl() throws InstantiationException {
        this.packagedElement = FactoryMethods.newList(PackageableElement.class);
    }


    //========================================================================//


    @Override
    public Collection<PackageableElement> getPackagedElement() {
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


    //========================================================================//


    @Override
    public Stream<PackageableElement> getPackagedElementAsStream() {
        return packagedElement.stream();
    }


    //========================================================================//


    private Collection<PackageableElement>     packagedElement;

}
