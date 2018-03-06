/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.CollanaDAOStub;
import entities.Autore;
import entities.CasaEditrice;
import entities.Collana;
import entities.Copia;
import entities.Libro;
import entities.Manuale;
import entities.Periodico;
import entities.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Criterio;
import utils.DriverManagerConnectionPool;

/**
 *
 * @author carmi
 */
public class ManagerGestioneLibriTest {
    private static Connection con;
    private static ManagerGestioneLibri managerGestioneLibri;
    private static Volume volume;
    
    public ManagerGestioneLibriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            con = new DriverManagerConnectionPool().getConnection();
            managerGestioneLibri = new ManagerGestioneLibri();
            volume = new Volume();
            volume.setCodice("VVV");
            volume.setTitolo("yyy");
            volume.setEdizione(1);
            volume.setDataPubblicazione("25-12-2018");
            volume.setLingua("italiano");
            System.out.println("\nUNIT TEST - ManagerGestioneLibri");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGestioneLibriTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        PreparedStatement prst, prst2, prst3, prst4, prst5, prst6;
        try {
            prst = con.prepareStatement("delete from volume where Codice = ?");
            prst.setString(1, "VVV");
            prst.execute(); 
            
            prst2 = con.prepareStatement("delete from libro where CodVolume = ?");
            prst2.setString(1, "LLL");
            prst2.execute();
            
            prst3 = con.prepareStatement("delete from manuale where CodVolume = ?");
            prst3.setString(1, "MMM");
            prst3.execute();
            
            prst4 = con.prepareStatement("delete from periodico where CodVolume = ?");
            prst4.setString(1, "PPP");
            prst4.execute();
            
            prst5 = con.prepareStatement("delete from appartiene where CodVolume = ? and NomeCollana=? and NumeroOrdineCollana=?");
            prst5.setString(1, "Manuale");
            prst5.setString(2, "collana1");
            prst5.setInt(3, 10);
            prst5.execute();
            
            prst6 = con.prepareStatement("delete from scritto where CodVolume = ? and CodAutore=?");
            prst6.setString(1, "Manuale");
            prst6.setString(2, "AAA");;
            prst6.execute();
            con.commit();
            prst.close(); 
            DriverManagerConnectionPool.releaseConnection(con);
            System.out.println("Database cleared");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGestioneLibriTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        int expResult = 5;  //nello stub ho inserito 5 collane
        CollanaDAOStub cDAOS = new CollanaDAOStub();
        List<Collana> result = cDAOS.doRetriveAll();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of insertVolume method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertVolume() {
        System.out.println("insertVolume");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 1;
        int result = instance.insertVolume(volume);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertLibro method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertLibro() {
        System.out.println("insertLibro");
        Libro libro = new Libro();
        libro.setCodice("LLL");
        libro.setGenere("prova");
        libro.setTipo("a");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 1;
        int result = instance.insertLibro(libro);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertManuale method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertManuale() {
        System.out.println("insertManuale");
        Manuale manuale = new Manuale();
        manuale.setCodice("MMM");
        manuale.setCategoria("prova");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 1;
        int result = instance.insertManuale(manuale);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertPeriodico method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertPeriodico() {
        System.out.println("insertPeriodico");
        Periodico periodico = new Periodico();
        periodico.setCodice("PPP");
        periodico.setFrequenza("settimanale");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 1;
        int result = instance.insertPeriodico(periodico);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertVolumeInCollana method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertVolumeInCollana() {
        System.out.println("insertVolumeInCollana");
        Collana c = new Collana("collana1");
        c.setNumeroOrdineCollana("10");
        Volume v = new Volume();
        v.setCodice("Manuale");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        boolean expResult = true;
        boolean result = instance.insertVolumeInCollana(v, c);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertAutoreDiVolume method, of class ManagerGestioneLibri.
     */
    @Test
    public void testInsertAutoreDiVolume() {
        System.out.println("insertAutoreDiVolume");
        Volume v = new Volume();
        v.setCodice("Manuale");
        Autore a = new Autore();
        a.setCodFiscale("AAA");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        boolean expResult = true;
        boolean result = instance.insertAutoreDiVolume(v, a);
        assertEquals(expResult, result);
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
        int expResult = 2;  //al momento ho 2 case editrici nel DB
        List<CasaEditrice> result = instance.getCaseEditrici();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getAutori method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetAutori() {
        System.out.println("getAutori");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 4;  //al momento ho 4 autori nel DB
        List<Autore> result = instance.getAutori();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getVolumi method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetVolumi() {
        System.out.println("getVolumi");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 8;
        List<Volume> result = instance.getVolumi();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getCopie method, of class ManagerGestioneLibri.
     */
    @Test
    public void testGetCopie() {
        System.out.println("getCopie");
        ManagerGestioneLibri instance = new ManagerGestioneLibri();
        int expResult = 4;
        List<Copia> result = instance.getCopie();
        assertEquals(expResult, result.size());
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
