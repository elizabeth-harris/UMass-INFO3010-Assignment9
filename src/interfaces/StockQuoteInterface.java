/*
 * Interface for all the methods in the StockQuote class
 */
package interfaces;

import exceptionhandlers.InvalidDataException;
import java.util.Calendar;

public interface StockQuoteInterface {

    public boolean equals(Object obj);

    /**
     * Returns the quoteDate of the StockQuote object
     */
    public Calendar getQuoteDate();

    /**
     * Returns the tickerSymbol of the StockQuote object
     */
    public String getTickerSymbol();

    /**
     * Returns the value of the StockQuote object
     */
    public double getValue();

    /**
     * These methods compares two StockQuote objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
   public  int hashCode();

    /**
     * Sets the quoteDate of the StockQuote object
     */
   public  void setQuoteDate(Calendar quoteDate);

    /**
     * Sets the ticker symbol of the StockQuote object
     */
    public void setTickerSymbol(String tickerSymbol) throws InvalidDataException;

    /**
     * Sets the value of the StockQuote object
     */
    public void setValue(double value);

    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    public String toString();
    
}
