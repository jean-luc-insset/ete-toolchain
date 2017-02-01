package fr.insset.jeanluc.gel.impl;


import fr.insset.jeanluc.gel.api.*;
import java.util.List;



public class VariableDeclarationImpl  implements VariableDeclaration {


    public VariableDeclarationImpl() {
    }


    //========================================================================//





    //========================================================================//
    //                A C C E S S O R S   and   M U T A T O R S               //
    //========================================================================//



    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String inValue) {
        identifier = inValue;
    }

    
    

    //========================================================================//
    //                           O P E R A T I O N S                          //
    //========================================================================//




    //========================================================================//
    //                   I N S T A N C E   V A R I A B L E S                  //
    //========================================================================//


    private String identifier;


}


