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
public class NewCredentialsHandler {
    
    private CredentialsHandler credentialsHandler;
    private String new_password1;
    private String new_password2;

    /**
     * @return the credentialsHandler
     */
    public CredentialsHandler getCredentialsHandler() {
        return credentialsHandler;
    }

    /**
     * @param credentialsHandler the credentialsHandler to set
     */
    public void setCredentialsHandler(CredentialsHandler credentialsHandler) {
        this.credentialsHandler = credentialsHandler;
    }

    /**
     * @return the new_password1
     */
    public String getNew_password1() {
        return new_password1;
    }

    /**
     * @param new_password1 the new_password1 to set
     */
    public void setNew_password1(String new_password1) {
        this.new_password1 = new_password1;
    }

    /**
     * @return the new_password2
     */
    public String getNew_password2() {
        return new_password2;
    }

    /**
     * @param new_password2 the new_password2 to set
     */
    public void setNew_password2(String new_password2) {
        this.new_password2 = new_password2;
    }
    
}
