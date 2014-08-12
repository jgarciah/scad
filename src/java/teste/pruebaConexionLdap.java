package teste;


import javax.naming.ldap.LdapContext;
import security.ActiveDirectory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lcevallosc
 */
public class pruebaConexionLdap {
    
   
    public static void main(String[] args) {
        try {
            LdapContext ctx = ActiveDirectory.getConnection("lcevallosc", "Passw0rd");
            System.out.println("Aqui tooyyy me longoneee");
            ctx.close();
        } catch (Exception e) {
            //Failed to authenticate user!
            e.printStackTrace();
        }
    }
        
    
}
