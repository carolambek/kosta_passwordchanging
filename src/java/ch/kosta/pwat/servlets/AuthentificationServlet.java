/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kosta.pwat.servlets;

import ch.kosta.pwat.beans.CredentialsHandler;
import ch.kosta.pwat.ldap.LDAPAuthenticator;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carolek
 */
public class AuthentificationServlet extends HttpServlet {
    
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
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message;
        
        // create bean credentialsBean and initialize with collected data from request
        CredentialsHandler credentialsBean = new CredentialsHandler();
        credentialsBean.setUsername(username);
        credentialsBean.setPassword(password);
        
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(user_auth);
        
        // add bean to request object
//        request.setAttribute("username", username);
//        request.setAttribute("password", password);
        request.setAttribute("credentialsBean", credentialsBean);
        
        try {
            // user_auth is true if authentification was successful or false if it was not.
            boolean user_auth = new LDAPAuthenticator().authenticate_user(username, password);

            // if authentification is not successful
            if (!user_auth) {
                message = "Error - The username or password is incorrect. Verify that CAPS LOCK is not on, "
                        + "and then retype the current username and password.";
                request.setAttribute("message_error", message);
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            // if authentification is successful
            else {               
                message = "Dear " + username + ", your authentification was successful, you can now enter your new password.";
                request.setAttribute("message_success", message); // add to request context
                request.getSession().setAttribute("username", username); // add to session context
                this.getServletContext().getRequestDispatcher("/change_password.jsp").forward(request, response);
                request.getRequestDispatcher("/ChangePWServlet.java").forward(request, response);
            }
        } 
        
        catch (NamingException ex) {
            Logger.getLogger(AuthentificationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
