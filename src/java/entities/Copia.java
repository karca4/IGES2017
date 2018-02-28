/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class Copia {
    
    protected String numeroRegistrazione;
    protected String numeroScaffale;
    protected int posizione;
    protected String codiceVolume;
    protected boolean disponibilita;

    public Copia(String numeroRegistrazione, String numeroScaffale, int posizione, String codiceVolume, boolean disponibilita) {
        this.numeroRegistrazione = numeroRegistrazione;
        this.numeroScaffale = numeroScaffale;
        this.posizione = posizione;
        this.codiceVolume = codiceVolume;
        this.disponibilita = disponibilita;
    }

    public String getNumeroRegistrazione() {
        return numeroRegistrazione;
    }

    public void setNumeroRegistrazione(String numeroRegistrazione) {
        this.numeroRegistrazione = numeroRegistrazione;
    }

    public String getNumeroScaffale() {
        return numeroScaffale;
    }

    public void setNumeroScaffale(String numeroScaffale) {
        this.numeroScaffale = numeroScaffale;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    public String getCodiceVolume() {
        return codiceVolume;
    }

    public void setCodiceVolume(String codiceVolume) {
        this.codiceVolume = codiceVolume;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }
    
    public String disponibile() {
        if(isDisponibilita()){
            return "SI";
        }else{
            return "NO";
        }
    }

    @Override
    public String toString() {
        return "Copia{" + "numeroRegistrazione=" + numeroRegistrazione + ", numeroScaffale=" + numeroScaffale + ", posizione=" + posizione + ", codiceVolume=" + codiceVolume + ", disponibilita=" + disponibilita + '}';
    }

}
