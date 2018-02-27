/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author carmi
 */
public class CasaEditrice {
    private String denominazione;
    private String citta;

    public CasaEditrice() {
    }
    
    public CasaEditrice(String denominazione, String citta) {
        this.denominazione = denominazione;
        this.citta = citta;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public String getCitta() {
        return citta;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
    
    
    
    
    
}
