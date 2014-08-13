/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import dao.DaoViInfoHorarioEkudemic;
import java.util.ArrayList;
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
public class horadocencia {

    /**
     * Creates a new instance of horadocencia
     */
    private List<String> comboFacultades;
    private String facultadSeleccionada;
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

    public void setComboFacultades(List<String> comboFacultades) {
        this.comboFacultades = comboFacultades;
        
    }

    public void setFacultadSeleccionada(String facultadSeleccionada) {
        this.facultadSeleccionada = facultadSeleccionada;
    }

    public void FechaInicialChange(SelectEvent event)
    {
        Date date = (Date) event.getObject();
        
        DateTime jodaTime = new DateTime(date);
        System.out.println("Este es el año men pilas "+jodaTime.getYear());
        
    }
    
}
