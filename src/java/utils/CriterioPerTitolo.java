/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Libro;
import entities.Volume;

public class CriterioPerTitolo implements Criterio {
    
    private String titolo;
    
    public CriterioPerTitolo(String titolo) {
        this.titolo = titolo;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Volume book = (Volume) ob;
        
        if (book.getTitolo().toLowerCase().contains(titolo.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
}
