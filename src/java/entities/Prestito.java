/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Calendar;

public class Prestito {

    protected int numTessUtente;
    protected String numRegCopia;
    protected String numScafCopia;
    protected int posCopia;
    protected Calendar dataPrestito;
    protected Calendar dataRestituzione;

    public Prestito(int numTessUtente, String numRegCopia, String numScafCopia, int posCopia, Calendar dataPrestito, Calendar dataRestituzione) {
        this.numTessUtente = numTessUtente;
        this.numRegCopia = numRegCopia;
        this.numScafCopia = numScafCopia;
        this.posCopia = posCopia;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
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

    @Override
    public String toString() {
        return "Prestito{" + "numTessUtente=" + numTessUtente + ", numRegCopia=" + numRegCopia + ", numScafCopia=" + numScafCopia + ", posCopia=" + posCopia + ", dataPrestito=" + dataPrestito + ", dataRestituzione=" + dataRestituzione + '}';
    }

    
}
