/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DriverManagerConnectionPool;

public class LibroDAO extends AbstractDAO<Libro>{
    
    private final String doRetriveByTitolo = "call ricercaLibro(?)";
    private final String doRetriveAll = "select volume.*, libro.tipo, libro.genere from libro join volume on volume.codice = libro.codvolume";
    private final String doRetriveAllWithPosition = "select volume.*, libro.tipo, libro.genere, copia.* from libro join volume on volume.codice = libro.codvolume join copia on volume.Codice = copia.CodiceVolume";
    
    @Override
    public Libro doRetriveById(Object... id) {
        
        String titolo = (String) id[0];
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByTitolo);
            prst.setString(1, titolo);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Libro book = null;
                if (rs.next()) {
                    book = new Libro(rs.getString("Genere"), rs.getString("Tipo"), rs.getString("CodVolume"), titolo, rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"));
                    String isbn = rs.getString("CodVolume");
                    book.setAutori(new AutoreDAO().doRetriveByLibro(isbn));
                    book.setCollana(new CollanaDAO().doRetriveById(isbn));
                    book.setCopie(new CopiaDAO().doRetriveAll());
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
    public int doInsert(Libro entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int doUpdate(Libro entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Libro> doRetriveAll() {
        
        List<Libro> libri = new ArrayList<>();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllWithPosition);

            try {
                ResultSet rs = prst.executeQuery();
                
                while (rs.next()) {
                    
                    Libro book = new Libro(rs.getString("genere"), rs.getString("tipo"), rs.getString("Codice"), rs.getString("Titolo"), rs.getInt("Edizione"), rs.getString("DataPubblicazione"), rs.getInt("DurataMaxPrestito"), rs.getString("Lingua"), rs.getString("DenominazioneEditore"), rs.getString("CittaEditore"));
                    String isbn = rs.getString("Codice");     
                 
                    book.setAutori(new AutoreDAO().doRetriveByLibro(isbn)); 
                    
                    //System.out.println("libro:  " + book.getTitolo() + "Lista autori: " + book.getAutori());
                    
                    book.setCollana(new CollanaDAO().doRetriveById(isbn));
                    book.setCopie(new CopiaDAO().doRetriveAllById(isbn));
                    libri.add(book);
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
        
        return libri;
        
    }

    
    
}
