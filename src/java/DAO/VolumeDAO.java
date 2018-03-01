/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DriverManagerConnectionPool;

public class VolumeDAO extends AbstractDAO<Volume>{
    
    private final String doRetriveByCodice = "Select * from Volume where Codice = ?";
    private final String doRetriveByTitolo = "Select * from volume where titolo = ?";
    private final String doRetriveAllQuery = "Select * from volume";
    private final String doRetriveAllNoPositionedQuery = "SELECT * FROM `volume` WHERE Codice NOT IN (" +
                                                            "SELECT CodiceVolume FROM copia WHERE 1)";
    private final String doInsertQuery = "INSERT INTO volume(Codice,Titolo,Edizione,DataPubblicazione,DurataMaxPrestito,Lingua,DenominazioneEditore,CittaEditore)" + "VALUES(?,?,?,?,?,?,?,?);";
    
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
        List<Volume> volumi = new ArrayList<>();
        
         try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);            

            try (ResultSet rs = prst.executeQuery()) { 
                con.commit();
                while (rs.next()) {
                    Volume v = new Volume();
                    v.setCodice(rs.getString("Codice"));
                    v.setTitolo(rs.getString("Titolo"));
                    v.setEdizione(rs.getInt("Edizione"));
                    v.setDataPubblicazione(rs.getString("DataPubblicazione"));
                    v.setDurataMaxPrestito(rs.getInt("DurataMaxPrestito"));
                    v.setLingua(rs.getString("Lingua"));
                    v.setDenominazioneEditore(rs.getString("DenominazioneEditore"));
                    v.setCittaEditore(rs.getString("CittaEditore"));   
                    volumi.add(v);
                }
                rs.close();
                
                
            } catch (SQLException e ){
                con.rollback();
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
                return volumi;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return volumi;
    }

    @Override
    public int doInsert(Volume volume) {
        try{
            Connection con = DriverManagerConnectionPool.getConnection();            
            PreparedStatement prst = con.prepareStatement(doInsertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            
            prst.setString(1,volume.getCodice());
            prst.setString(2,volume.getTitolo());
            prst.setInt(3,volume.getEdizione());
            prst.setString(4,volume.getDataPubblicazione());
            prst.setInt(5,volume.getDurataMaxPrestito());
            prst.setString(6,volume.getLingua());
            prst.setString(7, volume.getDenominazioneEditore());
            prst.setString(8, volume.getCittaEditore());
           
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
    public int doUpdate(Volume entity) {
        return 0;
    }
    
    
    public List<Volume> doRetriveNoPositioned() {
        List<Volume> volumi = new ArrayList<>();
        
         try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveAllNoPositionedQuery);            

            try (ResultSet rs = prst.executeQuery()) { 
                con.commit();
                while (rs.next()) {
                    Volume v = new Volume();
                    v.setCodice(rs.getString("Codice"));
                    v.setTitolo(rs.getString("Titolo"));
                    v.setEdizione(rs.getInt("Edizione"));
                    v.setDataPubblicazione(rs.getString("DataPubblicazione"));
                    v.setDurataMaxPrestito(rs.getInt("DurataMaxPrestito"));
                    v.setLingua(rs.getString("Lingua"));
                    v.setDenominazioneEditore(rs.getString("DenominazioneEditore"));
                    v.setCittaEditore(rs.getString("CittaEditore"));   
                    volumi.add(v);
                }
                rs.close();
                
                
            } catch (SQLException e ){
                con.rollback();
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
                return volumi;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return volumi;
    }
    
    
    
    
}
