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
import java.util.List;
import utils.DriverManagerConnectionPool;

/**
 *
 * @author carmi
 */
public class PeriodicoDAO extends AbstractDAO<Periodico>{
    private final String doInsertQuery = "INSERT INTO periodico(CodVolume,Frequenza)" + "VALUES(?,?);";

    @Override
    public Periodico doRetriveById(Object... id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Periodico> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
