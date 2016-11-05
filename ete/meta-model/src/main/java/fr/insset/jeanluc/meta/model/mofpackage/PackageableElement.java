package fr.insset.jeanluc.meta.model.mofpackage;

/**
 * diag. 12.4 EMOF Package, p.28
 *
 * @author jldeleage
 */
public interface PackageableElement {

    public void         setOwningPackage(MofPackage inPackage);
    public MofPackage   getOwningPackage();

}
