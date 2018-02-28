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
public class Manuale extends Volume{
    private String categoria;
    
    public Manuale(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, String categoria) {
        super(codice, titolo, edizione, dataPubblicazione, durataMaxPrestito, lingua, denominazioneEditore, cittaEditore);
        this.categoria = categoria;
    }
    
    
    
}
