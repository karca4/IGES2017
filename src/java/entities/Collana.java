/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Collana {
    
    protected String nomeCollana;
    protected String numeroOrdineCollana;

    public Collana(String nomeCollana, String numOrdine) {
        this.nomeCollana = nomeCollana;
        this.numeroOrdineCollana = numOrdine;
    }

    public String getNumeroOrdineCollana() {
        return numeroOrdineCollana;
    }

    public void setNumeroOrdineCollana(String numeroOrdineCollana) {
        this.numeroOrdineCollana = numeroOrdineCollana;
    }

    public String getNomeCollana() {
        return nomeCollana;
    }

    public void setNomeCollana(String nomeCollana) {
        this.nomeCollana = nomeCollana;
    }

    @Override
    public String toString() {
        return "Collana{" + "nomeCollana=" + nomeCollana + ", numeroOrdineCollana=" + numeroOrdineCollana + '}';
    }

}
