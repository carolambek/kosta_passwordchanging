/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kosta.pwat.beans;

/**
 *
 * @author carolek
 */
public class CredentialsHandler {

    private String username;
    private String password;

    /**
     * Creates a new instance of CredentialsHandler
     */
    public CredentialsHandler() {
        username = null;
        password = null;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
