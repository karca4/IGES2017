/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author carmi
 */
public class Periodico extends Volume{
    private String frequenza;
    
    public Periodico(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, String frequenza) {
        super(codice, titolo, edizione, dataPubblicazione, durataMaxPrestito, lingua, denominazioneEditore, cittaEditore);
        this.frequenza = frequenza;
    }

    public String getFrequenza() {
        return frequenza;
    }

    public String getCodice() {
        return codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getEdizione() {
        return edizione;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public int getDurataMaxPrestito() {
        return durataMaxPrestito;
    }

    public String getLingua() {
        return lingua;
    }

    public String getDenominazioneEditore() {
        return denominazioneEditore;
    }

    public String getCittaEditore() {
        return cittaEditore;
    }

    public Collana getCollana() {
        return collana;
    }

    public List<Autore> getAutori() {
        return autori;
    }
    
        
}
