/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Prestito;
import entities.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import utils.DriverManagerConnectionPool;


public class PrestitoDAO extends AbstractDAO<Prestito>{
    
    private final String doRetriveByNumTessQuery = "Call RicercaPrestitiUtente(?)";
    private final String doRetriveByNumTessInfoQuery = "select prestito.*, volume.* from copia join prestito on numeroRegistrazione = numRegCopia and numeroScaffale = numScafCopia and posizione = posCopia join volume on Codice = CodiceVolume where prestito.numTessUtente = ?";
    private final String doRetriveAll = "select prestito.*, volume.* from copia join prestito on numeroRegistrazione = numRegCopia and numeroScaffale = numScafCopia and posizione = posCopia join volume on Codice = CodiceVolume";
    private final String doDeleteQUery = "call restituzione(?)";
    private final String doInsertQuery = "call prestito(?,?,?,?)";
    
    
    public List<Prestito> doRetriveByNumTess(Object... id) {

        int numTess = (int) id[0];
        
        List<Prestito> prestiti = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByNumTessInfoQuery);
            prst.setInt(1, numTess);
            
            Date dataO = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY).getTime();
            long time = dataO.getTime();
            
            try {
                ResultSet rs = prst.executeQuery();
                
                while (rs.next()) {
                    
                    Calendar dataPrestito = new GregorianCalendar();
                    dataPrestito.setTimeInMillis(rs.getDate("DataPrestito").getTime());
                    Calendar dataRestituzione = new GregorianCalendar();
                    dataRestituzione.setTimeInMillis(rs.getDate("DataRestituzione").getTime());
                    long dataR = rs.getDate("DataRestituzione").getTime();
                    Volume volume = new Volume(rs.getString("Codice"), rs.getString("Titolo"), rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"));
                    Prestito prestito = new Prestito(rs.getInt("NumTessUtente"), rs.getString("NumRegCopia"), rs.getString("NumScafCopia"), rs.getInt("PosCopia"), dataPrestito, dataRestituzione, volume);
                    prestito.setStatus(prestito.getStatus(time, dataR));
                    prestiti.add(prestito);
                }
                
                rs.close();
                
            } catch (SQLException e) {
                con.rollback();
                
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return prestiti;
        

    }
    
    @Override
    public Prestito doRetriveById(Object... id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestito> doRetriveAll() {

        List<Prestito> prestiti = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAll);
            
            Date dataO = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY).getTime();
            long time = dataO.getTime();
            
            try {
                ResultSet rs = prst.executeQuery();
                
                while (rs.next()) {
                    
                    Calendar dataPrestito = new GregorianCalendar();
                    dataPrestito.setTimeInMillis(rs.getDate("DataPrestito").getTime());
                    Calendar dataRestituzione = new GregorianCalendar();
                    dataRestituzione.setTimeInMillis(rs.getDate("DataRestituzione").getTime());
                    long dataR = rs.getDate("DataRestituzione").getTime();
                    Volume volume = new Volume(rs.getString("Codice"), rs.getString("Titolo"), rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"));
                    Prestito prestito = new Prestito(rs.getInt("NumTessUtente"), rs.getString("NumRegCopia"), rs.getString("NumScafCopia"), rs.getInt("PosCopia"), dataPrestito, dataRestituzione, volume);
                    prestito.setStatus(prestito.getStatus(time, dataR));
                    prestito.setUtente(new UtenteDAO().doRetriveById(rs.getInt("NumTessUtente")));
                    prestiti.add(prestito);
                }
                
                rs.close();
                
            } catch (SQLException e) {
                con.rollback();
                
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return prestiti;
       
    }

    @Override
    public int doUpdate(Prestito entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int doDelete(String numRegCopia) {
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doDeleteQUery);
                prst.setString(1, numRegCopia);
                
                prst.execute();  

                con.commit();
                prst.close();
              
                return 1;
                
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }    
        
    }

    @Override
    public int doInsert(Prestito p) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, p.getNumRegCopia());
                prst.setString(2, p.getNumScafCopia());
                prst.setInt(3, p.getPosCopia());
                prst.setInt(4, p.getNumTessUtente());
                
                System.out.println("Query: " + prst.toString());
                
                prst.execute();  

                con.commit();
                prst.close();
              
                return 1;
                
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }    
    }
    
}
