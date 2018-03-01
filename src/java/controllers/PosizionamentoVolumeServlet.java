/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Copia;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.ManagerGestioneLibri;

/**
 *
 * @author carmi
 */
@WebServlet(name = "PosizionamentoVolumeServlet", urlPatterns = {"/gestioneLibri/position"})
public class PosizionamentoVolumeServlet extends HttpServlet {

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
        
        String scelta = request.getParameter("scelta");
        String volumeId = request.getParameter("volumeId");
        String numRegistrazione = request.getParameter("numRegistrazione");
        String numScaffale = request.getParameter("numScaffale");
        int posizione = Integer.parseInt(request.getParameter("posizione"));
        
        ManagerGestioneLibri managerGestioneLibri = new ManagerGestioneLibri();
        Copia copia = new Copia();
        copia.setNumeroRegistrazione(numRegistrazione);
        copia.setNumeroScaffale(numScaffale);
        copia.setPosizione(posizione);
        copia.setCodiceVolume(volumeId);
        copia.setDisponibilita(true);
        System.out.println("Inserimento della copia: "+managerGestioneLibri.insertCopia(copia));
        
        response.sendRedirect(request.getContextPath()+"/gestione/posizionamento");
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
