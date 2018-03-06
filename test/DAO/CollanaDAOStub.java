/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Collana;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carmi
 */
public class CollanaDAOStub extends CollanaDAO{

    @Override
    public List<Collana> doRetriveAll() {
        List<Collana> toReturn = new ArrayList();
        toReturn.add(new Collana("collana1"));
        toReturn.add(new Collana("collana2"));
        toReturn.add(new Collana("collana3"));
        toReturn.add(new Collana("collana4"));
        toReturn.add(new Collana("collana5"));
        
        return toReturn;
    }
    
}
