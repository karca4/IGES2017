/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Autore;
import entities.CasaEditrice;
import entities.Collana;
import entities.Copia;
import entities.Libro;
import entities.Manuale;
import entities.Periodico;
import entities.Volume;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Criterio;

/**
 *
 * @author carmi
 */
public class ManagerGestioneLibriTest {
    
    public ManagerGestioneLibriTest() {
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
     * Test of getCollane method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetCollane() {
        System.out.println("getCollane");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        List<Collana> expResult = null;
        List<Collana> result = instance.getCollane();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertVolume method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertVolume() {
        System.out.println("insertVolume");
        Volume volume = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 0;
        int result = instance.insertVolume(volume);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertLibro method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertLibro() {
        System.out.println("insertLibro");
        Libro libro = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 0;
        int result = instance.insertLibro(libro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertManuale method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertManuale() {
        System.out.println("insertManuale");
        Manuale manuale = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 0;
        int result = instance.insertManuale(manuale);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPeriodico method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertPeriodico() {
        System.out.println("insertPeriodico");
        Periodico periodico = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 0;
        int result = instance.insertPeriodico(periodico);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertVolumeInCollana method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertVolumeInCollana() {
        System.out.println("insertVolumeInCollana");
        Volume v = null;
        Collana c = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        boolean expResult = false;
        boolean result = instance.insertVolumeInCollana(v, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAutoreDiVolume method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertAutoreDiVolume() {
        System.out.println("insertAutoreDiVolume");
        Volume v = null;
        Autore a = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        boolean expResult = false;
        boolean result = instance.insertAutoreDiVolume(v, a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaLibro method, of class ManagerGestioneLibri.
     */
    @Test
    public void testCercaLibro() {
        System.out.println("cercaLibro");
        Criterio c = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        Collection<Libro> expResult = null;
        Collection<Libro> result = instance.cercaLibro(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaManuale method, of class ManagerGestioneLibri.
     */
    @Test
    public void testCercaManuale() {
        System.out.println("cercaManuale");
        Criterio c = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        Collection<Manuale> expResult = null;
        Collection<Manuale> result = instance.cercaManuale(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaPeriodico method, of class ManagerGestioneLibri.
     */
    @Test
    public void testCercaPeriodico() {
        System.out.println("cercaPeriodico");
        Criterio c = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        Collection<Periodico> expResult = null;
        Collection<Periodico> result = instance.cercaPeriodico(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCaseEditrici method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetCaseEditrici() {
        System.out.println("getCaseEditrici");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        List<CasaEditrice> expResult = null;
        List<CasaEditrice> result = instance.getCaseEditrici();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAutori method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetAutori() {
        System.out.println("getAutori");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        List<Autore> expResult = null;
        List<Autore> result = instance.getAutori();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVolumi method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetVolumi() {
        System.out.println("getVolumi");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        List<Volume> expResult = null;
        List<Volume> result = instance.getVolumi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCopie method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetCopie() {
        System.out.println("getCopie");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        List<Copia> expResult = null;
        List<Copia> result = instance.getCopie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVolumiNonPosizionati method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetVolumiNonPosizionati() {
        System.out.println("getVolumiNonPosizionati");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        List<Volume> expResult = null;
        List<Volume> result = instance.getVolumiNonPosizionati();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCopia method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertCopia() {
        System.out.println("insertCopia");
        Copia copia = null;
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 0;
        int result = instance.insertCopia(copia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCopia method, of class ManagerGestioneLibri.
     */
    @Test
    public void testRemoveCopia() {
        System.out.println("removeCopia");
        String idVolume = "";
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        boolean expResult = false;
        boolean result = instance.removeCopia(idVolume);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
