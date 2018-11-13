/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kosta.pwat.servlets;

//import ch.kosta.pwat.beans.CredentialsHandler;
//import ch.kosta.pwat.beans.NewCredentialsHandler;
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
public class ChangePWServlet extends HttpServlet {

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
        
        // create bean credentialsBean
//        CredentialsHandler credentialsBean = new CredentialsHandler();

        // create bean newCredentialsBean
//        NewCredentialsHandler newCredentialsBean = new NewCredentialsHandler();
        
        String new_password1 = request.getParameter("new_password1");
        String new_password2 = request.getParameter("new_password2");
        Object username = request.getSession().getAttribute("username");
        String message;
        
        // check if user tries to come back on change_password.jsp after effective password change
        if (username == null) {
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        
        // initialize bean with collected data from request
//        newCredentialsBean.setNew_password1(new_password1);
//        newCredentialsBean.setNew_password2(new_password2);
        
//        System.out.println(new_password1);
//        System.out.println(new_password2);
//        System.out.println(username);
        
        // add bean to request object
//        request.setAttribute("new_password1", new_password1);
//        request.setAttribute("new_password2", new_password2);
        
        try {
            // test that both entered new passwords are the same
            if (!new_password1.equals(new_password2)) {
                message = "Error - You didn't enter twice the same password, "
                        + "try again to enter your new password.";
                request.setAttribute("message_error", message);
                this.getServletContext().getRequestDispatcher("/change_password.jsp").forward(request, response);
            }
            // test that new password is not the same as username
            else if (new_password1.contains((String)username)) {
                message = "Error - Your password should not be "
                        + "the same as your username";
                request.setAttribute("message_error", message);
                this.getServletContext().getRequestDispatcher("/change_password.jsp").forward(request, response);
            }
            // test that new password has a minimum of 6 characters and a maximum of 15 characters
            else if (new_password1.length() > 15 || new_password1.length() < 6) {
                message = "Error - Your password should be more than "
                        + "6 and less than 15 characters in length.";
                request.setAttribute("message_error", message);
                this.getServletContext().getRequestDispatcher("/change_password.jsp").forward(request, response);
            }
            // else change password
            else {
                LDAPAuthenticator.change_password((String)username, new_password1);
                request.getSession().setAttribute("username", null);
                this.getServletContext().getRequestDispatcher("/success_change.jsp").forward(request, response);
            }
        } 

        catch (NamingException ex) {
            Logger.getLogger(AuthentificationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
