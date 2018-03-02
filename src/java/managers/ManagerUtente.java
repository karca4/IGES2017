/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.PrestitoDAO;
import DAO.UtenteDAO;
import entities.Prestito;
import entities.Utente;
import java.util.List;

/**
 *
 * @author carmi
 */
public class ManagerUtente {
    
    public Utente getUtenteDaEmail(String email){
        UtenteDAO uDAO = new UtenteDAO();
        return uDAO.doRetriveByEmail(email);
    }
    
    public List<Prestito> getPrestitiDiUtente(int numeroTessera){
        PrestitoDAO pDAO = new PrestitoDAO();
        return pDAO.doRetriveByNumTess(numeroTessera);
    }
}
