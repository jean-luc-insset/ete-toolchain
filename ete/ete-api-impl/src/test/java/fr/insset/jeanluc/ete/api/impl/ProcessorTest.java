package fr.insset.jeanluc.ete.api.impl;


import static fr.insset.jeanluc.ete.api.ActionSupport.ACTION_READER;
import static fr.insset.jeanluc.ete.api.impl.GenericTemplate.OUTPUT_BASE;
import static fr.insset.jeanluc.ete.api.impl.ModelAction.MODEL_READER;
import fr.insset.jeanluc.ete.api.impl.io.XmlActionReader;
import fr.insset.jeanluc.ete.meta.model.core.impl.Factories;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.impl.EteModelImpl;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import fr.insset.jeanluc.xmi.io.impl.XmlModelReader;
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
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        registry.registerFactory(MODEL, EteModelImpl.class);
        registry.registerFactory(ACTION_READER, XmlActionReader.class);
        registry.registerFactory(MODEL_READER, XmlModelReader.class);

        // Register meta-model
        Factories.init();
    }
    

    @After
    public void tearDown() {
    }


    /**
     * Test of run method, of class Processor.
     */
    @Test
    public void testRun() throws InstantiationException {
        System.out.println("run");

        ProcessorAction instance = new ProcessorAction("../../src/test/mda/ete-config.xml");
        instance.addParameter(OUTPUT_BASE, "target");
        instance.run();

        // Check the results
    }


}

