/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Autore;
import entities.CasaEditrice;
import entities.Collana;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "inserimentoServlet", urlPatterns = {"/gestione/inserimentoLibro", "/gestione/inserimentoManuale",
                                                        "/gestione/inserimentoPeriodico"})
public class InserimentoDatiServlet extends HttpServlet {

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
        
        PrintWriter out=response.getWriter();
        List<CasaEditrice> caseEditrici; 
        List<Autore> autori;
        List<Collana> collane;
        ManagerGestioneLibri managerGestioneLibri = new ManagerGestioneLibri();
        
        caseEditrici = managerGestioneLibri.getCaseEditrici();
        /*
        out.println("STAMPO LE CASE EDITRICI PRESENTI NEL DB");
        for(CasaEditrice ce:caseEditrici)
            out.println("<br><br>"+ce.getDenominazione()+" - "+ce.getCitta());
        */
        
        autori = managerGestioneLibri.getAutori();
        /*
        out.println("<br><br><br>STAMPO GLI AUTORI PRESENTI NEL DB");
        autori.forEach((a) -> {
            out.println("<br><br>"+a.getCodFiscale()+" - "+a.getCognome()+" - "+a.getNome()+" - "+a.getDataDiNascita()+" - "+a.getCittaResidenza());
        });
        */
        
        collane = managerGestioneLibri.getCollane();
        
        request.setAttribute("caseEditrici", caseEditrici);
        request.setAttribute("autori", autori);
        request.setAttribute("collane", collane);
        
        String path="";
        switch(request.getServletPath()){
            case "/gestione/inserimentoLibro":
                path+="../gestioneLibri/inserimentoLibro.jsp";
                break;
            case "/gestione/inserimentoManuale":
                path+="../gestioneLibri/inserimentoManuale.jsp";
                break;
            case "/gestione/inserimentoPeriodico":
                path+="../gestioneLibri/inserimentoPeriodico.jsp";
                break;               
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);   
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
