package fr.insset.jeanluc.ete.meta.model.mofpackage;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import java.util.Collection;


/**
 * diag. 12.4 EMOF Package, p.28
 *
 * @author jldeleage
 */
public interface MofPackage extends NamedElement {
    
    public  Collection<PackageableElement>      getPackagedElement();
    public  void                                addPackagedElement(PackageableElement inPackageableElement);
    public  void                                removePackagedElement(PackageableElement inPackageableElement);


}
