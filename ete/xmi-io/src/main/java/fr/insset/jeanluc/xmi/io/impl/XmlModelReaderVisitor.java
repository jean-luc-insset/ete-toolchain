package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.util.visit.DynamicVisitorSupport;
import java.util.Collection;
import java.util.LinkedList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
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


    public Object   visitProperty(Property inProperty, Object... inParam) {
        System.out.println("Property : " + inProperty.getName());
        if (inParam[0] != null) {
            System.out.println("  owning class : " + ((NamedElement)inParam[0]).getName() + " (" + inParam[0] + ")");
        }
        else {
            System.out.println("  no owning class");
        }
        System.out.println("  model : " + inParam[1]);
        System.out.println("  xml element : " + inParam[2] + " (" + inParam[2].getClass() + ")");
        MofClass    parentClass = (MofClass) inParam[0];
        if (parentClass != null) {
            parentClass.addOwnedAttribute((Property) inProperty);
        } else {
            System.out.println("Unable to add " + inProperty + " to " + parentClass);
        }
        if (inProperty.getType() == null) {
            readType((Element)inParam[2], (EteModel)inParam[1]);
        }
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
        System.out.println("Association parent : " + inParam[0]);
        System.out.println("Model : " + inParam[1]);
        System.out.println("Surrounding element : " + inParam[2]);
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
            System.out.println("IdRef : " + attribute);
            NamedElement elementById = inModel.getElementById(attribute);
            result.add(elementById);
        }
        return result;
    }

    //+=======================================================================//


    public MofType  readType(Element inElement, EteModel inModel) {
        System.out.println("Lecture du type de " + inElement);
        return null;
    }

}

