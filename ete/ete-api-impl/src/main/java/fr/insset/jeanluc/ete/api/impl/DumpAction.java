/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.meta.model.io.ModelWriter;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public class DumpAction extends ActionSupport {

    public final static String      DUMP_ACTION = "dump-action";



    @Override
    public MofPackage postProcess(MofPackage inPackage) throws EteException {
        OutputStream output = null;
        try {
            String filePath = (String) getParameter("file");
            output = filePath == null ? System.out
                    : new FileOutputStream(filePath);
            String format = (String) getParameter("format");
            if (format == null) {
                format = "UML-2-4-XMI-2-1";
            }
            ModelWriter newInstance = (ModelWriter) FactoryRegistry.newInstance(format + "-writer");
            newInstance.writeModel(inPackage, output);
            if (filePath != null) {
                output.close();
            }
            return inPackage;
        } catch (IOException | InstantiationException ex) {
            Logger.getLogger(DumpAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }       // postProcess
    



}
