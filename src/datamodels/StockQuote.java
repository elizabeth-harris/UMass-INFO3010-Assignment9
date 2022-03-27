package datamodels;

/**
 * This class creates a StockQuote object
 */
import exceptionhandlers.MyFileException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import utilities.date.DateFunctions;

public class StockQuote implements Serializable {

    private double value;
    private String tickerSymbol;
    private Calendar quoteDate;

    /**
     * no-arg constructor
     */
    public StockQuote() {
    }

    /**
     * This constructor takes the value, tickerSymbol and quoteDate
     *
     * @param value The monetary value of the StockQuote object
     * @param tickerSymbol The stock ticker symbol of the StockQuote object
     * @param quoteDate The date of the StockQuote object
     */
    public StockQuote(double value, String tickerSymbol, Calendar quoteDate)
            throws MyFileException {
        if (tickerSymbol.isEmpty()) {
            throw new MyFileException("Creating StockQuote failed, ticker symbol not specified");
        }
        this.value = value;
        this.tickerSymbol = tickerSymbol;
        this.quoteDate = quoteDate;
    }

    /**
     * This constructor takes the value and tickerSymbol
     *
     * @param value The monetary value of the StockQuote object
     * @param tickerSymbol The stock ticker symbol of the StockQuote object
     */
    public StockQuote(double value, String tickerSymbol) throws MyFileException {
        if (tickerSymbol.isEmpty()) {
            throw new MyFileException("Creating StockQuote failed, ticker symbol not specified");
        }
        this.value = value;
        this.tickerSymbol = tickerSymbol;
    }

    /**
     * This constructor takes a StockQuote object and creates a copy
     *
     * @param stockQuote The StockQuote to be copied
     */
    public StockQuote(StockQuote stockQuote) {
        this.value = stockQuote.getValue();
        this.tickerSymbol = stockQuote.getTickerSymbol();
        this.quoteDate = stockQuote.getQuoteDate();
    }

    /**
     * Returns the value of the StockQuote object
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Returns the tickerSymbol of the StockQuote object
     */
    public String getTickerSymbol() {
        return this.tickerSymbol;
    }

    /**
     * Returns the quoteDate of the StockQuote object
     */
    public Calendar getQuoteDate() {
        return this.quoteDate;
    }

    /**
     * Sets the value of the StockQuote object
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the ticker symbol of the StockQuote object
     */
    public void setTickerSymbol(String tickerSymbol) throws MyFileException {
        if (tickerSymbol.isEmpty()) {
            throw new MyFileException("Setting ticker symbol failed, no ticker symbol specified");
        } else {
            this.tickerSymbol = tickerSymbol;
        }
    }

    /**
     * Sets the quoteDate of the StockQuote object
     */
    public void setQuoteDate(Calendar quoteDate)  {
       this.quoteDate = quoteDate;
    }

    /**
     * These methods compares two StockQuote objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.tickerSymbol);
        hash = 67 * hash + Objects.hashCode(this.quoteDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StockQuote other = (StockQuote) obj;
        if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        if (!Objects.equals(this.tickerSymbol, other.tickerSymbol)) {
            return false;
        }
        if (!Objects.equals(this.quoteDate, other.quoteDate)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
        return "{\"StockQuote\":{"
                + "\"value\":\"" + this.value + "\""
                + ", \"tickerSymbol\":\"" + this.tickerSymbol + "\""
                + ", \"quoteDate\":\"" + DateFunctions.dateToString(this.quoteDate) + "\""
                + "}}";
    }
}
