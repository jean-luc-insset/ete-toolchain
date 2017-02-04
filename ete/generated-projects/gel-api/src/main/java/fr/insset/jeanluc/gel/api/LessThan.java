package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface LessThan  extends BooleanOperation  {




    public default String getSymbol() {
        return "<";
    }




    //========================================================================//




}