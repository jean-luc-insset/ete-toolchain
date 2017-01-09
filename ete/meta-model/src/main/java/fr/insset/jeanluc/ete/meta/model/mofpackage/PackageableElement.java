package fr.insset.jeanluc.ete.meta.model.mofpackage;

import fr.insset.jeanluc.ete.meta.model.types.TypedElement;

/**
 * diag. 12.4 EMOF Package, p.28
 *
 * @author jldeleage
 */
public interface PackageableElement extends TypedElement {

    public void                 setOwningPackage(MofPackage inPackage);
    public MofPackage           getOwningPackage();

    public default MofPackage   getPackage() {
        return getOwningPackage();
    }

    public default String getQualifiedName() {
        StringBuilder builder = new StringBuilder(getName());
        computeQualifiedName(builder);
        return builder.toString();
    }

    public default void computeQualifiedName(StringBuilder inoutBuilder) {
        MofPackage aPackage = getPackage();
        if (aPackage != null) {
            aPackage.computeQualifiedName(inoutBuilder);
            inoutBuilder.append("::");
        }
        inoutBuilder.append(getName());
    }

}
