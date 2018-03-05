/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import java.util.Objects;

public class Volume {
    
    protected String codice;
    protected String titolo;
    protected int edizione;
    protected String dataPubblicazione;
    protected int durataMaxPrestito;
    protected String lingua;
    protected String denominazioneEditore;
    protected String cittaEditore;
    protected Collana collana;
    protected List<Autore> autori;
    protected List<Copia> copie;

    public Volume(){
        
    }
    
    public Volume(String codice, String titolo, List<Autore> autori) {
        this.codice = codice;
        this.titolo = titolo;
        this.autori = autori;
    }

    public Volume(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, List<Autore> autori) {
        this.codice = codice;
        this.titolo = titolo;
        this.edizione = edizione;
        this.dataPubblicazione = dataPubblicazione;
        this.durataMaxPrestito = durataMaxPrestito;
        this.lingua = lingua;
        this.denominazioneEditore = denominazioneEditore;
        this.cittaEditore = cittaEditore;
        this.autori = autori;
    }
    
    public Volume(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, Collana collana, List<Autore> autori) {
        this.codice = codice;
        this.titolo = titolo;
        this.edizione = edizione;
        this.dataPubblicazione = dataPubblicazione;
        this.durataMaxPrestito = durataMaxPrestito;
        this.lingua = lingua;
        this.denominazioneEditore = denominazioneEditore;
        this.cittaEditore = cittaEditore;
        this.collana = collana;
        this.autori = autori;
    }
    
    public Volume(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore, Collana collana, List<Autore> autori, List<Copia> copie) {
        this.codice = codice;
        this.titolo = titolo;
        this.edizione = edizione;
        this.dataPubblicazione = dataPubblicazione;
        this.durataMaxPrestito = durataMaxPrestito;
        this.lingua = lingua;
        this.denominazioneEditore = denominazioneEditore;
        this.cittaEditore = cittaEditore;
        this.collana = collana;
        this.autori = autori;
        this.copie = copie;
    }

    public Volume(String codice, String titolo, int edizione, String dataPubblicazione, int durataMaxPrestito, String lingua, String denominazioneEditore, String cittaEditore) {
        this.codice = codice;
        this.titolo = titolo;
        this.edizione = edizione;
        this.dataPubblicazione = dataPubblicazione;
        this.durataMaxPrestito = durataMaxPrestito;
        this.lingua = lingua;
        this.denominazioneEditore = denominazioneEditore;
        this.cittaEditore = cittaEditore;
    }

    public Collana getCollana() {
        return collana;
    }

    public void setCollana(Collana collana) {
        this.collana = collana;
    }

    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }
    
    

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getEdizione() {
        return edizione;
    }

    public void setEdizione(int edizione) {
        this.edizione = edizione;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(String dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public int getDurataMaxPrestito() {
        return durataMaxPrestito;
    }

    public void setDurataMaxPrestito(int durataMaxPrestito) {
        this.durataMaxPrestito = durataMaxPrestito;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getDenominazioneEditore() {
        return denominazioneEditore;
    }

    public void setDenominazioneEditore(String denominazioneEditore) {
        this.denominazioneEditore = denominazioneEditore;
    }

    public String getCittaEditore() {
        return cittaEditore;
    }

    public void setCittaEditore(String cittaEditore) {
        this.cittaEditore = cittaEditore;
    }

    public List<Copia> getCopie() {
        return copie;
    }

    public void setCopie(List<Copia> copie) {
        this.copie = copie;
    }
    
    
    @Override
    public String toString() {
        if(collana!= null && autori!= null){
            return "Volume{" + "codice=" + codice + ", titolo=" + titolo + ", edizione=" + edizione + ", dataPubblicazione=" + dataPubblicazione + ", durataMaxPrestito=" + durataMaxPrestito + ", lingua=" + lingua + ", denominazioneEditore=" + denominazioneEditore + ", cittaEditore=" + cittaEditore + ", autori=" + autori.toString() + ", collana= " + collana.toString() + "copie= " + copie.toString() + '}';
        }else if(copie!=null){
                        return "Volume{" + "codice=" + codice + ", titolo=" + titolo + ", edizione=" + edizione + ", dataPubblicazione=" + dataPubblicazione + ", durataMaxPrestito=" + durataMaxPrestito + ", lingua=" + lingua + ", denominazioneEditore=" + denominazioneEditore + ", cittaEditore=" + cittaEditore + ", autori=" + autori.toString() + "copie= " + copie.toString() + '}';

        }else{
            return "Volume{" + "codice=" + codice + ", titolo=" + titolo + ", edizione=" + edizione + ", dataPubblicazione=" + dataPubblicazione + ", durataMaxPrestito=" + durataMaxPrestito + ", lingua=" + lingua + ", denominazioneEditore=" + denominazioneEditore + ", cittaEditore=" + cittaEditore + '}';
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.edizione != other.edizione) {
            return false;
        }
        if (this.durataMaxPrestito != other.durataMaxPrestito) {
            return false;
        }
        if (!Objects.equals(this.codice, other.codice)) {
            return false;
        }
        if (!Objects.equals(this.titolo, other.titolo)) {
            return false;
        }
        if (!Objects.equals(this.dataPubblicazione, other.dataPubblicazione)) {
            return false;
        }
        if (!Objects.equals(this.lingua, other.lingua)) {
            return false;
        }
        if (!Objects.equals(this.denominazioneEditore, other.denominazioneEditore)) {
            return false;
        }
        if (!Objects.equals(this.cittaEditore, other.cittaEditore)) {
            return false;
        }
        return true;
    }
    
    
    
}
