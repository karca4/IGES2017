/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.LibroDAO;
import entities.Libro;
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
    
}
