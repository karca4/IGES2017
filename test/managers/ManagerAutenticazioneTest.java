/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Account;
import entities.Utente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
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
public class ManagerAutenticazioneTest {
    private static Connection con;
    
    public ManagerAutenticazioneTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            con = new DriverManagerConnectionPool().getConnection();
            System.out.println("\nUNIT TEST - ManagerAutenticazione");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGestioneLibriTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        DriverManagerConnectionPool.releaseConnection(con);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class ManagerAutenticazione.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "carmine@biblio.it";
        String password = "admin";
        ManagerAutenticazione instance = new ManagerAutenticazione();
        int expResult = 2;
        Utente result = instance.login(email, password);
        assertEquals(expResult, result.getId());
    }

    /**
     * Test of logout method, of class ManagerAutenticazione.
     */
    @Test
    public void testLogout() {
        System.out.println("logout - non implementato"); 
    }
    
}
