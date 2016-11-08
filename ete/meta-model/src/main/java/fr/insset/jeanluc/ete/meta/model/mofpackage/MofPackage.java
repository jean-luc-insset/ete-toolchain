package fr.insset.jeanluc.ete.meta.model.mofpackage;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * diag. 12.4 EMOF Package, p.28
 *
 * @author jldeleage
 */
public interface MofPackage extends NamedElement {
    
    public  Collection<PackageableElement>          getPackagedElement();
    public  void                                    addPackagedElement(PackageableElement inPackageableElement);
    public  void                                    removePackagedElement(PackageableElement inPackageableElement);

    public  default Collection<PackageableElement>  getPackages() {
        return getPackagesAsStream().collect(Collectors.toList());
    }
    public  default Collection<PackageableElement>  getClasses() {
        return getClassesAsStream().collect(Collectors.toList());
    }

    public  Stream<PackageableElement>              getPackagedElementAsStream();
    public  default Stream<PackageableElement>      getPackagesAsStream() {
        return getPackagedElementAsStream().filter(p -> p instanceof MofPackage);
    }
    public  default Stream<PackageableElement>      getClassesAsStream() {
        return getPackagedElementAsStream().filter(c -> c instanceof MofClass);
    }

}
