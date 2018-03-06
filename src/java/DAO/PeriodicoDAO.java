/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Periodico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DriverManagerConnectionPool;

public class PeriodicoDAO extends AbstractDAO<Periodico>{
    private final String doInsertQuery = "INSERT INTO periodico(CodVolume,Frequenza)" + "VALUES(?,?);";
    private final String doRetriveAllQuery = "Select volume.*, periodico.frequenza from periodico join volume on volume.codice=periodico.codvolume";

    @Override
    public Periodico doRetriveById(Object... id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Periodico> doRetriveAll() {

        List<Periodico> periodici = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);
            
            try {
                ResultSet rs = prst.executeQuery();
                
                while (rs.next()) {
                    
                    Periodico periodico = new Periodico(rs.getString("Codice"), rs.getString("Titolo"), rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"), rs.getString("frequenza"));
                    String isbn = rs.getString("Codice");
                    periodico.setAutori(new AutoreDAO().doRetriveByLibro(isbn));
                    periodico.setCopie(new CopiaDAO().doRetriveAllById(isbn));
                    periodici.add(periodico);
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
        
        return periodici;

    }

    @Override
    public int doInsert(Periodico periodico) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            
            prst.setString(1,periodico.getCodice());
            prst.setString(2,periodico.getFrequenza());
           
            try{
                prst.execute();
                con.commit();
                ResultSet rs = prst.getGeneratedKeys();
                
                return 1;
            } catch(SQLException e){
                con.rollback();
                e.printStackTrace();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
            
        } catch(SQLException e){
            return -1;
        }
    }

    @Override
    public int doUpdate(Periodico entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
