package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface IntegerLiteral  extends Literal  {




    public default String getLiteralType() {
        return "Integer";
    }




    //========================================================================//




}