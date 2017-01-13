/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.Action;
import fr.insset.jeanluc.ete.api.ActionReader;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.ete.api.EteException;
import static fr.insset.jeanluc.ete.api.impl.ModuleAction.MODULE_DEFINITION_ACTION;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * <div>
 * A Module is defined within a configuration file provided by the attribute
 * "url" of a ModuleCallAction.<br>
 * When a ModuleCallAction is executed, the configuration file is loaded and
 * read.<br>
 * Then a Module is created to run that configuration.
 * </div>
 * 
 * <div>
 * </div>
 *
 * @author jldeleage
 */
public class ModuleCallAction extends ActionSupport {

    /**
     * The module call should have a "src" attribute.<br>
     * This attribute is the path to the configuration file.<br>
     * This literal holds the name of the "src" attribute.
     */
    public final static String      SRC                 = "src";

    public final static String      MODULE_CALL_ACTION  = "module-action";

    @Override
    public MofPackage preProcess(MofPackage inModel) throws EteException {
        try {
            // 1- attributes have been read. Among them, there should be a "src"
            // attribute or a "file" attribute.
            String src = (String)getParameter(SRC);
            String localBaseDir = (String)getParameter(BASE_DIR);
            if (src == null) {
                // Should we log the missing attribute or provide a default
                // value ?
                Logger.getGlobal().log(Level.WARNING, "Pas de fichier de configuration...");
                src = "ete-config.xml";
            }

            // 2- Turn the provided url to an actual url, eventually using
            //      the BASE_DIR parameter
            int index = src.lastIndexOf('/');
            if (index > 0) {
                localBaseDir += src.substring(0, index+1);
            }
            String resource = getFullUrl(src);
            URL url = new URL(resource);
            InputStream inputStream = url.openStream();

            // 3- try to read the configuration file
            ActionReader actionReader = (ActionReader) getParameter(ACTION_READER);
            if (actionReader == null) {
                actionReader = (ActionReader) FactoryRegistry.newInstance(ACTION_READER);
            }
            addParameter(ACTION_READER, actionReader);
            Object  rootElement = actionReader.readConfiguration(inputStream);

            Action action = (Action)FactoryRegistry.newInstance(MODULE_DEFINITION_ACTION);
            if (action instanceof ActionSupport) {
                ((ActionSupport) action).setDefinition(rootElement);
                ((ActionSupport) action).setReader(actionReader);
            }
            
            action.addParameter(BASE_DIR, localBaseDir);
            // the module will be processed among the children of
            // the call
            addChild(action);
            
            return inModel;
        } catch (InstantiationException | IOException ex) {
            Logger.getLogger(ModuleCallAction.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }



}
