package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.api.EteException;
import static fr.insset.jeanluc.ete.meta.model.constraint.Invariant.INVARIANT;
import static fr.insset.jeanluc.ete.meta.model.constraint.Postcondition.POSTCONDITION;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import static fr.insset.jeanluc.ete.meta.model.emof.Association.ASSOCIATION;
import static fr.insset.jeanluc.ete.meta.model.emof.MofClass.MOF_CLASS;
import static fr.insset.jeanluc.ete.meta.model.emof.Operation.OPERATION;
import static fr.insset.jeanluc.ete.meta.model.emof.Property.PROPERTY;
import static fr.insset.jeanluc.ete.meta.model.emof.Stereotype.STEREOTYPE;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
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


    public final String     PACKAGE_PATH        = "uml:Package";
    public final String     CLASS_PATH          = "//*[@*='uml:Package']/*[@*='uml:Class']";
    public final String     ASSOCIATION_PATH    = "uml:Association";
    public final String     PROPERTY_PATH       = "uml:Property";
    public final String     OPERATION_PATH      = "uml:Operation";
    public final String     INVARIANT_PATH      = ".//packagedElement/ownedRule";
    public final String     PRECONDITION_PATH   = ".//ownedOperation/ownedRule[@*=../precondition/@*]";
    public final String     POSTCONDITION_PATH  = ".//ownedOperation/ownedRule[@*=../postcondition/@*]";
    public final String     STEREOTYPE_PATH     = "";




    public XmlModelReader() {
    }


    @Override
    public Collection<NamedElement> readPackages(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, PACKAGE_PATH, PACKAGE);
        return result;
    }


    @Override
    public Collection<NamedElement> readClasses(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElementsByPath((Document) inDocument, inoutModel, CLASS_PATH, MOF_CLASS);
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


    @Override
    public Collection<NamedElement> readInvariants(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElementsByPath((Document) inDocument, inoutModel, INVARIANT_PATH, INVARIANT);
        return result;
    }


    @Override
    public Collection<NamedElement> readSpecifications(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElementsByPath((Document) inDocument, inoutModel, POSTCONDITION_PATH, POSTCONDITION);
        return result;
    }

    @Override
    public Collection<NamedElement> readStereotypes(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElementsByPath((Document) inDocument, inoutModel, STEREOTYPE_PATH, STEREOTYPE);
        return result;
    }

    
    

    //========================================================================//


    /**
     * Reads all elements described by inPath, using inNode as context.<br>
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
            NodeList elementsByType = getElementsByType(inPath, inNode);
            return _doReadElements(elementsByType, inNode, inModel, inPath, inType);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XmlModelReader.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }

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
    protected List<NamedElement> readElementsByPath(Node inNode, EteModel inModel,
            String inPath, String inType) throws EteException {
        try {
            NodeList elementsByType = getElements(inPath, inNode);
            return _doReadElements(elementsByType, inNode, inModel, inPath, inType);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XmlModelReader.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
    }

    /**
     * Reads the elements in the node list. For each element, creates an
     * according named element and sets its name.<br>
     * The new named element is added to its parent.<br>
     * Then visitors are invoked for the new named element.
     * 
     * @param elements
     * @param inNode
     * @param inModel
     * @param inPath
     * @param inType
     * @return
     * @throws EteException 
     */
    protected List<NamedElement> _doReadElements(NodeList elements, Node inNode, EteModel inModel,
            String inPath, String inType) throws EteException {
        try {
            List<NamedElement> result = FactoryMethods.newList(NamedElement.class);
            AbstractFactory factory = FactoryRegistry.getRegistry().getFactory(inType);
            for (int i=0 ; i<elements.getLength() ; i++) {
                Element domElement = (Element)elements.item(i);
                NamedElement newInstance = (NamedElement)factory.newInstance();
                String name = domElement.getAttribute("name");
                // TODO : we should read objects with empty name or no name.
                // Such objects can be associations
                if (null != name && !"".equals(name)) {
                    newInstance.setName(name);
                }
                String id = domElement.getAttribute("xmi:id");
                newInstance.setId(id);
                inModel.addElement(newInstance);
                Node parentNode = domElement.getParentNode();
                String parentName = parentNode instanceof Element ? ((Element)parentNode).getAttribute("name"):"";
                String parentId   = parentNode instanceof Element ? ((Element)parentNode).getAttribute("xmi:id"):"";
                NamedElement parentNamedElement = inModel.getElementById(parentId);
//                PackageableElement parentElement = inModel.getElementByName(parentName);
                for (DynamicVisitorSupport visitor : getVisitors()) {
                    try {
                        visitor.genericVisit(newInstance, parentNamedElement, inModel, domElement);
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

