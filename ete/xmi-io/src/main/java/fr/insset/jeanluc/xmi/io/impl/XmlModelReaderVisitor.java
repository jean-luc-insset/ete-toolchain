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
import fr.insset.jeanluc.ete.meta.model.types.collections.MofCollection;
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
import static fr.insset.jeanluc.ete.meta.model.types.collections.MofSequence.MOF_SEQUENCE;



/**
 * <div>
 * The read process reads elements of any kind. But each element must be
 * handled differently.<br>
 * Furthermore, the process may be customized.<br>
 * So we use a visitor to provide that double polymorphism.
 * </div>
 * 
 * <div>
 * In order to achieve its goal, the visitor requires for some of its methods
 * <ul>
 * <li>of course, obj, the object it is visiting</li>
 * <li>the object that should contain obj</li>
 * <li>the model which should own obj</li>
 * <li>and the XML element defining obj</li>
 * </ul>
 * </div>
 * 
 * <div>
 * TODO : there are two responsibilities in this class<ul>
 * <li>to complete the read process</li>
 * <li>to deal with XML specifics</li>
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
        Collection<NamedElement> memberEnds = getNamedElements("memberEnd", "xmi:idref", (Element) inParam[2], (EteModel) inParam[1]);

        Property[]  properties  = new Property[2];
        int         index       = 0;
        for (NamedElement aNamedElement : memberEnds) {
            Property prop = (Property) aNamedElement;
            properties[index++] = prop;
            inAssociation.addMemberEnd(prop);
            prop.setAssociation(inAssociation);
        }
        properties[0].setOpposite(properties[1]);
        properties[1].setOpposite(properties[0]);

        memberEnds = getNamedElements("ownedEnd", "xmi:id", (Element)inParam[2], (EteModel) inParam[1]);
        for (NamedElement aNamedElement : memberEnds) {
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


    protected MofType  readType(Element inElement, EteModel inModel) throws EteException {
        MofType     result;
        String attribute = inElement.getAttribute("type");
        if (attribute != null && ! "".equals(attribute)) {
            result = (MofType)inModel.getElementById(attribute);
        }
        else {
            try {
                String typeAsString = xPath.evaluate(typePath, inElement);
                int index = typeAsString.lastIndexOf("::");
                typeAsString = typeAsString.substring(index+2);
                result = (MofType)inModel.getElementByName(typeAsString);
            } catch (XPathExpressionException ex) {
                Logger.getLogger(XmlModelReaderVisitor.class.getName()).log(Level.SEVERE, null, ex);
                throw new EteException(ex);
            }
        }
        NodeList upperValues = inElement.getElementsByTagName("upperValue");
        if (upperValues.getLength() > 0) {
            String upperValue = ((Element)upperValues.item(0)).getAttribute("value");
            if (! "1".equals(upperValue)) {
                try {
                    // TODO : check properties of the association to derive the
                    // true nature of the collection
                    MofCollection sequence = (MofCollection) FactoryRegistry.newInstance(MOF_SEQUENCE);
                    sequence.setBaseType(result);
                    result = sequence;
                } catch (InstantiationException ex) {
                    throw new EteException(ex);
                }
            }
        }
        return result;
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

