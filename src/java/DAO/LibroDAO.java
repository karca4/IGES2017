/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

public class LibroDAO extends AbstractDAO<VolumeDAO>{

    @Override
    public VolumeDAO doRetriveById(Object... id) {
        return null;
    }

    @Override
    public List<VolumeDAO> doRetriveAll() {
        return null;    
    }

    @Override
    public int doInsert(VolumeDAO entity) {
        return 0;
    }

    @Override
    public int doUpdate(VolumeDAO entity) {
        return 0;
    }

    
    
}
