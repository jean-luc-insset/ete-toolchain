package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
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
public class XmlModelReaderLogVisitor extends DynamicVisitorSupport {

    public XmlModelReaderLogVisitor() {
        this.register("visit",
                    "fr.insset.jeanluc.ete.meta.model.emof",
                    "fr.insset.jeanluc.ete.meta.model.mofpackage");
    }


    public Object   visitPackageableElement(PackageableElement inElement, Object... inParam) {
        System.out.println("inElement : " + inElement.getName());
        return inElement;
    }

    public Object   visitMofClass(MofClass inElement, Object... inParam) {
        System.out.println("Class : " + inElement.getName());
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

