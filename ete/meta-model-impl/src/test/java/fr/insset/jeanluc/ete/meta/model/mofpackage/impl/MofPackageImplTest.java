/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.meta.model.mofpackage.impl;

import fr.insset.jeanluc.ete.meta.model.emof.MofClass;
import fr.insset.jeanluc.ete.meta.model.emof.impl.MofClassImpl;
import fr.insset.jeanluc.ete.meta.model.mofpackage.MofPackage;
import fr.insset.jeanluc.ete.meta.model.mofpackage.PackageableElement;
import java.util.stream.Stream;
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
public class MofPackageImplTest {
    

    private     MofPackage      instance;
    private     MofPackage      sub1;           // in instance
    private     MofPackage      sub2;           // in sub1
    private     MofPackage      sub3;           // in sub1
    private     MofClass        qcm;            // in instance
    private     MofClass        question;       // in sub1
    private     MofClass        reponse;        // in sub1
    private     MofClass        choix;          // in sub2
    private     MofClass        etudiant;       // in sub3


    public MofPackageImplTest() {
    }
    

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InstantiationException {
        instance = new MofPackageImpl();
        sub1     = addPackage("sub1", instance);
        sub2     = addPackage("sub2", sub1);
        sub3     = addPackage("sub3", sub1);
        qcm      = addClass("QCM", instance);
        question = addClass("Question", sub1);
        reponse  = addClass("Reponse", sub1);
        choix    = addClass("Choix", sub2);
        etudiant = addClass("Etudiant", sub3);
    }
    

    protected MofClass addClass(String inName, MofPackage inoutPackage) throws InstantiationException {
        MofClass    aClass = new MofClassImpl();
        aClass.setName(inName);
        inoutPackage.addPackagedElement(aClass);
        return aClass;
    }


    protected MofPackage addPackage(String inName, MofPackage inoutPackage) throws InstantiationException {
        MofPackage    subPackage = new MofPackageImpl();
        subPackage.setName(inName);
        inoutPackage.addPackagedElement(subPackage);
        return subPackage;
    }


    @After
    public void tearDown() {
    }




    /**
     * Test of getPackagedElementAsStream method, of class MofPackageImpl.
     */
    @Test
    public void testGetPackagedElementAsStream() throws InstantiationException {
        System.out.println("getPackagedElementAsStream");
        Stream<PackageableElement> result = instance.getPackagedElement();
        assertEquals(2, result.count());
    }


    /**
     * Test of getPackagedElementAsStream method, of class MofPackageImpl.
     */
    @Test
    public void testGetPackagesAsStream() throws InstantiationException {
        System.out.println("getPackagedElementAsStream");
        Stream<PackageableElement> result = instance.getPackages();
        assertEquals(1, result.count());
    }


    /**
     * Test of getPackagedElementAsStream method, of class MofPackageImpl.
     */
    @Test
    public void testGetClassesAsStream() throws InstantiationException {
        System.out.println("getPackagedElementAsStream");
        Stream<PackageableElement> result = instance.getClasses();
        assertEquals(1, result.count());
    }


    /**
     * Test of getPackagedElementAsStream method, of class MofPackageImpl.
     */
    @Test
    public void testGetAllClassesAsStream() throws InstantiationException {
        System.out.println("getAllClasses");
        Stream<PackageableElement> result = instance.getAllClasses();
        assertEquals(5, result.count());
    }




}
