/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Interface.ITViInfoHorariosEkudemic;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author lcevallosc
 */
public class DaoViInfoHorarioEkudemic implements ITViInfoHorariosEkudemic {

    @Override
    public ArrayList<String> getFacultadesByAnio(Session session,Integer year) {
        String hql="select DISTINCT vista.id.facultad from ViInfoHorariosEkudemic AS vista";
        Query query =session.createQuery(hql);
        //query.setParameter("usuarioParam", usuario);
        return (ArrayList<String>)query.list();
        
    }
    
}
