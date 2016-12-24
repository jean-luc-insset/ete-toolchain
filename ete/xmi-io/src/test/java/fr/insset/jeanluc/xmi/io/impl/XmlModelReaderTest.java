package fr.insset.jeanluc.xmi.io.impl;



import fr.insset.jeanluc.ete.meta.model.core.impl.FactoriesInitializer;
import fr.insset.jeanluc.ete.meta.model.emof.Association;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.Property;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
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
    public void testReadModelWithUrl() throws Exception {
        System.out.println("readModel");
        // 1- Initialize framework
        FactoriesInitializer.registerFactories();

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
        assertEquals(6, allClasses.size());
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


    //========================================================================//

    
}
