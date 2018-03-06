/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Manuale;

public class CriterioPerManuale implements Criterio {
    
    private String titoloManuale;
    
    public CriterioPerManuale(String titoloManuale) {
        this.titoloManuale = titoloManuale;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Manuale manuale = (Manuale) ob;
        
        if (manuale.getTitolo().toLowerCase().contains(titoloManuale.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
}