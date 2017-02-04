package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface Different  extends BooleanOperation  {




    public default String getSymbol() {
        return "<>";
    }




    //========================================================================//




}