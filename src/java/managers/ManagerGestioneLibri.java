/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.AutoreDAO;
import DAO.CasaEditriceDAO;
import DAO.CollanaDAO;
import DAO.LibroDAO;
import DAO.ManualeDAO;
import DAO.PeriodicoDAO;
import DAO.VolumeDAO;
import entities.Autore;
import entities.CasaEditrice;
import entities.Collana;
import entities.Libro;
import entities.Manuale;
import entities.Periodico;
import entities.Volume;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.Criterio;

public class ManagerGestioneLibri {
    
    private LibroDAO libroDAO;
    
    public ManagerGestioneLibri() {
        this.libroDAO = new LibroDAO();
    }
    
    public ManagerGestioneLibri(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }
    
    public List<Collana> getCollane(){
        List<Collana> toReturn;
        CollanaDAO collanaDAO=new CollanaDAO();
        toReturn=collanaDAO.doRetriveAll();
        
        return toReturn;
    }
    
    public int insertVolume(Volume volume){
        VolumeDAO volumeDAO = new VolumeDAO();
        return volumeDAO.doInsert(volume);
    }
    
    public int insertLibro(Libro libro){
        LibroDAO lDAO = new LibroDAO();
        return lDAO.doInsert(libro);
    }
    
    public int insertManuale(Manuale manuale){
        ManualeDAO mDAO = new ManualeDAO();
        return mDAO.doInsert(manuale);
    }
    
    public int insertPeriodico(Periodico periodico){
        PeriodicoDAO pDAO = new PeriodicoDAO();
        return pDAO.doInsert(periodico);
    }
    
    
    /**
     * Metodo che ricerca un libro nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di libri, null se c'è qualche 
     * errore.
     */
    public Collection<Libro> cercaLibro(Criterio c) {
        int limit = 0;
        
        List<Libro> listToReturn = new ArrayList<>();
        List<Libro> allBooks = libroDAO.doRetriveAll();    
        

        if(allBooks == null || allBooks.isEmpty()){
            return null;
        }
        
        for (int i = 0; i < allBooks.size(); i++) {
            if(limit == 3 ){ //per ora limitato a 3 libri
                break;
            }
            if (c.isValid(allBooks.get(i))) {
                listToReturn.add(allBooks.get(i));
                limit++;
            }
        }

        return listToReturn;
    }
    
    
    public List<CasaEditrice> getCaseEditrici(){
        CasaEditriceDAO ceDAO = new CasaEditriceDAO();
        return ceDAO.doRetriveAll();
    }
    
    public List<Autore> getAutori(){
        AutoreDAO aDAO = new AutoreDAO();
        return aDAO.doRetriveAll();
    }
    
}
