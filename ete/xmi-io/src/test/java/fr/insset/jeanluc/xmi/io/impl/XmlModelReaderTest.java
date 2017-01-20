package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.meta.model.constraint.Invariant;
import fr.insset.jeanluc.ete.meta.model.constraint.Postcondition;
import fr.insset.jeanluc.ete.meta.model.core.impl.Factories;
import fr.insset.jeanluc.ete.meta.model.core.PrimitiveDataTypes;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Operation;
import fr.insset.jeanluc.ete.meta.model.emof.Parameter;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import fr.insset.jeanluc.ete.meta.model.types.MofType;
import fr.insset.jeanluc.ete.meta.model.types.collections.MofCollection;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static fr.insset.jeanluc.ete.meta.model.types.collections.MofSequence.MOF_SEQUENCE;



/**
 *
 * @author jldeleage
 */
public class XmlModelReaderTest {

    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    //========================================================================//


    @Test
    public void testReadSimpleModel() throws Exception {
        System.out.println("readSimpleModel");
        // 1- Initialize framework
        Factories.init();

        // 2- call the operation
        XmlModelReader instance = new XmlModelReader();
        String  url = "../../src/test/mda/models/QCM.xml";
        EteModel parent = new EteModelImpl();
        EteModel result = instance.readModel(url, parent);

        // 3- check result
        Map<String, Integer>    properties = new HashMap<>();
        properties.put("Question", 4);
        properties.put("QCM", 2);
        Collection<MofClass> allClasses = result.getAllClasses();
        assertEquals(2, allClasses.size());
        Collection<MofClass> classes = result.getClasses();
        assertEquals(2, classes.size());
        Collection<Association> associations = new LinkedList<>();
        for (MofClass aClass : classes) {
            List<Property> ownedAttribute = aClass.getOwnedAttribute();
            assertEquals((long)properties.get(aClass.getName()), (long)ownedAttribute.size());
            for (Property aProperty : ownedAttribute) {
                Association association = aProperty.getAssociation();
                if (null != association) {
                    associations.add(association);
                    Collection<Property> memberEnd = association.getMemberEnd();
                    assertTrue(memberEnd.contains(aProperty));
                    Collection<Property> ownedEnd = association.getOwnedEnd();
                    System.out.println("ownedEnd : " + ownedEnd.size());
                }
            }       // loop over properties
        }       // loop over classes
        assertEquals(1, associations.size());
    }



    @Test
    public void testReadComplexModel() throws Exception {
        System.out.println("readComplexModel");
        // 1- Initialize framework
        Factories.init();

        // 2- call the operation
        XmlModelReader instance = new XmlModelReader();
        instance.addVisitors(new XmlModelReaderVisitor());
        String  url = "../../src/test/mda/models/QCM_complet.xml";
        EteModel parent = new EteModelImpl();
        PrimitiveDataTypes.init(parent);
        EteModel result = instance.readModel(url, parent);

        // 3- Check result

        // 3-a check the number of classes
        Collection<MofClass> classes = result.getClasses();
        assertEquals(9, classes.size());

        // 3-b check the number of properties of each class
        Map<String, Integer>    properties = new HashMap<>();
        properties.put("CreateurQuestion", 0);
        properties.put("QCM", 2);
        properties.put("Question", 5);
        properties.put("Etudiant", 2);
        properties.put("Epreuve", 3);
        properties.put("Passage", 5);
        properties.put("Reponse", 2);
        properties.put("QuestionPosee", 3);
        properties.put("ReponseFournie", 1);
        for (MofClass aClass : classes) {
            Integer get = properties.get(aClass.getName());
            if (get != null) {
                assertEquals((long)get, (long)aClass.getOwnedAttribute().size());
            }
        }

        // 3-c check some associations
        // 3-c-1 check the relation passage -> questionsPosees
        MofClass passageClass = (MofClass) result.getElementByName("Passage");
        MofClass questionPoseeClass = (MofClass) result.getElementByName("QuestionPosee");
        MofCollection  sequenceQuestionsPoseesClass = (MofCollection) FactoryRegistry.newInstance(MOF_SEQUENCE);
        sequenceQuestionsPoseesClass.setBaseType(questionPoseeClass);
        Property questionsPosees = passageClass.getOwnedAttribute("questionsPosees");
        MofType typeQuestionsPosees = questionsPosees.getType();
        assertEquals(sequenceQuestionsPoseesClass, typeQuestionsPosees);
        Collection<Invariant> invariants = questionPoseeClass.getInvariants();
        assertEquals(1, invariants.size());

        // 3-d check some operations
        // 3-d-1 Passage::calculeNote
        List<Operation> ownedOperations = passageClass.getOwnedOperation();
        assertEquals(1, ownedOperations.size());
        Operation calculeNote = ownedOperations.get(0);
        MofType calculeNoteType = calculeNote.getType();
        PackageableElement floatType = result.getElementByName("float");
        assertNotNull(floatType);
        assertEquals(floatType, calculeNoteType);
        Collection<Postcondition> postconditions = calculeNote.getPostconditions();
        assertEquals(1, postconditions.size());
        // 3-d-2 CreateurQuestion::nouvelleReponse
        MofClass createurQuestionClass = (MofClass) result.getElementByName("CreateurQuestion");
        Operation ownedOperation = createurQuestionClass.getOwnedOperation("nouvelleReponse");
        MofType type = ownedOperation.getType();
        MofClass reponseClass = (MofClass)result.getElementByName("Reponse");
        assertEquals(reponseClass, type);
        Collection<Parameter> ownedParameters = ownedOperation.getOwnedParameter();
        assertEquals(2, ownedParameters.size());
        for (Parameter aParameter : ownedParameters) {
            String      parameterName = aParameter.getName();
            if ("inQuestion".equals(parameterName)) {
                MofClass questionClass = (MofClass) result.getElementByName("Question");
                assertEquals(questionClass, aParameter.getType());
            } else if ("inLibelle".equals(parameterName)) {
                MofType  stringType = (MofType) result.getElementByName("String");
                assertEquals(stringType, aParameter.getType());
            } else {
                fail("Unknown parameter : " + parameterName + " in " + ownedOperation.getName());
            }
        }

        // 3-e check some stereotypes
        assertEquals("true", passageClass.hasStereotype("Entity"));
        

    }       // testReadComplexModel


    //========================================================================//




}       // XmlModelReaderTest
