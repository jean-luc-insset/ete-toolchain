package fr.insset.jeanluc.ete.meta.model.types;


import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface MofType extends PackageableElement {

    public final static String      MOF_TYPE = "mof-type";

    public Collection<MofType>      getSuperTypes();
    public void                     setSuperTypes(Collection<MofType> inSuperTypes);
    public void                     addSuperType(MofType inSuperType);

    public default boolean          isCollection() {
        return false;
    }

    public default boolean          isPrimitive() {
        return true;
    }

}
