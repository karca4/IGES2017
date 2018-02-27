/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Libro;

public class CriterioPerTipo implements Criterio {
    
    private String tipo;
    
    public CriterioPerTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Libro book = (Libro) ob;
        
        if (book.getTipo().toLowerCase().contains(tipo.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
}
