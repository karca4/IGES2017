/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import entities.Utente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.ManagerAutenticazione;

/**
 *
 * @author kliffom
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/autenticazione/login"})
public class LoginServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //System.out.println("mail:" + email + ", pass: " + password);
        
        ManagerAutenticazione managerAutenticazione = new ManagerAutenticazione();
        Utente utente = managerAutenticazione.login(email, password);
        
        //System.out.println("utente:" + utente.getAccount().toString());
        
        if(utente!=null) {
            message = "correct";
            
            if(utente.getAccount().getTipo().equals(Utente.TIPO_PERSONA)) { //se l'Utente che si è loggato è una Persona
                //System.out.println("Persona login.");
                request.getSession().setAttribute("persona", utente);
            }
            else if(utente.getAccount().getTipo().equals(Utente.TIPO_BIBLIOTECARIO)) { //se l'Utente che si è loggato è un Bibliotecario
                
                request.getSession().setAttribute("bibliotecario", utente);
            }
            else {
                System.err.println("You should not be here.");
                message = "C'è stato un errore imprevisto.";
            }
            
        }
        else { //l'utente è null, quindi non è possibile effettuare il login
            message = "Dati inseriti errati o non presenti.";
            request.setAttribute("message", message);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        
        response.sendRedirect("../skeleton-pages/index.jsp");
        
        
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
