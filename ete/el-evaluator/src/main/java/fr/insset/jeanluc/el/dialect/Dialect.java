package fr.insset.jeanluc.el.dialect;

import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;



/**
 *
 * @author jldeleage
 */
public interface Dialect {


    /**
     * "i2uc" = "initial to upper case"
     * 
     * @param inString
     * @return 
     */
    public default String    i2uc(String inString) {
        if (inString == null) {
            return null;
        }
        if ("".equals(inString)) {
            return "";
        }
        return inString.substring(0, 1).toUpperCase() + inString.substring(1);
    }

    public default String    i2lc(String inString) {
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
    public default String moft2lt(String inString) {
        return inString;
    }

    /**
     * mofv2lv : "MOF Value to Local Value"<br>
     * 
     * @param inValue : the value to be converted
     * @return the converted value
     */
    public default Object mofv2lv(String inValue) {
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
    public default String converter(String inValue, String inMofType) {
//        return inValue
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public default boolean isEntity(MofClass inClass) {
        if (inClass.hasStereotype("Entity")) {
            return true;
        }
        MofPackage owningPackage = inClass.getOwningPackage();
        if (owningPackage != null) {
            return isEntityPackage(owningPackage);
        }
        return false;
    }


    public default boolean isEntityPackage(MofPackage inPackage) {
        MofPackage current = inPackage;
        while (current != null) {
            if (current.hasStereotype("entitypackage")) {
                return true;
            }
            current = current.getOwningPackage();
        }
        return false;
    }

}
