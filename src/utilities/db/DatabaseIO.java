/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.db;

import datacontainers.StockQuoteDataContainer;
import datamodels.StockQuote;
import exceptionhandlers.DatabaseException;
import exceptionhandlers.MyFileException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class contains methods to write to and read from, a sql database I
 * suppose I could have put them in the I/O class but I decided to leave all the
 * database code together.
 */
public class DatabaseIO {

    /**
     * Store all stockquotes to database
     */
    public static void storeStockQuotes(StockQuoteDataContainer stockquoteDataContainer) throws DatabaseException {

        // We are looping through the list of stock quotes. Each stock quote will
        // create its own statement in a try/catch block so that if one insert
        // fails, they all won't fail.  We could have put the for loop inside the
        // try/catch block but then all the inserts will fail after the first failure
        for (StockQuote quote : stockquoteDataContainer.getStockQuoteList()) {
            try {
                // Retreive the database connection and create the statement object
                Connection connection = DatabaseUtilities.openDatabaseConnection();
                Statement insertStatement = connection.createStatement();

                // Create the string for the sql statement
                String command = "INSERT INTO uml.stockquote (tickersymbol, value, date)"
                        + "VALUES ('" + quote.getTickerSymbol() + "','"
                        + quote.getValue() + "','" + DatabaseDateUtilities.getSqlFormattedDate(quote.getQuoteDate()) + "')";

                // Execute the statement
                insertStatement.executeUpdate(command);
                // Close the statement

            } catch (SQLException error) {
                throw new DatabaseException("A database error occured updating"
                        + " stock quote table " + error.getMessage());
            }
        }
    }

    /**
     * retrieve data from database
     */
    public static List<StockQuote> retrieveStockQuotes() throws DatabaseException {

        ArrayList<StockQuote> listOfStockQuotes = new ArrayList<>();

        try {
            // Retreive the database connection and create the statement object
            Connection connection = DatabaseUtilities.openDatabaseConnection();
            Statement queryStatement = connection.createStatement();

            // Create the string for the statement objrct
            String command = "SELECT tickersymbol, value, date FROM stockquote ORDER BY tickersymbol";

            // Execute the statement object 
            ResultSet results = queryStatement.executeQuery(command);

            // Call private helper method to parse the result set into the array list
            listOfStockQuotes = parseResults(results);

        } catch (SQLException error) {
            throw new DatabaseException("A database error occured retrieve data from the stock quote table " + error.getMessage());
        }

        return listOfStockQuotes;
    }

    /**
     * Populate the array list with data from the database
     */
    private static ArrayList<StockQuote> parseResults(ResultSet results) throws DatabaseException {

        ArrayList<StockQuote> listOfStockquotes = new ArrayList<>();

        try {
            while (results.next()) {
                StockQuote quote = new StockQuote();
                quote.setTickerSymbol(results.getString(1));
                quote.setValue(Double.parseDouble(results.getString(2)));
                quote.setQuoteDate(DatabaseDateUtilities.getJavaFormattedDate(results.getDate("date")));
                listOfStockquotes.add(quote);
            }
        } catch (MyFileException | NumberFormatException | SQLException e) {
            throw new DatabaseException("Error parsing database results"
                    + " stockquote table " + e.getMessage());
        }

        return listOfStockquotes;
    }

}
