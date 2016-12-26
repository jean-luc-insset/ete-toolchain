package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural;
import static fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural.UNBOUND;
import static fr.insset.jeanluc.ete.meta.model.datatype.UnlimitedNatural.UNLIMITED_NATURAL;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.MultiplicityElement;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * <div>
 * The read process reads elements of any kind. But each element must be
 * handled differently.<br>
 * Furthermore, the process may be customized.<br>
 * So we use a visitor to provide that double polymorphism.
 * </div>
 * 
 * <div>
 * TODO : there are two responsibilities in this class<ul>
 * <li>to complete to read process</li>
 * <li>to read XML</li>
 * </ul>
 * The first task must be achieved whatever the format is.<br>
 * The second one is the way to get information.<br>
 * So this class should be split&nbsp;:<ul>
 * <li>a generic visitor to complete the process</li>
 * <li>a helper class to read specific XML</li>
 * </ul>
 * </div>
 *
 * @author jldeleage
 */
public class XmlModelReaderVisitor extends DynamicVisitorSupport {


    public XmlModelReaderVisitor() {
        this.register("visit",
                    "fr.insset.jeanluc.ete.meta.model.emof",
                    "fr.insset.jeanluc.ete.meta.model.mofpackage");
    }


    public Object   visitPackageableElement(PackageableElement inElement, Object... inParam) {
        PackageableElement packageable = (PackageableElement) inElement;
        NamedElement       parentElement = (NamedElement) inParam[0];
        if (parentElement instanceof MofPackage) {
            MofPackage parentPackage = (MofPackage) parentElement;
            parentPackage.addPackagedElement(packageable);
            packageable.setOwningPackage(parentPackage);
        }
        EteModel            inModel = (EteModel) inParam[1];
        inModel.addPackagedElement(packageable);
        return inElement;
    }


    public Object   visitMofClass(MofClass inElement, Object... inParam) {
        PackageableElement packageable = (PackageableElement) inElement;
        NamedElement       parentElement = (NamedElement) inParam[0];
        if (parentElement instanceof MofPackage) {
            MofPackage parentPackage = (MofPackage) parentElement;
            parentPackage.addPackagedElement(packageable);
            packageable.setOwningPackage(parentPackage);
        }
        EteModel    inoutModel = (EteModel) inParam[1];
        inoutModel.addPackagedElement(packageable);
        return inElement;
    }


    /**
     * 
     * @param inProperty
     * @param inParam
     * @return
     * @throws XPathExpressionException 
     */
    public Object   visitProperty(Property inProperty, Object... inParam) throws EteException, XPathExpressionException {
        MofClass    parentClass = (MofClass) inParam[0];
        if (parentClass != null) {
            parentClass.addOwnedAttribute((Property) inProperty);
        }
        if (inProperty.getType() == null) {
            inProperty.setType(readType((Element)inParam[2], (EteModel)inParam[1]));
        }
        readMultiplicity(inProperty, (Element)inParam[2], (EteModel)inParam[1]);
        return inProperty;
    }


    public Object   visitOperation(Operation inOperation, Object... inParam) {
        MofClass    parentClass = (MofClass) inParam[0];
        parentClass.addOwnedOperation(inOperation);
        return inOperation;
    }


    /**
     * 
     * @param inAssociation
     * @param inParam
     * @return 
     */
    public Object   visitAssociation(Association inAssociation, Object... inParam) {
        Collection<NamedElement> namedElements = getNamedElements("memberEnd", "xmi:idref", (Element) inParam[2], (EteModel) inParam[1]);
        for (NamedElement aNamedElement : namedElements) {
            Property prop = (Property) aNamedElement;
            inAssociation.addMemberEnd(prop);
            prop.setAssociation(inAssociation);
        }
        namedElements = getNamedElements("ownedEnd", "xmi:id", (Element)inParam[2], (EteModel) inParam[1]);
        for (NamedElement aNamedElement : namedElements) {
            inAssociation.addOwnedEnd((Property) aNamedElement);
        }
        return inAssociation;
    }


    //========================================================================//


    protected Collection<NamedElement> getNamedElements(String inName, String inAttributeName, Element inElement, EteModel inModel) {
        Collection<NamedElement> result = new LinkedList<>();
        NodeList childNodes = inElement.getChildNodes();
        for (int i=0 ; i<childNodes.getLength() ; i++) {
            Node n = childNodes.item(i);
            if (! inName.equals(n.getNodeName())) {
                continue;
            }
            Element elt = (Element) n;
            String attribute = elt.getAttribute(inAttributeName);
            NamedElement elementById = inModel.getElementById(attribute);
            result.add(elementById);
        }
        return result;
    }

    //+=======================================================================//


    protected MofType  readType(Element inElement, EteModel inModel) {
        String attribute = inElement.getAttribute("type");
        if (attribute != null && ! "".equals(attribute)) {
            return (MofType)inModel.getElementById(attribute);
        }
        try {
            String typeAsString = xPath.evaluate(typePath, inElement);
            int index = typeAsString.lastIndexOf("::");
            typeAsString = typeAsString.substring(index+2);
            MofType type = (MofType)inModel.getElementByName(typeAsString);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XmlModelReaderVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    protected void   readMultiplicity(MultiplicityElement inoutElement, Element inXmlElement, EteModel inModel) throws EteException, XPathExpressionException {
        String upperAsString = xPath.evaluate(upperPath, inXmlElement);
        if (UNBOUND.equals(upperAsString)) {
            try {
                // TODO
                UnlimitedNatural unbound = (UnlimitedNatural) FactoryRegistry.newInstance(UNLIMITED_NATURAL);
                unbound.setValue(UNBOUND);
                inoutElement.setUpper(unbound);
            } catch (InstantiationException ex) {
                Logger.getLogger(XmlModelReaderVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                inoutElement.setUpper(Integer.parseInt(upperAsString));
            }
            catch (Exception e) {
                // our friend tried to give us a fake
                inoutElement.setUpper(1);
            }
        }       // upper != *
        String lowerAsString = xPath.evaluate(lowerPath, inXmlElement);
        try {
            inoutElement.setLower(Integer.parseInt(lowerAsString));
        }
        catch (Exception e) {
            // There is no lower value for multiplicity. Let's take 1 instead
            inoutElement.setLower(
                UNBOUND.equals(inoutElement.getUpper()) ?0:1
            );
        }
    }   // readMultiplicity


    private XPathFactory    factory     = XPathFactory.newInstance();
    private XPath           xPath       = factory.newXPath();
    private String          typePath    = "type/*/*/@referentPath";
    private String          lowerPath   = ".//lowerValue/@value";
    private String          upperPath   = ".//upperValue/@value";


}

