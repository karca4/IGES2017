/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Prestito;
import entities.Utente;
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
public class ManagerUtenteTest {
    private static Connection con;
    private static ManagerUtente managerUtente;
    
    public ManagerUtenteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            con = new DriverManagerConnectionPool().getConnection();
            managerUtente = new ManagerUtente();
            System.out.println("\nUNIT TEST - ManagerUtente");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGestioneLibriTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Test of getUtenteDaEmail method, of class ManagerUtente.
     */
    @Test
    public void testGetUtenteDaEmail() {
        System.out.println("getUtenteDaEmail");
        String email = "carmine@biblio.it";
        ManagerUtente instance = new ManagerUtente();
        int expResult = 2;
        Utente result = instance.getUtenteDaEmail(email);
        assertEquals(expResult, result.getId());
    }

    /**
     * Test of getPrestitiDiUtente method, of class ManagerUtente.
     */
    @Test
    public void testGetPrestitiDiUtente() {
        System.out.println("getPrestitiDiUtente");
        int numeroTessera = 2;
        ManagerUtente instance = new ManagerUtente();
        int expResult = 1;
        List<Prestito> result = instance.getPrestitiDiUtente(numeroTessera);
        assertEquals(expResult, result.size());
    }
    
}
