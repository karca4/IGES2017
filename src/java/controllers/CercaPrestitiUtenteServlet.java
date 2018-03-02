/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Libro;
import entities.Prestito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.ManagerPrestiti;

@WebServlet(name = "CercaPrestitiServlet", urlPatterns = {"/gestionePrestiti/cercaPrestiti"})
public class CercaPrestitiUtenteServlet extends HttpServlet{
    
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

        Collection<Prestito> prestiti = new ArrayList<>();
        String message;
        int searchKey = Integer.parseInt(request.getParameter("searchKey"));
        
        ManagerPrestiti managerPrestiti = new ManagerPrestiti();
        
        prestiti = managerPrestiti.cercaPrestitiUtente(searchKey);
        
        if(prestiti == null){
            message = "Nessun prestito associato all'utente.";
            
            System.out.println("Nessun prestito");
            
        }else{
            
            System.out.println("Qualche prestito");
            
            message = "correct";
        }       

        request.setAttribute("message", message);
        request.setAttribute("prestiti", prestiti);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("../skeleton-pages/homePrestiti.jsp"); 
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
