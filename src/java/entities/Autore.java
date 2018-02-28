/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Autore {
    
    protected String codFiscale;
    protected String nome;
    protected String cognome;
    protected String dataDiNascita;
    protected String cittaResidenza;

    public Autore() {
    }

    public Autore(String nome) {
        this.nome = nome;
        
    }

    public Autore(String codFiscale, String nome, String cognome) {
        this.codFiscale = codFiscale;
        this.nome = nome;
        this.cognome = cognome;
        
    }

    public Autore(String codFiscale, String nome, String cognome, String dataNascita, String cittaResidenza) {
        this.codFiscale = codFiscale;
        this.cognome = cognome;
        this.nome = nome;
        this.dataDiNascita = dataNascita;
        this.cittaResidenza = cittaResidenza;
        
    }
    
    
    public String getCodFiscale() {
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public void setCittaResidenza(String cittaResidenza) {
        this.cittaResidenza = cittaResidenza;
    }

   

    @Override
    public String toString() {
        return "Autore{" + "codFiscale=" + codFiscale + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita + ", cittaResidenza=" + cittaResidenza + '}';
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
        final Autore other = (Autore) obj;
        if (this.codFiscale != other.codFiscale) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
       
        return true;
    }
    
    
    
}
