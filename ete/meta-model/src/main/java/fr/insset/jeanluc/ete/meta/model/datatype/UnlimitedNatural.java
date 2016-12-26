package fr.insset.jeanluc.ete.meta.model.datatype;

/**
 *
 * @author jldeleage
 */
public interface UnlimitedNatural extends DataType {


    public final static String  UNLIMITED_NATURAL = "unlimited-natural";
    public final static String  ZERO              = "0";
    public final static String  UN                = "1";


    public  String    getValue();


}
