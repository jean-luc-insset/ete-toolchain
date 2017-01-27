/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.el.evaluator;

import fr.insset.jeanluc.util.coll.ListDecorator;
import java.util.HashMap;
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
public class SimpleEvaluatorTest {
    
    public SimpleEvaluatorTest() {
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
     * Test of evaluate method, of class SimpleEvaluator.
     */
    @Test
    public void testEvaluateSimple() {
        System.out.println("evaluate");
        String inExpression = "#{ 5 + 3 }";
        ELEvaluator instance = new ELEvaluator();
        Object expResult = new Long(8);
        Object result = instance.evaluate(inExpression, new HashMap<String, Object>());
        assertEquals(expResult, result);
    }

    /**
     * Test of evaluate method, of class SimpleEvaluator.
     */
    @Test
    public void testEvaluateSimpleWithInitialAndFinalConcat() {
        System.out.println("evaluate");
        String inExpression = "The price is #{ 5 + 3 } €";
        ELEvaluator instance = new ELEvaluator();
        Object expResult = "The price is 8 €";
        Object result = instance.evaluate(inExpression, new HashMap<String, Object>());
        assertEquals(expResult, result);
    }

    @Test
    public void testEvaluateWithInitialConcat() {
        System.out.println("evaluateWithInitialConcat");
        String inExpression = "The price is #{ 5 + 3 }";
        ELEvaluator instance = new ELEvaluator();
        Object expResult = "The price is 8";
        Object result = instance.evaluate(inExpression, new HashMap<String, Object>());
        assertEquals(expResult, result);
    }

    @Test
    public void testEvaluateWithFinalConcat() {
        System.out.println("evaluateWithFinalConcat");
        String inExpression = "#{ 5 + 3 } €";
        ELEvaluator instance = new ELEvaluator();
        Object expResult = "8 €";
        Object result = instance.evaluate(inExpression);
        assertEquals(expResult, result);
    }

    @Test
    public void testEvaluateParameter() {
        System.out.println("evaluateParameter");
        String inExpression = "The price is #{ price } €";
        ELEvaluator instance = new ELEvaluator();
        instance.addParameter("price", new Long(8));
        Object expResult = "The price is 8 €";
        Object result = instance.evaluate(inExpression, new HashMap<String, Object>());
        assertEquals(expResult, result);
    }

    @Test
    public void testEvaluateLambda() {
        System.out.println("evaluateParameter");
        ListDecorator<Integer> collect = new ListDecorator<>();
        collect.add(10);
        collect.add(20);
        collect.add(30);
        collect.add(20);
        collect.add(40);
        String inExpression = "#{list.stream().filter(n->n>20).toList()}";
        ELEvaluator instance = new ELEvaluator();
        instance.addParameter("list", collect);
        Object result = instance.evaluate(inExpression, new HashMap<String, Object>());
        assertEquals(result, result);
        collect = collect.filter(n->n>20);
        assertEquals(collect, result);
    }

    @Test
    public void testEvaluateLateParameter() {
        System.out.println("evaluateParameter");
        String inExpression = "The price is #{ price } €";
        ELEvaluator instance = new ELEvaluator();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("price", new Long(8));
        Object expResult = "The price is 8 €";
        Object result = instance.evaluate(inExpression, parameters);
        assertEquals(expResult, result);
    }



}
