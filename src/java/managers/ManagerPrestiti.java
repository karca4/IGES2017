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
    
    public String inserisciNuovoPrestito(String numRegCopia, String scaffale, int posizione, int tessera) {
        
        String toReturn = "";
        int valReturn = 0;
        
        Prestito p = new Prestito(tessera, numRegCopia, scaffale, posizione);
        
        valReturn = prestitoDAO.doInsert(p);
        
        if(valReturn == 1){
            toReturn = "correct";
        }else if(valReturn == -1){
            toReturn = "err";
        }
        
        if(toReturn == ""){
            return null;
        }
                
        return toReturn;
    }
    
    /**
     * Metodo che rende disponibile un volume, preso in prestito, nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Stringa che è un messaggio, null se c'è qualche 
     * errore.
     */
    public String restituisciVolume(String numRegCopia) {
        
        String toReturn = "";
        int valReturn=0;
        
        valReturn = prestitoDAO.doDelete(numRegCopia);
        
        if(valReturn == 1){
            toReturn = "correct";
        }else{
            toReturn = "err";
        }
        
        if(toReturn == "" ){
            return null;
        }
        
        return toReturn;
    }
    
    /**
     * Metodo che ricerca i volumi presi in prestito e che sono nello stato "Oltre Scadenza" nel database
     *
     * @param c il criterio di ricerca usato
     * @return una Collection di prestiti, null se c'è qualche 
     * errore.
     */
    public Collection<Prestito> cercaPrestitiDaRestituire() {
        
        List<Prestito> allPrestiti = new ArrayList<Prestito>();
        
        allPrestiti = prestitoDAO.doRetriveAll();
        
        if(allPrestiti == null || allPrestiti.isEmpty()){
            return null;
        }
        
        return allPrestiti;
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
        
        if(allPrestiti == null || allPrestiti.isEmpty()){
            return null;
        }
        
        return allPrestiti;
    }
    
}
