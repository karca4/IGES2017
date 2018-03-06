/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

public class Manuale extends Volume{
    private String categoria;
    
    public Manuale(){
        
    }
    
    public Manuale(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, String categoria) {
        super(codice, titolo, edizione, dataPubblicazione, durataMaxPrestito, lingua, denominazioneEditore, cittaEditore);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    

    @Override
    public String toString() {
        return "Manuale{" + super.toString() + "categoria=" + categoria + '}';
    }
    
    
    
}
