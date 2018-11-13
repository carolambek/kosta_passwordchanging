/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kosta.pwat.ldap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.ejb.Stateless;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
//import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
//import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;
//import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;

// Imports for changing password
import javax.naming.directory.ModificationItem;
import javax.naming.directory.BasicAttribute;

/**
 *
 * @author Severin Münger (severin@kosta.ch) Created on Aug 1 2017. Modified on
 * Sep 2018 by Charles Lambelet (charles@kosta.ch)
 */
public class LDAPAuthenticator {

    private static final String[] userAttributes = {
        "uidNumber"
    };

    private static final String PW_FILE_PATH = "../config/config_file.txt";
    private static final String SERVER = "ldap://localhost:5678";
    private static final String BASE_USER = "ou=Users,dc=kosta,dc=ethz,dc=ch";
    private static final String BASE_ADMIN = "cn=admin,dc=kosta,dc=ethz,dc=ch";
    private static final Hashtable<String, Object> ENVIRONMENT = new Hashtable<>();

    static {
        ENVIRONMENT.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ENVIRONMENT.put("com.sun.jndi.ldap.read.timeout", "3000");
        ENVIRONMENT.put(Context.PROVIDER_URL, SERVER);
        ENVIRONMENT.put(Context.SECURITY_AUTHENTICATION, "simple");
    }

    public String read_pw_file(String file_path) throws NamingException, IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file_path)));
       String line;
       StringBuilder result= new StringBuilder();
       while ((line = br.readLine()) != null){
           result.append(line);
       }
       return result.toString();
    }

    public InitialLdapContext authenticate(String username, String password, String base) throws
            NamingException {
        Hashtable<String, Object> env = new Hashtable<>(ENVIRONMENT);

        if (base == BASE_USER) {
            env.put(Context.SECURITY_PRINCIPAL, "uid=" + username + "," + base);
        } else if (base == BASE_ADMIN) {
            env.put(Context.SECURITY_PRINCIPAL, base);
        }

        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            return new InitialLdapContext(env, null);
        } catch (AuthenticationException ex) {
            // Authentication failed, return null
            return null;
        } catch (NamingException ex) {
            // There was a problem doing authentication, log the error and throw exception
            Logger.getLogger(LDAPAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public boolean authenticate_user(String username, String password) throws NamingException {

        InitialLdapContext userLdapContext = new LDAPAuthenticator().authenticate(username, password, BASE_USER);
        if (userLdapContext == null) {
            System.out.println("Login userLdapContext failed.");
            return false;
        } else {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            //controls.setReturningAttributes(null);
            controls.setReturningAttributes(userAttributes);
            NamingEnumeration answer = userLdapContext.search(BASE_USER, "uid=" + username, controls);

            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                NamingEnumeration<? extends Attribute> enu = attrs.getAll();
                while (enu.hasMore()) {
                    System.out.println(enu.next());
                }
            }
        }
        return true;
    }

    public static void change_password(String username, String newPassword) throws NamingException, IOException {

        String pw = new LDAPAuthenticator().read_pw_file(PW_FILE_PATH);
        InitialLdapContext adminLdapContext = new LDAPAuthenticator().authenticate("admin", pw, BASE_ADMIN);
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", /*newpwd=*/ newPassword));
        adminLdapContext.modifyAttributes("uid=" + username + "," + BASE_USER, new ModificationItem[]{item});
    }

//    public static void main(String[] args) throws NamingException, IOException {
//
//        String pw_string = new LDAPAuthenticator().read_pw_file(PW_FILE_PATH);
//        System.out.println(pw_string);
//
//        boolean auth_user = new LDAPAuthenticator().authenticate_user("your_username", "your_password");
//        System.out.println(auth_user);
//        if (auth_user == true) {
//            change_password("your_username", "your_password");
//        }
//    }
}
