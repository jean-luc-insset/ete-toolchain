/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.core.impl;

import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author jldeleage
 */
public class NamedElementImpl implements NamedElement {

    @Override
    public Collection<String> getName() {
        return name;
    }

    @Override
    public void addName(String inName) {
        name.add(inName);
    }

    @Override
    public void removeName(String inName) {
        name.remove(inName);
    }


    private     Collection<String>      name = new LinkedList<String>();
    
}
