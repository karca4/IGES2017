/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

public class Utente {
    
    protected int numeroTessera;
    protected String nome;
    protected String cognome;
    protected Account account;
    //public static final String TIPO_ADMIN = "Admin";
    public static final String TIPO_BIBLIOTECARIO = "bibliotecario";
    public static final String TIPO_PERSONA = "persona";

    public Utente() {
        
    }

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Utente(String nome, String cognome, Account account) {
        this.nome = nome;
        this.cognome = cognome;
        this.account = account;
    }

    public Utente(int numeroTessera, String nome, String cognome, Account account) {
        this.numeroTessera = numeroTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.account = account;
    }
    
    public int getId() {
       // System.out.println("Id di persona dentro getID: "+id);
        return numeroTessera;
    }

    public void setId(int numeroTessera) {
        //System.out.println("Id dentro il setID: " + id);
        this.numeroTessera = numeroTessera;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
    
    /*
    public int getNumeroTessera() {
        return numeroTessera;
    }
    */
    


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
        final Utente other = (Utente) obj;
        if (this.numeroTessera != other.numeroTessera) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
        return true;
    }
        
    
}
