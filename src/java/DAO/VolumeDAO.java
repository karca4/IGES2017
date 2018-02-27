/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Libro;
import entities.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import utils.DriverManagerConnectionPool;

public class VolumeDAO extends AbstractDAO<Volume>{
    
    private final String doRetriveByCodice = "Select * from Volume where Codice = ?";
    private final String doRetriveByTitolo = "Select * from volume where titolo = ?";
    private final String doRetriveAll = "Select * from volume";
    private final String doInsertQuery = "";
    private final String doInsertAutoreQuery = "";
    private final String doUpdateQuery = "";

    @Override
    public Volume doRetriveById(Object... id) {
        
        String isbn = (String) id[0];
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByCodice);
            prst.setString(1, isbn);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Volume book = null;
                if (rs.next()) {
                    book = new Volume(rs.getString("Codice"), rs.getString("Titolo"), rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"));
                    book.setAutori(new AutoreDAO().doRetriveByLibro(isbn));
                }
                rs.close();
                return book;

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
    public List<Volume> doRetriveAll() {
        return null;
    }

    @Override
    public int doInsert(Volume entity) {
        return 0;
    }

    @Override
    public int doUpdate(Volume entity) {
        return 0;
    }
    
    
    
    
}
