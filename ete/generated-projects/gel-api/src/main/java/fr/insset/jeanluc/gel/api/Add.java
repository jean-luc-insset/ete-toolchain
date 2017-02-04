package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface Add  extends NumberOperation  {




    public default String getSymbol() {
        return "+";
    }




    //========================================================================//




}