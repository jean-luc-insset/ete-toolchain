package fr.insset.jeanluc.el.dialect;

import static fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes.DATE_TYPE;
import static fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes.FLOAT_TYPE;
import static fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes.INT_TYPE;
import static fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes.STRING_TYPE;
import static fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes.TYPE_SUFFIX;

/**
 *
 * @author jldeleage
 */
public class Java implements Dialect {

    public String moft2lt(String inString) {
        switch (inString) {
            case STRING_TYPE + TYPE_SUFFIX:
                return "String";
            case INT_TYPE + TYPE_SUFFIX :
                return "int";
            case FLOAT_TYPE + TYPE_SUFFIX:
                return "double";
            case DATE_TYPE + TYPE_SUFFIX:
                return "java.util.Date";
        }
        return inString;
    }


}
