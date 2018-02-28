/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Collana;
import entities.Libro;
import entities.Manuale;
import entities.Periodico;
import entities.Volume;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.ManagerGestioneLibri;


@WebServlet(name = "InsertVolumeServlet", urlPatterns = {"/gestione/insert"})
public class CaricaVolumeServlet extends HttpServlet {

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
        
        String scelta = request.getParameter("scelta");   //mantiene il tipo del volume scelto
        String codice = request.getParameter("codice");
        String titolo = request.getParameter("titolo");
        int edizione = Integer.parseInt(request.getParameter("edizione"));
        String dataPub = request.getParameter("dataPub");
        int durataMaxPrestito = Integer.parseInt(request.getParameter("durataMaxPrestito"));
        String lingua = request.getParameter("lingua");
        String casaEditrice = request.getParameter("casaEditrice");
        String casaEdNome=null;
        String casaEdCitta=null;
        if(casaEditrice!=null){
            String casaEd[] = casaEditrice.split(" - ");
            casaEdNome = casaEd[0];
            casaEdCitta = casaEd[1];
        }
        
        Volume volume = new Volume(codice, titolo, edizione, dataPub, durataMaxPrestito, lingua, casaEdNome, casaEdCitta);
        ManagerGestioneLibri managerGestionelibri = new ManagerGestioneLibri();
        int index=managerGestionelibri.insertVolume(volume);
        System.out.println("inserimento volume effettuato: "+index);
        
        switch(scelta){
            case "libro":
                String genere = request.getParameter("genere");
                String tipo = request.getParameter("tipo"); 
                Libro libro = new Libro(genere, tipo, codice, titolo, edizione, dataPub, durataMaxPrestito, lingua, casaEdNome, casaEdCitta);
                
                index=managerGestionelibri.insertLibro(libro);
                System.out.println("inserimento libro effettuato: "+index);
                break;
            case "manuale":
                String categoria = request.getParameter("categoria");
                Manuale manuale = new Manuale(codice, titolo, edizione, dataPub, durataMaxPrestito, lingua, casaEdNome, casaEdCitta, categoria);
                
                index=managerGestionelibri.insertManuale(manuale);
                System.out.println("inserimento manuale effettuato: "+index);
                break;
            case "periodico":
                String frequenza = request.getParameter("frequenza"); 
                Periodico periodico = new Periodico(codice, titolo, edizione, dataPub, durataMaxPrestito, lingua, casaEdNome, casaEdCitta, frequenza);
                
                index=managerGestionelibri.insertPeriodico(periodico);
                System.out.println("inserimento peridico effettuato: "+index);
                break;
        }
        
        String collana = request.getParameter("collana");
        String numeroOrdineCollana = request.getParameter("numeroOrdineCollana");
        if((collana!=null)&&(numeroOrdineCollana!=null)){
            Collana c = new Collana(collana, numeroOrdineCollana);
            System.out.println("inserimento nella collana: "+managerGestionelibri.insertVolumeInCollana(volume, c));
        }
        
        String[] autori = request.getParameterValues("autori");
        
             
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
