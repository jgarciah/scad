/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author lcevallosc
 */
public interface ITViInfoHorariosEkudemic {
   public ArrayList<String> getFacultadesByAnio( Session session, Integer year);           
    
}
