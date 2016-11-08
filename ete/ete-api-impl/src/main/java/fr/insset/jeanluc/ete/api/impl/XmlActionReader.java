/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.Action;
import fr.insset.jeanluc.ete.api.ActionReader;
import fr.insset.jeanluc.ete.api.ActionSupport;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jldeleage
 */
public class XmlActionReader implements ActionReader {


    @Override
    public void readChildren(Action inoutAction, Object parameters) {
        Logger global = Logger.getGlobal();

        ActionSupport   support = (ActionSupport)inoutAction;
        Object definition = support.getDefinition();

        Element element = (Element) definition;
        if (element == null) {
            return;
        }

        FactoryRegistry registry = FactoryRegistry.getRegistry();

        // Create an action for every child element
        NodeList childNodes = element.getChildNodes();
        int     i = 0;
        while (i < childNodes.getLength()) {
            Node node = childNodes.item(i++);
            if (!(node instanceof Element)) continue;
            Element childElement = (Element) node;
            String  elementName = childElement.getNodeName();
            global.log(Level.INFO, "Lecture d''une action de type {0}", elementName);
            AbstractFactory factory = registry.getFactory(elementName);
            try {
                Action childAction = (Action)factory.newInstance();
                global.log(Level.FINE, "Instanciation de l'action OK");
                inoutAction.addChild(childAction);
                if (childAction instanceof ActionSupport) {
                    support = (ActionSupport) childAction;
                    support.setDefinition(childElement);
                    support.setReader(this);
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(XmlActionReader.class.getName()).log(
                            Level.SEVERE, "Impossible d'instancier l'action " + elementName, ex);
            }       // child action creation       // child action creation
        }       // loop on child elements
    }       // readChildren


    @Override
    public void readAttributes(Action action, Object elt) {
        ActionSupport   support = (ActionSupport) action;
        Element element = (Element) support.getDefinition();
        if (element == null) {
            return;
        }
        NamedNodeMap attributes = element.getAttributes();
        for (int i=0 ; i<attributes.getLength() ; i++) {
            Node item = attributes.item(i);
            String name = item.getNodeName();
            String value = item.getNodeValue();
            action.addParameter(name, value);
        }       // loop over the attributes
    }       // readAttributes


}
