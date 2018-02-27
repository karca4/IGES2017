/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

public class Libro extends Volume{
    
    protected String genere;
    protected String tipo;
    
    public Libro(String genere, String tipo, String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore) {
        super(codice, titolo, edizione, dataPubblicazione, durataMaxPrestito, lingua, denominazioneEditore, cittaEditore);
        this.genere = genere;
        this.tipo = tipo;
    }

    public Libro(String genere, String tipo, String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, List<Autore> autori) {
        super(codice, titolo, edizione, dataPubblicazione, durataMaxPrestito, lingua, denominazioneEditore, cittaEditore, autori);
        this.genere = genere;
        this.tipo = tipo;
    }

    public Libro(String genere, String tipo, String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, Collana collana, List<Autore> autori) {
        super(codice, titolo, edizione, dataPubblicazione, durataMaxPrestito, lingua, denominazioneEditore, cittaEditore, collana, autori);
        this.genere = genere;
        this.tipo = tipo;
    }
    
    public Libro(String codice, String titolo, List<Autore> autori) {
        super(codice, titolo, autori);
    }

    public Libro(String codice, String titolo, String genere, String tipo, List<Autore> autori) {
        super(codice, titolo, autori);
        this.genere = genere;
        this.tipo = tipo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void addAutore(Autore a){
        if(!autori.contains(a)){
            autori.add(a);
            a.addLibro(this);
        }
    }
    
    public void removeAutore(Autore a){
        if(autori.contains(a)){
            autori.remove(a);
            a.removeLibro(this);
        }
    }
    
    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;

    }

    public Collana getCollana() {
        return collana;
    }

    public void setCollana(Collana collana) {
        this.collana = collana;
    }
    
    

    @Override
    public String toString() {
             return "Libro{" + super.toString() + "genere=" + genere + ", tipo=" + tipo + '}';
    }
        
}