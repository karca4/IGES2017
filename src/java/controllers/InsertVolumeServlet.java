/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Volume;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.ManagerGestioneLibri;


@WebServlet(name = "InsertVolumeServlet", urlPatterns = {"/gestioneLibri/insert"})
public class InsertVolumeServlet extends HttpServlet {

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
        
        String message="";
        String codice = request.getParameter("codice");
        String titolo = request.getParameter("titolo");
        int edizione = Integer.parseInt(request.getParameter("edizione"));
        String dataPub = request.getParameter("dataPub");
        int durataMaxPrestito = Integer.parseInt(request.getParameter("durataMaxPrestito"));
        String lingua = request.getParameter("lingua");
        Volume volume = new Volume(codice, titolo, edizione, dataPub, durataMaxPrestito, lingua, null, null, null);
                
        ManagerGestioneLibri managerGestionelibri = new ManagerGestioneLibri();
        int index=managerGestionelibri.insertVolume(volume);
        message+="inserimento effettuato: "+index;
        System.out.println(message);
                
        response.sendRedirect("../skeleton-pages/index.jsp");
    }
    
    
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