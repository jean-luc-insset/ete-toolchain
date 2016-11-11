package fr.insset.jeanluc.ete.meta.model.mofpackage;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * diag. 12.4 EMOF Package, p.28
 *
 * @author jldeleage
 */
public interface MofPackage extends PackageableElement {


    public final static String      PACKAGE     = "mof-package";


    public  Collection<PackageableElement>          getPackagedElementAsCollection();
    public  void                                    addPackagedElement(PackageableElement inPackageableElement);
    public  void                                    removePackagedElement(PackageableElement inPackageableElement);

    public  default Collection<PackageableElement>  getPackagesAsCollection() {
        return getPackages().collect(Collectors.toList());
    }
    public  default Collection<PackageableElement>  getClassesAsCollection() {
        return getClasses().collect(Collectors.toList());
    }

    public  Stream<PackageableElement>              getPackagedElement();
    public  default Stream<PackageableElement>      getPackages() {
        return getPackagedElement().filter(p -> p instanceof MofPackage);
    }
    public  default Stream<PackageableElement>      getClasses() {
        return getPackagedElement().filter(c -> c instanceof MofClass);
    }

    public  default Stream<PackageableElement>      getAllClasses() {
        return Stream.concat(// local classes
getClasses(),
                // plus descendant classes
getPackages().flatMap(f -> ((MofPackage)f).getAllClasses()));
    }

}
