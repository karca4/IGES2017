/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Libro;
import entities.Manuale;
import entities.Periodico;
import utils.*;
import managers.ManagerGestioneLibri;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CercaLibroServlet", urlPatterns = {"/gestioneLibri/cercaLibro"})
public class CercaLibroServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String message;
        String searchKey = request.getParameter("searchKey");
        String criterioName = request.getParameter("criterio");
        String criterioName2 = request.getParameter("criterioManuale");
        String criterioName3 = request.getParameter("criterioPeriodico");
        
        Collection<Libro> libri = new ArrayList<>();
        Collection<Manuale> manuali = new ArrayList<>();
        Collection<Periodico> periodici = new ArrayList<>();
        
        Criterio criterio = null;
        
        ManagerGestioneLibri managerLibri = new ManagerGestioneLibri();
        
        RequestDispatcher dispatcher = null;
        
        if(criterioName!= null){
        
            if (criterioName.equalsIgnoreCase("titolo")) {

                System.out.println("Cerca per titolo");

               criterio = new CriterioPerTitolo(searchKey);

            } else if (criterioName.equalsIgnoreCase("autore")) {

                criterio = new CriterioPerAutore(searchKey);

            } else if (criterioName.equals("editore")) {

                criterio = new CriterioPerEditore(searchKey);

            } else if (criterioName.equals("isbn")) {

                criterio = new CriterioPerIsbn(searchKey);   

            } else if (criterioName.equals("tipo")) {

                criterio = new CriterioPerTipo(searchKey);   

            } else {
                //YOU SHOULD NOT BE HERE!
            }
            
            libri = managerLibri.cercaLibro(criterio);

            if (libri.isEmpty()) {
                message = "Nessun libro corrispondente alla tua ricerca.";
            } else {
                message = "correct";
            }

            request.setAttribute("message", message);
            request.setAttribute("libri", libri);
            
            dispatcher = request.getRequestDispatcher("cercaLibro.jsp");

        }else if(criterioName2 != null){//manuale
            
            criterio = new CriterioPerManuale(searchKey);
            
            manuali = managerLibri.cercaManuale(criterio);

            if (manuali.isEmpty()) {
                message = "Nessun manuale corrispondente alla tua ricerca.";
            } else {
                message = "correct";
            }

            request.setAttribute("message", message);
            request.setAttribute("manuali", manuali);
            
            dispatcher = request.getRequestDispatcher("cercaManuale.jsp");
            
        }else if(criterioName3 != null){//periodico
            
            criterio = new CriterioPerPeriodico(searchKey);
            
            periodici = managerLibri.cercaPeriodico(criterio);

            if (periodici.isEmpty()) {
                message = "Nessun periodico corrispondente alla tua ricerca.";
            } else {
                message = "correct";
            }

            request.setAttribute("message", message);
            request.setAttribute("periodici", periodici);
            
            dispatcher = request.getRequestDispatcher("cercaPeriodico.jsp");
            
        } else {
            //YOU SHOULD NOT BE HERE!
            System.out.println("Non devi essere qui!");
        }
        
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
