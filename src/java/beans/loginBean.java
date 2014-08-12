/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import Pojo.UsuarioScd;
import dao.DaoTUsuarioScd;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.ldap.LdapContext;
import org.primefaces.context.RequestContext;
import security.ActiveDirectory;
import security.ActiveDirectory.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *Clase Bean que guardara la informacion de los campos de
 * 
 * --------------*---------------*----------------------------
 * | Formulario  | Pagina XHTML  | FUNCION                   |
 * --------------*---------------*----------------------------
 * |formLogin    |  inicio.xhtml  | Pagina de login           |
 * --------------*---------------*----------------------------
 * Ejecuntado el metodo de Login contra el active directory
 * 
 * @author Ing. Luis Cevallos
 * @version 1.0.0
 * 
 * 
 * 
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    /**
     * Creates a new instance of loginBean
     */
    
    Session session;
    Transaction transaction;
    
    private UsuarioScd usuario_scd;    
    private String username;
    private String password;    
    private String apellidos;

    

    public loginBean() {
            this.usuario_scd = new UsuarioScd();
    }
    
    /**
     * Metodo que obtiene el usuario actual que ha ingresado al sistema de control de docencia.
     * @return El nombre del usuario
     * @since v 1.0.0
     */
    public String getUsername() {
        return username;
    }
    /**
     * Metodo que inserta el valor del nombre del usuario al bean
     * @param username String que contiene el nombre del usuario
     * @since v 1.0.0
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo que obtiene la contraseña actual que ha ingresado al sistema de control de docencia el usuario.
     * @return La contraseña del usuario
     * @since v 1.0.0
     */
    public String getPassword() {
        return password;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    /**
     * Metodo que inserta el valos del nombre del usuario al bean
     * @param username String que contiene el nombre del usuario
     * @since v 1.0.0
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioScd getUsuario_scd() {
        return usuario_scd;
    }

    public void setUsuario_scd(UsuarioScd usuario_scd) {
        this.usuario_scd = usuario_scd;
    }
    
    
    /**
     * Metodo valida la informacion de Login que viene desde el xhtml en el boton     * 
     * 
     * --------------*---------------*----------------------------
     * | Formulario  | Pagina XHTML  | ELEMENTO                   |
     * --------------*---------------*----------------------------
     * |formLogin    |  inicio.xhtml  | loginButton:commandButton  |
      * --------------*---------------*----------------------------
     * @param actionEvent es el evento click del formulario encapsula la informacion del mismo en un objeto
     * @since v 1.0.0
     */
    
    public String login(ActionEvent actionEvent) {
        
        String mensajeSalida="";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        String outcome=null;
        User usuarioactiveDirectory;
        
       
       if (username != null  && password != null && username.length()>0 && password.length()>0) 
            {
                 usuarioactiveDirectory=this.getUserActiveDirectory(username, password);
                 
                 if(usuarioactiveDirectory!=null)
                 {
                     this.getUsuarioScdxUsuario(username);
                        
                        if(this.usuario_scd!=null)
                        {
                            
                            mensajeSalida="Bienvenido: "+usuarioactiveDirectory.getCommonName()+" con rol "+this.usuario_scd.getRol();
                            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", mensajeSalida);
                            outcome="members";
                        }
                        else
                        {
                            
                           mensajeSalida="El usuario "+usuarioactiveDirectory.getDistinguishedName()+" No consta como usuario del sistema";
                           msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No autorizado", mensajeSalida); 
                          
                        }
                 }
                 else{
                     
                     msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales Invalidas");
                    
                 }
                     
             }
        else {
                       msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Campos en blanco intentelo de nuevo");
              }
        
       
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return outcome;
        
    }//fin del metodo Login
    
    
    public User getUserActiveDirectory(String user,String passw)
    {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        User usuario_conectado=null;
            
          try {
                LdapContext ctx = ActiveDirectory.getConnection(user, passw);
                usuario_conectado= ActiveDirectory.getUser(user, ctx);
                ctx.close();
              
          }
          catch(Exception ex){
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Credenciales Invalidas");
                FacesContext.getCurrentInstance().addMessage(null, msg);
          }
        return usuario_conectado;
    }//fin del metodo getUserActiveDirectory
    
    
    public void getUsuarioScdxUsuario(String usuario)
    {
         RequestContext context = RequestContext.getCurrentInstance(); 
         FacesMessage msg = null; 
         this.session=null;
         this.transaction=null;
         
               try {                 
         
                            this.session=HibernateUtil.getSessionFactory().openSession();
                            DaoTUsuarioScd daoTUsuarioScd= new DaoTUsuarioScd();
                            this.transaction=this.session.beginTransaction();
                            this.usuario_scd=daoTUsuarioScd.getByUsuario(session, usuario);
                            this.transaction.commit();
               }
               catch(Exception e)
               {
                     if(this.transaction!=null)
                        {
                            transaction.rollback();
                        }
            
                        e.printStackTrace();
                        msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Excepcion error", e.getMessage());
                        FacesContext.getCurrentInstance().addMessage(null, msg);
               
               }
      
    }
            
    
    
    
    
}
