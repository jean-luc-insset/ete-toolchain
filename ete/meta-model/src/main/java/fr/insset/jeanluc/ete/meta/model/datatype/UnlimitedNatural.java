package fr.insset.jeanluc.ete.meta.model.datatype;

/**
 *
 * @author jldeleage
 */
public interface UnlimitedNatural extends DataType, Comparable<Object> {


    public final static String  UNLIMITED_NATURAL = "unlimited-natural";

    public final static String  UNBOUND           = "*";
    public final static String  ZERO              = "0";
    public final static String  UN                = "1";


    public  String    getValue();
    public  void      setValue(String inValue);


    /**
     * obj must be an UnlimitedNatural or an Integer.
     * 
     * @param obj
     * @return 
     */
    public default int compareTo(Object obj) {
        Integer     itsValue;
        if (obj instanceof UnlimitedNatural) {
            UnlimitedNatural other  = (UnlimitedNatural) obj;
            if (UNBOUND.equals(getValue())) {
                return UNBOUND.equals(other.getValue()) ? 0:1;
            }
            if (UNBOUND.equals(getValue())) {
                return -1;
            }
            itsValue = Integer.parseInt(other.getValue());
        }
        else {
            itsValue = (Integer) obj;
        }
        if (UNBOUND.equals(getValue())) {
            return 1;
        }
        Integer myValue = Integer.parseInt(getValue());
        return myValue.compareTo(itsValue);
    }

}
