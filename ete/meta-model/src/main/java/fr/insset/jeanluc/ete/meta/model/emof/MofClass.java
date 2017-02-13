package fr.insset.jeanluc.ete.meta.model.emof;



import fr.insset.jeanluc.ete.meta.model.constraint.Invariant;
import fr.insset.jeanluc.ete.meta.model.types.Classifier;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 *
 * @author jldeleage
 */
public interface MofClass extends Classifier {
    

    public final static String              MOF_CLASS = "mof-class";


    public  default List<Property>          getOwnedAttribute() {
        return getOwnedAttributeAsStream().collect(Collectors.toList());
    }
    public  void                            addOwnedAttribute(Property inProperty);
    public  void                            removeOwnedAttribute(Property inProperty);
    public  Property                        getOwnedAttribute(String inName);
    public  Stream<Property>                getOwnedAttributeAsStream();

    public  default List<Operation>         getOwnedOperation() {
        return getOwnedOperationAsStream().collect(Collectors.toList());
    }
    public  void                            addOwnedOperation(Operation inOperation);
    public  void                            removeOwnedOperation(Operation inOperation);
    public  Operation                       getOwnedOperation(String inName);
    public  Stream<Operation>               getOwnedOperationAsStream();

    public  default Collection<MofClass>    getSuperClass() {
        return getSuperClassAsStream().collect(Collectors.toList());
    }
    public  default void                    addSuperClass(MofClass inMofClass) {
        addSuperType(inMofClass);
    }

    public  default Stream<MofClass>        getSuperClassAsStream() {
        return getSuperTypes().stream().map(t -> (MofClass)t);
    }

    public  Collection<Invariant>           getInvariants();
    public  void                            setInvariants(Collection<Invariant> inInvariants);
    public  void                            addInvariant(Invariant inInvariant);

    public  boolean                         isAbstract();
    public  void                            setAbstract(boolean inAbstract);

    @Override
    public default  boolean                 isPrimitive() {
        return false;
    }


}
