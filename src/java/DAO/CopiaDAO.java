/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Copia;
import entities.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DriverManagerConnectionPool;

public class CopiaDAO extends AbstractDAO<Copia>{

    private final String doRetriveById = "Call RicercaCopia(?)";

    @Override
    public Copia doRetriveById(Object... id) {
        
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    public List<Copia> doRetriveAllById(Object... id) {

           String codice = (String) id[0];
        List<Copia> copie = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveById);
            prst.setString(1, codice);
            
            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                
                while (rs.next()) {
                    
                    Copia copia = new Copia(rs.getString("NumeroRegistrazione"), rs.getString("NumeroScaffale"), rs.getInt("Posizione"), rs.getString("CodiceVolume"), rs.getBoolean("Disponibilità"));
                    copie.add(copia);
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
            return null;
        }
        
        return copie;
        
    }

    @Override
    public int doInsert(Copia entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Copia entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Copia> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
}
