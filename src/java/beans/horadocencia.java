/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.DaoViInfoHorarioEkudemic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.primefaces.event.SelectEvent;
import util.HibernateUtil;

/**
 *
 * @author lcevallosc
 */
@Named(value = "horadocencia")
@ViewScoped
public class horadocencia implements Serializable{

    /**
     * Creates a new instance of horadocencia
     */
    private List<String> comboFacultades;
    private String facultadSeleccionada;
    private Date FechaInicial;
    private Date FechaFinal;
    
    
    Session session;
    Transaction transaction;
    
    
    public horadocencia() {
        this.comboFacultades= new ArrayList<>();
    }

    public List<String> getComboFacultades() {
        
        this.session=null;
        this.transaction=null;
        
        try{
            this.session=HibernateUtil.getSessionFactory().openSession();
            DaoViInfoHorarioEkudemic daoViInfoHorarioEkudemic=new DaoViInfoHorarioEkudemic();
            
            this.transaction=this.session.beginTransaction();
            
            this.comboFacultades=daoViInfoHorarioEkudemic.getFacultadesByAnio(session, 2013);
            
            this.transaction.commit();
            
            
        }
        catch(HibernateException ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
        
        }
        return comboFacultades;
    }

    public String getFacultadSeleccionada() {
        return facultadSeleccionada;
    }

    public Date getFechaInicial() {
        return FechaInicial;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    
    
    public void setFechaInicial(Date FechaInicial) {
        this.FechaInicial = FechaInicial;
    }

    public void setFechaFinal(Date FechaFinal) {
        this.FechaFinal = FechaFinal;
    }
   
    
    public void setComboFacultades(List<String> comboFacultades) {
        this.comboFacultades = comboFacultades;
        
    }

    public void setFacultadSeleccionada(String facultadSeleccionada) {
        this.facultadSeleccionada = facultadSeleccionada;
    }

    public void FechaInicialChange(SelectEvent event)
    {
        
        
        Date date = (Date) event.getObject();
       
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        
        int month=calendar.get(Calendar.MONTH); //Hay que sumarle uno al mes
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        
        
        
    }
    
    public void FechaFinalChange(SelectEvent event)
    {
        
        
        Date date = (Date) event.getObject();
        
       
        
        System.out.println("Esta es la fecha inicial: "+this.getFechaInicial().toString());
        System.out.println("Esta es la fecha Final: "+this.getFechaFinal().toString());
        //TODO: Aqui debo de buscar las facultades existentes en esa epoca
        
        
    }
    
}
