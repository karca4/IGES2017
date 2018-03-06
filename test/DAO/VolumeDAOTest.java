/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class VolumeDAOTest {
    private static Connection con;
    private static Volume volume;
    
    public VolumeDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            con = DriverManagerConnectionPool.getConnection();
            volume = new Volume();
            volume.setCodice("ZZZ");
            volume.setTitolo("zzz");
            volume.setEdizione(1);
            volume.setDataPubblicazione("25-12-2018");
            volume.setLingua("italiano");
            System.out.println("UNIT TEST - VolumeDAO");
        } catch (SQLException ex) {
            Logger.getLogger(VolumeDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        PreparedStatement prst;
        try {
            prst = con.prepareStatement("delete from volume where Codice = ?");
            prst.setString(1, volume.getCodice());
            prst.execute(); 
            con.commit();
            prst.close(); 
            DriverManagerConnectionPool.releaseConnection(con);
            System.out.println("Database cleared");
        } catch (SQLException ex) {
            Logger.getLogger(VolumeDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doRetriveById method, of class VolumeDAO.
     */
    @Test
    public void testDoRetriveById() {
        System.out.println("doRetriveById");
        VolumeDAO instance = new VolumeDAO();
        Volume expResult = new Volume("Libro", "a", 23, "25-12-2018", 1, "inglese", null, null);
        Volume result = instance.doRetriveById("Libro");
        assertEquals(expResult, result);
    }

    /**
     * Test of doRetriveAll method, of class VolumeDAO.
     */
    @Test
    public void testDoRetriveAll() {
        System.out.println("doRetriveAll");
        VolumeDAO instance = new VolumeDAO();
        int expResult = 8;    //attualmente nel DB ho caricato 8 volumi
        List<Volume> result = instance.doRetriveAll();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of doInsert method, of class VolumeDAO.
     */
    @Test
    public void testDoInsert() {
        System.out.println("doInsert");
        VolumeDAO instance = new VolumeDAO();
        int expResult = 1;
        int result = instance.doInsert(volume);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class VolumeDAO.
     */
    @Test
    public void testDoUpdate() {
        System.out.println("doUpdate - non implementato nel DAO");
    }

    /**
     * Test of doRetriveNoPositioned method, of class VolumeDAO.
     */
    @Test
    public void testDoRetriveNoPositioned() {
        System.out.println("doRetriveNoPositioned");
        VolumeDAO instance = new VolumeDAO();
        int expResult = 4;      //attualmente nel DB ho 4 volumi che non sono ancora posizionati
        List<Volume> result = instance.doRetriveNoPositioned();
        assertEquals(expResult, result.size());
    }
    
}
