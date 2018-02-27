/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.CasaEditrice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DriverManagerConnectionPool;

/**
 *
 * @author carmi
 */
public class CasaEditriceDAO extends AbstractDAO<Object>{
    private final String doRetriveAllQuery = "SELECT * FROM casaeditrice";

    @Override
    public Object doRetriveById(Object... id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List doRetriveAll() {
        List<CasaEditrice> caseEditrici = new ArrayList<>();
        
         try (Connection con = DriverManagerConnectionPool.getConnection()) {
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);            

            try (ResultSet rs = prst.executeQuery()) { 
                con.commit();
                while (rs.next()) {
                    CasaEditrice ce = new CasaEditrice();
                    ce.setDenominazione(rs.getString("Denominazione"));
                    ce.setCitta(rs.getString("CittaDiAppartenenza"));
                    caseEditrici.add(ce);
                }
                rs.close();
            } catch (SQLException e ){
                con.rollback();
            } finally{
                DriverManagerConnectionPool.releaseConnection(con);                
                prst.close();
                return caseEditrici;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caseEditrici;
    }

    @Override
    public int doInsert(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
