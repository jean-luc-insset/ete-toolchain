package fr.insset.jeanluc.ete.api.impl;



import static fr.insset.jeanluc.ete.api.ActionReader.ACTION_READER;
import static fr.insset.jeanluc.ete.api.impl.ModelAction.MODEL_READER;
import fr.insset.jeanluc.ete.api.impl.io.XmlActionReader;
import fr.insset.jeanluc.ete.meta.model.impl.util.MetaModelInit;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import fr.insset.jeanluc.xmi.io.impl.XmlModelReader;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author jldeleage
 */
public class ProcessorTest {


    public ProcessorTest() {
    }


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


    /**
     * Test of run method, of class Processor.
     */
    @Test
    public void test() throws InstantiationException {
        System.out.println("run");
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerFactory(MODEL, EteModelImpl.class);
        registry.registerFactory(ACTION_READER, XmlActionReader.class);
        registry.registerFactory(MODEL_READER, XmlModelReader.class);

        // Register meta-model
        MetaModelInit.init();

        ProcessorAction instance = new ProcessorAction("../../src/test/mda/ete-config.xml");
        instance.run();
    }


}
