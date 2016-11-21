package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.api.EteException;
import fr.insset.jeanluc.ete.meta.model.core.NamedElement;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.meta.model.io.ModelReader;
import fr.insset.jeanluc.util.factory.FactoryMethods;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.w3c.dom.Document;






/**
 * This is an XML reader : it uses XPath to select definitions of
 * objects and then uses an element reader to read the actual content of the
 * item.
 *
 * @author jldeleage
 */
public class XmlModelReader implements ModelReader {


    public final String     PACKAGE_PATH     = "";
    public final String     CLASS_PATH       = "";
    public final String     ASSOCIATION_PATH = "";
    public final String     PROPERTY_PATH    = "";
    public final String     OPERATION_PATH   = "";


    @Override
    public Collection<NamedElement> readPackages(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, PACKAGE_PATH);
        return result;
    }


    @Override
    public Collection<NamedElement> readClasses(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, CLASS_PATH);
        return result;
    }


    @Override
    public Collection<NamedElement> readAssociations(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, ASSOCIATION_PATH);
        return result;
    }


    @Override
    public Collection<NamedElement> readProperties(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, PROPERTY_PATH);
        return result;
    }


    @Override
    public Collection<NamedElement> readOperations(Object inDocument, EteModel inoutModel) throws EteException {
        Collection<NamedElement> result = readElements((Document) inDocument, inoutModel, OPERATION_PATH);
        return result;
    }


    

    //========================================================================//


    public Collection<NamedElement>     readElements(Document inDocument, EteModel inoutModel, String inPath) throws EteException {
        Collection<NamedElement> result;
        try {
            result = FactoryMethods.newList(NamedElement.class);
        } catch (InstantiationException ex) {
            Logger.getLogger(XmlModelReader.class.getName()).log(Level.SEVERE, null, ex);
            throw new EteException(ex);
        }
        return result;
    }

}

