/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**

 * Superclasse astratta di tutte le DAO, obbliga
 * l'implementazione dei metodi CRUD.
 * @author manuel
 * @param <T> L'entità per cui si sta creando la DAO
 */
public abstract class AbstractDAO<T> {
    /**
     * 
     * @param id
     * @return 
     */

    public abstract T doRetriveById( Object ... id );

    
    /**
     * 
     * @return 
     */
    public abstract List<T> doRetriveAll();
    
    /**
     * 
     * @param entity
     * @return 
     */
    public abstract int doInsert( T entity);
    
    
    /**
     * 
     * @param entity
     * @return 
     */
    public abstract int doUpdate(T entity);
    
    
}
