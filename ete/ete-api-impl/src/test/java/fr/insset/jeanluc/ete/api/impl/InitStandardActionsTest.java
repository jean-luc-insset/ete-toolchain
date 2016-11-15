/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.api.impl;

import fr.insset.jeanluc.ete.api.impl.util.InitStandardActions;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
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
public class InitStandardActionsTest {
    
    public InitStandardActionsTest() {
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
     * Test of init method, of class InitStandardActions.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        InitStandardActions.init();
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory("dump-action");
        assertNotNull(factory);
        factory = registry.getFactory("for-each-action");
        assertNotNull(factory);
        factory = registry.getFactory("if-action");
        assertNotNull(factory);
        factory = registry.getFactory("model-action");
        assertNotNull(factory);
        factory = registry.getFactory("module-action");
        assertNotNull(factory);
        factory = registry.getFactory("register-action");
        assertNotNull(factory);
        factory = registry.getFactory("velocity-action");
        assertNotNull(factory);
    }
    
}
