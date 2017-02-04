package fr.insset.jeanluc.gel.api;


import java.util.List;
import java.util.Set;


public interface StringLiteral  extends Literal  {




    public default String getLiteralType() {
        return "String";
    }




    //========================================================================//





}


