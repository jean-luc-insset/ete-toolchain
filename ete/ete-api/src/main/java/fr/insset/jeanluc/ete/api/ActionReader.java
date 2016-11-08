/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api;

/**
 *
 * @author jldeleage
 */
public interface ActionReader {

    public  void readAttributes(Action inoutAction, Object inParameter);
    public  void readChildren(Action inoutAction, Object inParameter);

}
