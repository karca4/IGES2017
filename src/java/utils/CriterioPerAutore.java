/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Autore;
import entities.Libro;

public class CriterioPerAutore implements Criterio{

    private String autore;

    public CriterioPerAutore(String autore) {
        this.autore = autore;
    }
    
    @Override
    public boolean isValid(Object ob) {
        
        
        Libro book = (Libro) ob;
     
        for(Autore a : book.getAutori()){
            if(a.getNome().toLowerCase().contains(autore.toLowerCase())){
                return true;
            }
        }
        
        return false;
    }
    
}
