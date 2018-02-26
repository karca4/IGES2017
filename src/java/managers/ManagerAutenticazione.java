/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import DAO.AccountDAO;
import DAO.UtenteDAO;
import entities.Utente;
import entities.Account;
import javax.servlet.http.HttpSession;


public class ManagerAutenticazione {
    
    
    private AccountDAO accountDAO;
    private UtenteDAO utenteDAO;
    
    public ManagerAutenticazione() {
        this.accountDAO = new AccountDAO();
        this.utenteDAO = new UtenteDAO();
       
    }
    
    public ManagerAutenticazione(AccountDAO accountDAO, UtenteDAO utenteDAO) {
        this.accountDAO = accountDAO;
        this.utenteDAO= utenteDAO;
    }
    
    /**
     * Metodo che esegue il login di un utente all'interno del sistema.
     * @param email (String) indirizzo email dell'utente che vuole loggarsi,
     * @param password (String) password corrispondente all'email dell'utente che vuole loggarsi.
     * @return oggetto Utente corrispondente a chi si è loggato, null altrimenti.
     */
    public Utente login(String email,String password) {
        
        Account account = accountDAO.doRetriveById(email);
        
        if(account!=null) { //l'account è esistente
            
            if(password.equals(account.getPassword())) { //la password corrisponde
                
                Utente utente = utenteDAO.doRetriveByEmail(email);
                return utente;
            }
        }
        
        return null;
    }
    
    
    /**
     * Metodo che esegue un logout dell'utente collegato dal sistema.
     * @param session (HttpSession) la sessione nella quale l'utente è loggato
     * @return true se il logout è avvenuto con successo, false altrimenti
     */
    public boolean logout(HttpSession session){
        try {
            session.invalidate();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
