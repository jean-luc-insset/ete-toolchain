/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.el.evaluator;

import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import static fr.insset.jeanluc.ete.meta.model.mofpackage.EteModel.MODEL;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.util.factory.AbstractFactory;
import fr.insset.jeanluc.util.factory.FactoryRegistry;
import java.util.List;
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
public class ELEvaluatorTest {
    

    private     MofPackage      model;


    public ELEvaluatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InstantiationException {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(MODEL);
        model = (MofPackage) factory.newInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of evaluateBoolean method, of class ELEvaluator.
     */
    @Test
    public void testEvaluateBoolean() {
        System.out.println("evaluateBoolean");
        String inExpression = "true";
        ELEvaluator instance = new ELEvaluator(null);
        Boolean expResult = true;
        Boolean result = instance.evaluateBoolean(inExpression);
        assertEquals(expResult, result);
    }



    /**
     * Test of evaluateDouble method, of class ELEvaluator.
     */
    @Test
    public void testEvaluateDouble() {
        System.out.println("evaluateDouble");
        String inExpression = "12.3";
        ELEvaluator instance = new ELEvaluator(null);
        Double expResult = 12.3;
        Double result = instance.evaluateDouble(inExpression);
        assertEquals(expResult, result);
    }

    /**
     * Test of evaluateString method, of class ELEvaluator.
     */
    @Test
    public void testEvaluateString() {
        System.out.println("evaluateString");
        String inExpression = "Hello world";
        ELEvaluator instance = new ELEvaluator(null);
        String expResult = "Hello world";
        String result = instance.evaluateString(inExpression);
        assertEquals(expResult, result);
    }

    /**
     * Test of evaluateList method, of class ELEvaluator.
     */
    @Test
    public void testEvaluateList() {
        System.out.println("evaluateList");
        String inExpression = "";
        ELEvaluator instance = null;
        List expResult = null;
        List result = instance.evaluateList(inExpression);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of evaluateInt method, of class ELEvaluator.
     */
    @Test
    public void testEvaluateInt() {
        System.out.println("evaluate");
        ELEvaluator instance = new ELEvaluator(null);
        Object expResult = new Integer(12);
        Object result = instance.evaluateInt("12");
        assertEquals(expResult, result);
    }
    
}
