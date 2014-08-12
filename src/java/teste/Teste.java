/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import Pojo.ViInfoHorariosEkudemic;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 *
 * @author lcevallosc
 */
public class Teste {
    
    
    public static void main(String[] args) {
       try{
       
           Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction t=session.beginTransaction();

            String hql="select DISTINCT vista.id.facultad from ViInfoHorariosEkudemic AS vista";
            //Query query=session.createQuery(hql);
            //query.setParameter("usuarioParam", usuario);

         //   List<ViInfoHorariosEkudemic> lista=(List<ViInfoHorariosEkudemic>) session.createQuery(hql).list();
            List<String> lista=(List<String>)session.createQuery(hql).list();
            
            
            for(String it :lista)
            {
                JOptionPane.showMessageDialog(null,it );
                
                //JOptionPane.showMessageDialog(null, it.getId().getFacultad());
            }


            t.commit();
            session.close();
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
           JOptionPane.showMessageDialog(null, ex.getMessage());
       
       }
        
        
        
        
        
        
    }
    
}
