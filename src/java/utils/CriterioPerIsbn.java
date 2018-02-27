/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Libro;

public class CriterioPerIsbn implements Criterio {

    private String isbn;

    public CriterioPerIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean isValid(Object ob) {
        Libro book = (Libro) ob;

        if (book.getCodice().equals(isbn)) {
            return true;
        }

        return false;
    }

}
