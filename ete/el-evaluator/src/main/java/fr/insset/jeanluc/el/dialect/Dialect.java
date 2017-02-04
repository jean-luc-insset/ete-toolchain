package fr.insset.jeanluc.el.dialect;



/**
 *
 * @author jldeleage
 */
public class Dialect {


    /**
     * "i2uc" = "initial to upper case"
     * 
     * @param inString
     * @return 
     */
    public String    i2uc(String inString) {
        if (inString == null) {
            return null;
        }
        if ("".equals(inString)) {
            return "";
        }
        return inString.substring(0, 1).toUpperCase() + inString.substring(1);
    }

    public String    i2lc(String inString) {
        if (inString == null) {
            return null;
        }
        if ("".equals(inString)) {
            return "";
        }
        return inString.substring(0, 1).toLowerCase() + inString.substring(1);
    }


    /**
     * moft2lt = "MOF Type to Local Type"
     * 
     * @param inString : MofType name
     * @return : type name in the local language
     */
    public String moft2lt(String inString) {
        return inString;
    }

    /**
     * mofv2lv : "MOF Value to Local Value"<br>
     * 
     * @param inValue : the value to be converted
     * @return the converted value
     */
    public Object mofv2lv(String inValue) {
        return inValue;
    }

    /**
     * The mofv2lv method is static. In case of a dynamic need, one can call
     * converter which returns the text of the statement to run to convert
     * the value.
     * 
     * @param inValue
     * @param inMofType
     * @return 
     */
    public String converter(String inValue, String inMofType) {
//        return inValue
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
