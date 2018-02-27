/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Collana;
import entities.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import utils.DriverManagerConnectionPool;

public class CollanaDAO extends AbstractDAO<Collana>{

    private final String doRetriveByNome = "select * from collana where NomeCollana = ?";   
    private final String doRetriveByTitoloLibro = "select volume.*, appartiene.*\n" +
                                            "from appartiene join volume on appartiene.CodVolume = volume.Codice\n" +
                                            "where volume.titolo= ?;";
    private final String doRetriveByIsbnLibro = "select volume.*, appartiene.*\n" +
                                            "from appartiene join volume on appartiene.CodVolume = volume.Codice\n" +
                                            "where volume.Codice= ?;";

    @Override
    public Collana doRetriveById(Object... id) {

        String isbn = (String) id[0];
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIsbnLibro);
            prst.setString(1, isbn);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Collana collana = null;
                if (rs.next()) {
                    collana = new Collana(isbn, rs.getString("NumeroOrdineCollana"));
                   
                }
                rs.close();
                return collana;

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
    public List<Collana> doRetriveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doInsert(Collana entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Collana entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
            
    

    
}
