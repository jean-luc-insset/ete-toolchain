package fr.insset.jeanluc.ete.as.impl;


import fr.insset.jeanluc.ete.as.api.*;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.gel.api.GelExpression;
import fr.insset.jeanluc.gel.impl.GelExpressionImpl;
import java.util.List;
import java.util.Set;


public class InstanciationImpl  extends GelExpressionImpl  implements Instanciation {






    //========================================================================//


    public MofClass getMofClass() {
        return mofClass;
    }

    public void setMofClass(MofClass inValue) {
        mofClass = inValue;
    }



    //========================================================================//




    //========================================================================//


    private MofClass  mofClass;


}