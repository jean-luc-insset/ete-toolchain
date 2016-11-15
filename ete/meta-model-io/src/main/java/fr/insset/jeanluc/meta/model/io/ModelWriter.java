/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.meta.model.io;

import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import java.io.OutputStream;

/**
 *
 * @author jldeleage
 */
public interface ModelWriter {
    

    public  void    writeModel(MofPackage inModel, OutputStream inOutput) throws EteException;


}
