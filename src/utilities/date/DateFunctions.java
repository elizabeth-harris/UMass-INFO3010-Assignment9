/**
 * Static methods that can be used in any class that requires them
 */
package utilities.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JFrame;
import exceptionhandlers.ErrorPopup;

public class DateFunctions {

   public enum Month {
      Jan, 
      Feb, 
      Mar,
      Apr,
      May,
      Jun,
      Jul,
      Aug,
      Sep,
      Oct,
      Nov,
      Dec;    
   }
   
   /**
    * This method takes a Calendar date and returns it as a String in the format
    * M/d/yyyy.
    *
    * @param date the date to convert
    * @return the String representation of the date
    */
   public static String dateToString(Calendar date) {
      SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
      String dateString = null;
      if (date != null) {
         dateString = formatter.format(date.getTime());
      }
      return dateString;
   }

   public static Calendar stringToDate(String dateString) {
      Calendar calendarObject = Calendar.getInstance();
      
      try {
         SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy", Locale.ENGLISH);
         calendarObject.setTime(sdf.parse(dateString));
      } catch (ParseException exception) {
    	// DONE: Inform user that a date was not entered, so a default date will be used
    	  // new ErrorPopup(form, exp); pass dateString where form var is
    	  new ErrorPopup(new JFrame(), new Exception("Invalid date:  " + dateString + ".  Date will be defaulted to current date."));
      }
      return calendarObject;
   }

   /**
    * This method takes a Calendar date and returns it as a String in the format
    * yyyy_MM_dd_hh_mm_ss
    *
    * @param date the date to convert
    * @return the String representation of the date
    */
   public static String logFileDate(Calendar date) {
      SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
      String dateString = null;
      if (date != null) {
         dateString = formatter.format(date.getTime());
      }
      return dateString;
   }
           
   /**
    * This helper method takes advantage of the fact that the enumerated values
    * have an ordinal value associated with them.  Normally I would strongly 
    * discourage the use of the ordinal method but in this case, since the 
    * ordinal value of the months of the year will never change, it's safe to 
    * use it.  Of course, that also means that the data functions rely on data
    * classes that assume January is zero!
    * 
    * @param month
    * @return 
    */
   private static int convertMonth(String month) {
      return Month.valueOf(month).ordinal();
   }
}
