/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Calendar;

public class Prestito {
    
    public static final String RESTITUITO ="Restituito";
    public static final String OLTRE_SCADENZA="Oltre scadenza";

    protected int numTessUtente;
    protected String numRegCopia;
    protected String numScafCopia;
    protected int posCopia;
    protected Calendar dataPrestito;
    protected Calendar dataRestituzione;
    protected Volume volume;
    protected Utente utente;
    private String status;

    public Prestito(int numTessUtente, String numRegCopia, String numScafCopia, int posCopia) {
        this.numTessUtente = numTessUtente;
        this.numRegCopia = numRegCopia;
        this.numScafCopia = numScafCopia;
        this.posCopia = posCopia;
    }
    
    public Prestito(int numTessUtente, String numRegCopia, String numScafCopia, int posCopia, Calendar dataPrestito, Calendar dataRestituzione) {
        this.numTessUtente = numTessUtente;
        this.numRegCopia = numRegCopia;
        this.numScafCopia = numScafCopia;
        this.posCopia = posCopia;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
        
    }
    
    public Prestito(int numTessUtente, String numRegCopia, String numScafCopia, int posCopia, Calendar dataPrestito, Calendar dataRestituzione, Volume volume) {
        this.numTessUtente = numTessUtente;
        this.numRegCopia = numRegCopia;
        this.numScafCopia = numScafCopia;
        this.posCopia = posCopia;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
        this.volume = volume;
        
    }

    public int getNumTessUtente() {
        return numTessUtente;
    }

    public void setNumTessUtente(int numTessUtente) {
        this.numTessUtente = numTessUtente;
    }

    public String getNumRegCopia() {
        return numRegCopia;
    }

    public void setNumRegCopia(String numRegCopia) {
        this.numRegCopia = numRegCopia;
    }

    public String getNumScafCopia() {
        return numScafCopia;
    }

    public void setNumScafCopia(String numScafCopia) {
        this.numScafCopia = numScafCopia;
    }

    public int getPosCopia() {
        return posCopia;
    }

    public void setPosCopia(int posCopia) {
        this.posCopia = posCopia;
    }

    public Calendar getDataPrestito() {
        return dataPrestito;
    }

    public void setDataPrestito(Calendar dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public Calendar getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataRestituzione(Calendar dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public String getStatus(long dataOggi, long dataRest) {
        if(dataOggi > dataRest){
            return Prestito.OLTRE_SCADENZA;
        }else{
            return Prestito.RESTITUITO;
        }
    }
    
    public String getStato(){
        return status;
    }
    
    public String getDataPrestitoString(){
            return "" + this.getDataPrestito().get(Calendar.DAY_OF_MONTH) + "-" + (this.getDataPrestito().get(Calendar.MONTH) + 1) + "-" + this.getDataPrestito().get(Calendar.YEAR) + "";
    }
    
    public String getDataRestituzioneString(){
        return "" + this.getDataRestituzione().get(Calendar.DAY_OF_MONTH) + "-" + (this.getDataRestituzione().get(Calendar.MONTH) + 1) + "-" + this.getDataRestituzione().get(Calendar.YEAR) + "";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
   
    @Override
    public String toString() {
        if(volume != null){
            return "Prestito{" + volume.toString() + ", numTessUtente=" + numTessUtente + ", numRegCopia=" + numRegCopia + ", numScafCopia=" + numScafCopia + ", posCopia=" + posCopia + ", dataPrestito=" + dataPrestito + ", dataRestituzione=" + dataRestituzione + ", status: " + status + '}';
        }else if(volume != null && utente != null){
            return "Prestito{" + volume.toString() + ", utente: " + utente.toString() + ", numTessUtente=" + numTessUtente + ", numRegCopia=" + numRegCopia + ", numScafCopia=" + numScafCopia + ", posCopia=" + posCopia + ", dataPrestito=" + dataPrestito + ", dataRestituzione=" + dataRestituzione + ", status: " + status + '}';
        }else{
            return "Prestito{" + "numTessUtente=" + numTessUtente + ", numRegCopia=" + numRegCopia + ", numScafCopia=" + numScafCopia + ", posCopia=" + posCopia + ", dataPrestito=" + dataPrestito + ", dataRestituzione=" + dataRestituzione + ", status: " + status + '}';

        }
    }

    
}
