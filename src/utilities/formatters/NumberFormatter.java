/*
 * Use this utility class to format numbers
 */
package utilities.formatters;

import java.text.NumberFormat;

public class NumberFormatter {

   public static String formatCurrency(Double value) {
      NumberFormat formatter = NumberFormat.getCurrencyInstance();
      return formatter.format(value);
   }

}
