/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author wondercode
 */
public class DateUtil {
    public static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat formatter2=new SimpleDateFormat("dd/MM/yyyy");
    public static Date getDate(String inputDate){
        
        Date date=null;
        try{
            date=formatter.parse(inputDate);
        } catch (ParseException ex) {
            try {
                date=formatter2.parse(inputDate);
            } catch (ParseException ex1) {
                
            }
        }
        return date;
    }
    public static String formatDate(Date date){
        return formatter2.format(date);
    }
}
