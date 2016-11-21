/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.plain.text.io;

import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.meta.model.io.ModelWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public class TextModelWriter implements ModelWriter {

    private static final String     INDENT = "- ";

    @Override
    public void writeModel(MofPackage inModel, OutputStream inOutput) throws EteException {
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(inOutput, "UTF-8"));
            writeRecursive(inModel, pw, INDENT);
            pw.flush();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TextModelWriter.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }
    
    protected   void writeRecursive(MofPackage inPackage, PrintWriter inOutput, String inIndent) {
        inOutput.print(inIndent);
        inOutput.println(inPackage.getName());
        inIndent += INDENT;
        for (MofPackage p : inPackage.getPackages()) {
            writeRecursive(p, inOutput, inIndent);
        }
    }


}
