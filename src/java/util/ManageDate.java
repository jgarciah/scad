/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lcevallosc
 */
public class ManageDate {
    
    
    public ArrayList<Integer> desgloseFecha(Date fechaIngreso)
    {
        ArrayList<Integer> salida=new ArrayList<Integer>();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIngreso);
        int year=calendar.get(Calendar.YEAR);
        
        int month=calendar.get(Calendar.MONTH); //Hay que sumarle uno al mes
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        
        salida.add(day);
        salida.add(month+1);
        salida.add(year);
        
        return salida;        
    }
}
