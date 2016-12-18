/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jldeleage
 */
public interface ActionReader {


    public  final static String     ACTION_READER = "action-reader";


    public  default Object readConfiguration(String inPath) throws EteException {
        try {
            if (! inPath.contains(":/")) {
                // It's not a URL so it is a file in the local file system
                if (!inPath.startsWith("/")) {
                    File file = new File(inPath);
                    if (file.isDirectory()) {
                        file = new File(file, "ete-config.xml");
                    }
                    inPath = file.getAbsolutePath();
                    
                }
                inPath = "file://" + inPath;
            }
            URL url = new URL(inPath);
            return readConfiguration(url.openStream());
        } catch (IOException ex) {
            Logger.getLogger(ActionReader.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }

    /**
     * The only provided implementation returns a W3C XML Document.
     * 
     * @param inInputStream
     * @return
     * @throws EteException 
     */
    public  Object  readConfiguration(InputStream inInputStream) throws EteException;
    public  void    readAttributes(Action inoutAction, Object inParameter) throws EteException;
    public  void    readChildren(Action inoutAction, Object inParameter) throws EteException;

}
