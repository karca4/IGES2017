/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.PrestitoDAO;
import entities.Prestito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManagerPrestiti {
    
    private PrestitoDAO prestitoDAO;

    public ManagerPrestiti() {
        this.prestitoDAO = new PrestitoDAO();
    }

    public ManagerPrestiti(PrestitoDAO prestitoDAO) {
        this.prestitoDAO = prestitoDAO;
    }
    
/**
     * Metodo che ricerca i prestiti di un utente nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di prestiti, null se c'è qualche 
     * errore.
     */
    public Collection<Prestito> cercaPrestitiUtente(int codTessera) {
        
        List<Prestito> allPrestiti = prestitoDAO.doRetriveByNumTess(codTessera);
        
        System.out.println("AllPrestiti: " + allPrestiti);
        
        if(allPrestiti == null || allPrestiti.isEmpty()){
            return null;
        }
        
        return allPrestiti;
    }
    
}
