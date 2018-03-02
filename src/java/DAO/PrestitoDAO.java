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

    public List<Prestito> doRetriveByNumTess(Object... id) {

        int numTess = (int) id[0];
        
        List<Prestito> prestiti = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            //PreparedStatement prst = con.prepareStatement(doRetriveByNumTessQuery);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Prestito entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Prestito entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
