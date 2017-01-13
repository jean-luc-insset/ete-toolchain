package fr.insset.jeanluc.ete.api.impl;

import static fr.insset.jeanluc.ete.api.Action.BASE_DIR;
import fr.insset.jeanluc.ete.api.EteException;
import static fr.insset.jeanluc.ete.api.impl.GenericTemplate.ITEMS;
import static fr.insset.jeanluc.ete.api.impl.GenericTemplate.TARGET;
import static fr.insset.jeanluc.ete.api.impl.GenericTemplate.TEMPLATE;
import fr.insset.jeanluc.ete.api.impl.util.InitStandardActions;
import fr.insset.jeanluc.ete.meta.model.core.impl.Factories;
import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import static fr.insset.jeanluc.ete.meta.model.emof.MofClass.MOF_CLASS;
import fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage.MOF_PACKAGE;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 *
 * @author jldeleage
 */
public class VelocityActionTest {
    
    public VelocityActionTest() {
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
     * Test of initIteration method, of class VelocityAction.
     */
    @Test
    public void testVelocity() throws InstantiationException, EteException {
        System.out.println("Velocity");
        // Registers default factories
        Factories.init();
        // Registers default actions
        InitStandardActions.init();
        System.out.println("initIteration");
        VelocityAction instance = new VelocityAction();
        EteModel model = (EteModel) FactoryRegistry.newInstance(MODEL);
        MofClass aClass = (MofClass) FactoryRegistry.newInstance(MOF_CLASS);
        MofPackage aPackage = (MofPackage) FactoryRegistry.newInstance(MOF_PACKAGE);
        aPackage.setName("mypackage");
        aPackage.addPackagedElement(aClass);
        aClass.setOwningPackage(aPackage);
        aClass.setName("MyClass");
        model.addElement(aClass);
        instance.addParameter(BASE_DIR, "src/test/mda/");
        instance.addParameter(MODEL, model);
        instance.addParameter(ITEMS, "${classes}");
        instance.addParameter(TEMPLATE, "templates/umlclass2interface.vm");
        instance.addParameter(TARGET, "target/generated-sources/ete/${current.package.name.replace('.', '/')}/api/${current.name}.java");
        instance.process((MofPackage) model);
        File result = new File("target/generated-sources/ete/mypackage/api/MyClass.java");
        Assert.assertTrue(result.exists());
    }
    
}
