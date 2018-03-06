/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Prestito;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.DriverManagerConnectionPool;

/**
 *
 * @author carmi
 */
public class ManagerPrestitiTest {
    private static Connection con;
    private static ManagerPrestiti managerPrestiti;
    
    public ManagerPrestitiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            con = new DriverManagerConnectionPool().getConnection();
            managerPrestiti = new ManagerPrestiti();
            managerPrestiti.inserisciNuovoPrestito("PREST", "Prest", 54, 2);
            System.out.println("\nUNIT TEST - ManagerPrestiti");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGestioneLibriTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        managerPrestiti.restituisciVolume("ddd");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of inserisciNuovoPrestito method, of class ManagerPrestiti.
     */
    @Test
    public void testInserisciNuovoPrestito() {
        System.out.println("inserisciNuovoPrestito");
        String numRegCopia = "ddd";
        String scaffale = "a";
        int posizione = 1;
        int tessera = 2;
        ManagerPrestiti instance = new ManagerPrestiti();
        String expResult = "correct";
        String result = instance.inserisciNuovoPrestito(numRegCopia, scaffale, posizione, tessera);
        assertEquals(expResult, result);
    }

    /**
     * Test of restituisciVolume method, of class ManagerPrestiti.
     */
    @Test
    public void testRestituisciVolume() {
        System.out.println("restituisciVolume");
        String numRegCopia = "PREST";
        ManagerPrestiti instance = new ManagerPrestiti();
        String expResult = "correct";
        String result = instance.restituisciVolume(numRegCopia);
        assertEquals(expResult, result);
    }

    /**
     * Test of cercaPrestitiDaRestituire method, of class ManagerPrestiti.
     */
    @Test
    public void testCercaPrestitiDaRestituire() {
        System.out.println("cercaPrestitiDaRestituire");
        ManagerPrestiti instance = new ManagerPrestiti();
        int expResult = 1;
        List<Prestito> result = instance.cercaPrestitiDaRestituire();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of cercaPrestitiUtente method, of class ManagerPrestiti.
     */
    @Test
    public void testCercaPrestitiUtente() {
        System.out.println("cercaPrestitiUtente - non implementato");
    }
    
}
