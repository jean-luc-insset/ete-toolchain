package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import static fr.insset.jeanluc.ete.meta.model.emof.Association.ASSOCIATION;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import static fr.insset.jeanluc.ete.meta.model.emof.MofClass.MOF_CLASS;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import static fr.insset.jeanluc.ete.meta.model.emof.Operation.OPERATION;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import static fr.insset.jeanluc.ete.meta.model.emof.Property.PROPERTY;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage.PACKAGE;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.meta.model.io.ModelReader;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;






/**
 * This is an XML reader : it uses XPath to select definitions of
 * objects and then uses an element reader to read the actual content of the
 * item.
 *
 * @author jldeleage
 */
public class XmlModelReader implements ModelReader {


    public final String     PACKAGE_PATH     = "uml:Package";
    public final String     CLASS_PATH       = "uml:Class";
    public final String     ASSOCIATION_PATH = "uml:Association";
    public final String     PROPERTY_PATH    = "uml:Property";
    public final String     OPERATION_PATH   = "uml:Operation";


    @Override
    public Collection<NamedElement> readPackages(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, PACKAGE_PATH, PACKAGE);
        return result;
    }


    @Override
    public Collection<NamedElement> readClasses(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, CLASS_PATH, MOF_CLASS);
        return result;
    }


    @Override
    public Collection<NamedElement> readAssociations(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, ASSOCIATION_PATH, ASSOCIATION);

        return result;
    }


    @Override
    public Collection<NamedElement> readProperties(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, PROPERTY_PATH, PROPERTY);
        return result;
    }


    @Override
    public Collection<NamedElement> readOperations(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, OPERATION_PATH, OPERATION);
        return result;
    }


    

    //========================================================================//


    /**
     * Reads all elements matching the path, using inNode as context.<br>
     * Each DOM element is converted into a NamedElement by the factory
     * associated to inType.<br>
     * Every element is added to its own parent in the model.
     * 
     * @param inNode
     * @param inModel
     * @param inPath
     * @param inType
     * @return
     * @throws EteException 
     */
    protected List<NamedElement> readElements(Node inNode, EteModel inModel,
            String inPath, String inType) throws EteException {
        try {
            List<NamedElement> result = FactoryMethods.newList(NamedElement.class);
            AbstractFactory factory = FactoryRegistry.getRegistry().getFactory(inType);
            NodeList elementsByType = getElementsByType(inPath, inNode);
            for (int i=0 ; i<elementsByType.getLength() ; i++) {
                Element elt = (Element)elementsByType.item(i);
                NamedElement newInstance = (NamedElement)factory.newInstance();
                String name = elt.getAttribute("name");
                // TODO : we should read objects with empty name or no name.
                // Such objects can be associations
                if (null != name && !"".equals(name)) {
                    newInstance.setName(name);
                }
                String id = elt.getAttribute("xmi:id");
                newInstance.setId(id);
                inModel.addElement(newInstance);
                Node parentNode = elt.getParentNode();
                String parentName = parentNode instanceof Element ? ((Element)parentNode).getAttribute("name"):"";
                PackageableElement parentElement = inModel.getElementByName(parentName);
                for (DynamicVisitorSupport visitor : getVisitors()) {
                    try {
                        visitor.genericVisit(newInstance, parentElement, inModel, elt);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(XmlModelReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                result.add(newInstance);
            }
            return result;
        } catch (InstantiationException ex) {
            Logger.getLogger(XmlModelReader.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XmlModelReader.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }



    //========================================================================//
    //                            U T I L I T I E S                           //
    //========================================================================//


    protected NodeList getElementsByType(String inType, Node inSubTreeRoot) throws XPathExpressionException {
        String path = "//*[@*[local-name()='type']='" + inType + "']";
        NodeList result = getElements(path, inSubTreeRoot);
        return result;
    }

    protected NodeList getElements(String inPath, Node inSubTreeRoot) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
        NodeList result = (NodeList)path.evaluate(inPath, inSubTreeRoot, XPathConstants.NODESET);
        return result;
    }


    protected String getStringValue(String inPath, Node inSubTreeRoot) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
        String result = (String)path.evaluate(inPath, inSubTreeRoot, XPathConstants.STRING);
        return result;
    }

    //========================================================================//
    //                            A C C E S S O R S                           //
    //========================================================================//


    public Collection<DynamicVisitorSupport> getVisitors() {
        if (visitors.isEmpty()) {
            visitors.add(new XmlModelReaderVisitor());
        }
        return visitors;
    }

 
    public void addVisitors(DynamicVisitorSupport... inVisitors) {
        Collections.addAll(visitors, inVisitors);
    }


    public void setVisitors(Collection<DynamicVisitorSupport> visitors) {
        this.visitors = visitors;
    }



    //========================================================================//
    //                   I N S T A N C E   V A R I A B L E S                  //
    //========================================================================//



    /**
     * 
     */
    private     Collection<DynamicVisitorSupport>       visitors = new LinkedList<>();



}

