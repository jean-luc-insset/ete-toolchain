package fr.insset.jeanluc.ete.meta.model.mofpackage;

import fr.insset.jeanluc.ete.meta.model.types.TypedElement;

/**
 * diag. 12.4 EMOF Package, p.28
 *
 * @author jldeleage
 */
public interface PackageableElement extends TypedElement {

    public void         setOwningPackage(MofPackage inPackage);
    public MofPackage   getOwningPackage();

}
