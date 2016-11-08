/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.Action;
import fr.insset.jeanluc.ete.api.ActionSupport;
import static fr.insset.jeanluc.ete.api.impl.ModuleAction.MODULE_DEFINITION_ACTION;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * A Module is defined within a configuration file provided by the attribute
 * "url" of a ModuleCallAction.<br>
 * When a ModuleCallAction is executed, the configuration file is loaded and
 * read.<br>
 * Then a Module is created to run that configuration.
 *
 * @author jldeleage
 */
public class ModuleCallAction extends ActionSupport {

    public final static String      URL     = "url";
    public final static String      FILE    = "file";


    public EteModel preProcess(EteModel inModel) {
        try {
            // 1- attributes have been read. Among them, there must be a url attribute
            // or a file attribute.
            String pathToConfigFile = (String)getParameter(URL);
            if (pathToConfigFile == null) {
                pathToConfigFile = (String)getParameter(FILE);
            }
            if (pathToConfigFile == null) {
                // We must log the missing attribute
                Logger.getLogger(ModuleCallAction.class.getName()).log(Level.SEVERE,
                        "UNABLE TO FIND CONFIGURATION FILE : no url attribute");
                return inModel;
            }

            // 2- Turn the provided url to an actual url, eventually using
            //      the "base-url" parameter
            String      path;
            if (pathToConfigFile.contains("://")) {
                path = pathToConfigFile;
            }
            else {
                String baseUrl = getBaseUrl();
                path = baseUrl + pathToConfigFile;
                int index = pathToConfigFile.lastIndexOf('/');
                if (index >= 0) {
                    baseUrl += pathToConfigFile.substring(0, index+1);
                    addParameter("base-url", baseUrl);
                }
            }

            // 3- try to read the configuration file
            Element     rootElement;
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Logger.getGlobal().log(Level.FINE, "Configuration file read in " + path + "...");
                Document document = documentBuilder.parse(path);
                rootElement = document.getDocumentElement();
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(ModuleCallAction.class.getName()).log(Level.SEVERE, null, ex);
                return inModel;
            }
            
            FactoryRegistry registry = getFactoryRegistry();
            AbstractFactory factory = registry.getFactory(MODULE_DEFINITION_ACTION);
            Action action = (Action) factory.newInstance();
            if (action instanceof ActionSupport) {
                ((ActionSupport) action).setDefinition(rootElement);
                ((ActionSupport) action).setReader(this.getReader());
            }
            // the module will be processed among the children of
            // the call
            addChild(action);
            
            return inModel;
        } catch (InstantiationException ex) {
            Logger.getLogger(ModuleCallAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }



}
