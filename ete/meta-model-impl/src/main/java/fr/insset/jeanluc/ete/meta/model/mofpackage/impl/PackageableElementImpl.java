package fr.insset.jeanluc.ete.meta.model.mofpackage.impl;



import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.ete.meta.model.types.impl.TypedElementImpl;



/**
 *
 * @author jldeleage
 */
public class PackageableElementImpl extends TypedElementImpl implements PackageableElement {


    @Override
    public void setOwningPackage(MofPackage inPackage) {
        MofPackage currentOwningPackage = getOwningPackage();
        if (currentOwningPackage == inPackage) {
            return;
        }
        if (currentOwningPackage != null) {
            currentOwningPackage.removePackagedElement(this);
        }
        owningPackage = inPackage;
        if (inPackage != null) {
            inPackage.addPackagedElement(this);
        }
    }


    @Override
    public MofPackage getOwningPackage() {
        return owningPackage;
    }


    private     MofPackage      owningPackage;


}
