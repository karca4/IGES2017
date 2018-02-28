/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Manuale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import utils.DriverManagerConnectionPool;

/**
 *
 * @author carmi
 */
public class ManualeDAO extends AbstractDAO<Manuale>{
    private final String doInsertQuery = "INSERT INTO manuale(CodVolume,Categoria)" + "VALUES(?,?);";
    private final String doRetriveByTitoloQuery = "call ricercaManuale(?)";

    @Override
    public Manuale doRetriveById(Object... id) {

        String titolo = (String) id[0];
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByTitoloQuery);
            prst.setString(1, titolo);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Manuale manuale = null;
                if (rs.next()) {
                    manuale = new Manuale(rs.getString("CodVolume"), rs.getString("Titolo"), rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"), rs.getString("Categoria"));
                    
                }
                rs.close();
                return manuale;

            } catch (SQLException e) {
                con.rollback();
                return null;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<Manuale> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Manuale manuale) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            
            prst.setString(1,manuale.getCodice());
            prst.setString(2,manuale.getCategoria());
           
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
    public int doUpdate(Manuale entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    
}
