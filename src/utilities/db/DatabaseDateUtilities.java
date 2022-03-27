/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import utilities.date.DateFunctions;

/**
 * This class contains methods to format dates for use with database
 */
public class DatabaseDateUtilities {

    /**
     * This method will take a Calendar data object and convert it to a 
     * java sql date that can be inserted into a sql database
     */
    public static java.sql.Date getSqlFormattedDate(Calendar date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        java.sql.Date sqlDate = new java.sql.Date(date.getTimeInMillis());
        return sqlDate;
        
    }

    /**
     * This method will convert a java sql date to a Calendar object
     *
     */
    public static Calendar getJavaFormattedDate(java.sql.Date date) {

        try {

            // Format the sql date into a string we can use for a java date
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
            String stockdatestring = sdf.format(date);

            // Convert the string to a java Date
            Date javadate = sdf.parse(stockdatestring);

            // Create a calendar object and store the Java date
            Calendar calendarObject = Calendar.getInstance();
            calendarObject.setTime(javadate);
            return calendarObject;

        } catch (ParseException exp) {
            // something went wrong with the date so for now, return a null value
            return null;
        }
    }
}
