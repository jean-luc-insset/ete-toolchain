package fr.insset.jeanluc.ete.meta.model.types.collections;


import fr.insset.jeanluc.ete.meta.model.types.MofType;


/**
 *
 * @author jldeleage
 */
public interface MofCollection extends MofType {

    public  MofType     getBaseType();
    public  void        setBaseType(MofType inType);

    public  boolean     isOrdered();
    public  boolean     isDistinct();

    /**
     * @return the java-style name, e.g. List&lt;String&gt;
     */
    @Override
    public default String getName() {
        return "List<" + getBaseType().getName() + ">";
    }

    @Override
    public default boolean         isCollection() {
        return true;
    }
}

