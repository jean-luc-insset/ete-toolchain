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
    public  default PackageableElement              getElementByName(String inName)  {
        for (PackageableElement element : getPackagedElementAsCollection()) {
            if (inName.equals(element.getName())) {
                return element;
            }
        }
        return null;
    }

    public  default Collection<MofPackage>          getPackages() {
        return getPackagesAsStream().collect(Collectors.toList());
    }
    public  default Collection<MofClass>            getClasses() {
        return getClassesAsStream().collect(Collectors.toList());
    }
    public  default Collection<MofClass>            getAllClasses() {
        return getAllClassesAsStream().collect(Collectors.toList());
    }


    public  Stream<PackageableElement>              getPackagedElement();
    public  default Stream<MofPackage>              getPackagesAsStream() {
        return getPackagedElement()
                    .filter(p -> p instanceof MofPackage).map(p -> (MofPackage)p);
    }
    public  default Stream<MofClass>                getClassesAsStream() {
        return getPackagedElement()
                    .filter(c -> c instanceof MofClass).map(p -> (MofClass)p);
    }

    public  default Stream<MofClass>                getAllClassesAsStream() {
        return Stream.concat(getClassesAsStream(),
                getPackagesAsStream()
                    .flatMap(f -> ((MofPackage)f).getAllClassesAsStream()));
    }

}
