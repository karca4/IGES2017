/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Periodico;

public class CriterioPerPeriodico implements Criterio {
    
    private String titoloPeriodico;
    
    public CriterioPerPeriodico(String titoloPeriodico) {
        this.titoloPeriodico = titoloPeriodico;
    }
    
    @Override
    public boolean isValid(Object ob) {
        Periodico periodico = (Periodico) ob;
        
        if (periodico.getTitolo().toLowerCase().contains(titoloPeriodico.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
}
