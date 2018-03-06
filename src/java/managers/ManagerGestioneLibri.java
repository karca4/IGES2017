/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.AutoreDAO;
import DAO.CasaEditriceDAO;
import DAO.CollanaDAO;
import DAO.CopiaDAO;
import DAO.LibroDAO;
import DAO.ManualeDAO;
import DAO.PeriodicoDAO;
import DAO.VolumeDAO;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.Criterio;
import utils.DriverManagerConnectionPool;

public class ManagerGestioneLibri {
    
    private LibroDAO libroDAO;
    private ManualeDAO manualeDAO;
    private PeriodicoDAO periodicoDAO;
    
    public ManagerGestioneLibri() {
        this.libroDAO = new LibroDAO();
        this.manualeDAO = new ManualeDAO();
        this.periodicoDAO = new PeriodicoDAO();
    }
    
    public ManagerGestioneLibri(LibroDAO libroDAO, ManualeDAO manualeDAO, PeriodicoDAO periodicoDAO) {
        this.libroDAO = libroDAO;
        this.manualeDAO = manualeDAO;
        this.periodicoDAO = periodicoDAO;
    }
    
    public List<Collana> getCollane(){
        List<Collana> toReturn;
        CollanaDAO collanaDAO=new CollanaDAO();
        toReturn=collanaDAO.doRetriveAll();
        
        return toReturn;
    }
    
    public int insertVolume(Volume volume){
        VolumeDAO volumeDAO = new VolumeDAO();
        return volumeDAO.doInsert(volume);
    }
    
    public int insertLibro(Libro libro){
        LibroDAO lDAO = new LibroDAO();
        return lDAO.doInsert(libro);
    }
    
    public int insertManuale(Manuale manuale){
        ManualeDAO mDAO = new ManualeDAO();
        return mDAO.doInsert(manuale);
    }
    
    public int insertPeriodico(Periodico periodico){
        PeriodicoDAO pDAO = new PeriodicoDAO();
        return pDAO.doInsert(periodico);
    }
    
     public boolean insertVolumeInCollana(Volume v, Collana c){
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement("INSERT INTO appartiene(CodVolume,NomeCollana,NumeroOrdineCollana)" + 
                                                            "VALUES(?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
            
            prst.setString(1, v.getCodice());
            prst.setString(2,c.getNomeCollana());
            prst.setInt(3,Integer.parseInt(c.getNumeroOrdineCollana()));
           
            try{
                prst.execute();
                con.commit();
                ResultSet rs = prst.getGeneratedKeys();
                
                return true;
            } catch(SQLException e){
                con.rollback();
                e.printStackTrace();
                return false;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException e){
            return false;
        }
    }
    
    public boolean insertAutoreDiVolume(Volume v, Autore a){
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement("INSERT INTO scritto(CodAutore,CodVolume)" + 
                                                            "VALUES(?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
            
            prst.setString(1, a.getCodFiscale());
            prst.setString(2, v.getCodice());
           
            try{
                prst.execute();
                con.commit();
                ResultSet rs = prst.getGeneratedKeys();
                
                return true;
            } catch(SQLException e){
                con.rollback();
                e.printStackTrace();
                return false;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException e){
            return false;
        }
    }
    
    /**
     * Metodo che ricerca un libro nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di libri, null se c'è qualche 
     * errore.
     */
    public Collection<Libro> cercaLibro(Criterio c) {
        
        List<Libro> listToReturn = new ArrayList<>();
        List<Libro> allBooks = libroDAO.doRetriveAll();
        
        if(allBooks == null || allBooks.isEmpty()){
            return null;
        }
        
        for (int i = 0; i < allBooks.size(); i++) {
            if (c.isValid(allBooks.get(i))) {
                listToReturn.add(allBooks.get(i));
            }
        }

        return listToReturn;
    }
    
    /**
     * Metodo che ricerca un manuale nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di manuali, null se c'è qualche 
     * errore.
     */
    public Collection<Manuale> cercaManuale(Criterio c) {
        
        List<Manuale> listToReturn = new ArrayList<>();
        List<Manuale> allManuale = manualeDAO.doRetriveAll();
        
        if(allManuale == null || allManuale.isEmpty()){
            return null;
        }
        
        for (int i = 0; i < allManuale.size(); i++) {
            
            if (c.isValid(allManuale.get(i))) {
                listToReturn.add(allManuale.get(i));
            }
        }

        return listToReturn;
    }
    
    /**
     * Metodo che ricerca un periodico nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di periodici, null se c'è qualche 
     * errore.
     */
    public Collection<Periodico> cercaPeriodico(Criterio c) {
        
        List<Periodico> listToReturn = new ArrayList<>();
        List<Periodico> allPeriodico = periodicoDAO.doRetriveAll();
        
        if(allPeriodico == null || allPeriodico.isEmpty()){
            return null;
        }
        
        for (int i = 0; i < allPeriodico.size(); i++) {
            
            if (c.isValid(allPeriodico.get(i))) {
                listToReturn.add(allPeriodico.get(i));
            }
        }

        return listToReturn;
    }
    
    
    public List<CasaEditrice> getCaseEditrici(){
        CasaEditriceDAO ceDAO = new CasaEditriceDAO();
        return ceDAO.doRetriveAll();
    }
    
    public List<Autore> getAutori(){
        AutoreDAO aDAO = new AutoreDAO();
        return aDAO.doRetriveAll();
    }
    
    public List<Volume> getVolumi() {
        VolumeDAO vDAO = new VolumeDAO();
        return vDAO.doRetriveAll();
    }
    
    public List<Copia> getCopie() {
        CopiaDAO cDAO = new CopiaDAO();
        return cDAO.doRetriveAll();
    }
    
    public List<Volume> getVolumiNonPosizionati(){
        VolumeDAO vDAO = new VolumeDAO();
        return vDAO.doRetriveNoPositioned();
    }
    
    public int insertCopia(Copia copia){
        CopiaDAO cDAO = new CopiaDAO();
        return cDAO.doInsert(copia);
    }
    
    public boolean removeCopia(String idVolume){
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement("DELETE FROM copia WHERE CodiceVolume=?",PreparedStatement.RETURN_GENERATED_KEYS);
            
            prst.setString(1, idVolume);
           
            try{
                prst.execute();
                con.commit();
                //ResultSet rs = prst.getGeneratedKeys();
                
                return true;
            } catch(SQLException e){
                con.rollback();
                e.printStackTrace();
                return false;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException e){
            return false;
        }
    }
    
}
