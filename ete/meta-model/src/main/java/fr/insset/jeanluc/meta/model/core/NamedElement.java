/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.core;

import java.util.Collection;

/**
 *
 * @author jldeleage
 */
public interface NamedElement extends Element {
    
    public  Collection<String>      getName();
    public  void                    addName(String inName);
    public  void                    removeName(String inName);


}
