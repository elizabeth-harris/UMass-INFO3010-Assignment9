/*
 * Listens for events on the report form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 *
 * Handles all events on the stock quotes report form
 *
 * Populates the form with data if there is any in the application data model
 */
package controllers.reportformcontrollers;

import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import datacontainers.*;
import datamodels.StockQuote;
import utilities.date.DateFunctions;
import utilities.formatters.NumberFormatter;
import view.reportforms.StockQuoteReportForm;

public class ListAllStockQuotesController implements ActionListener {

   // The form is created here
   StockQuoteReportForm form = new StockQuoteReportForm();

   // Constructor 
   public ListAllStockQuotesController(StockQuoteDataContainer stockQuoteDataContainer) {

      // Link this controller to the components on the form  
      // In this case, there is only one controllable component on the form, the close button
      this.form.getCloseButton().addActionListener(this);

      // Call private method that will add all stockquotes to the table on the form
      this.populateTable(stockQuoteDataContainer);

      // Call the private method to create the content for the text area
      this.populateTextArea(stockQuoteDataContainer);
      
      // make the form visible
      form.setVisible(true);

   }

   /**
    * Implements the ActionListener controller actions In this case, there is
    * just one control on the form, a close button
    */
   public void actionPerformed(java.awt.event.ActionEvent event) {

      //  Figure out which button was clicked
      String buttonClicked = event.getActionCommand();

      if (buttonClicked.equals("Close Report")) {
         form.dispose();
      }
   }

   /**
    * Private method that will add all stock quotes to the text area
    */
   private void populateTextArea(StockQuoteDataContainer stockQuoteDataContainer) {

      // Loop through the list and add the stock tickers to the text area,
      // Each time adding a cr/lf between items for readibility.
      String stocks = "";
      for (StockQuote stock : stockQuoteDataContainer.getStockQuoteList()) {
         stocks = stocks + stock.getTickerSymbol() + "\n";
      }
      // Once all the data is in the string, set the text of the text area to the string value
      this.form.getTextArea().setText(stocks);

   }

   /**
    * Private method that will add all stockquotes to the table in the report
    * form
    */
   private void populateTable(StockQuoteDataContainer stockQuoteDataContainer) {

      // A table model will hold the data for the JTable (this is the M in MVC)
      DefaultTableModel tableModel = new DefaultTableModel();

      // add columns to table model
      tableModel.addColumn("Tciker Symbol");
      tableModel.addColumn("Date");
      tableModel.addColumn("Value");

      // Link the data model to the table
      this.form.getTableOfStockQuotes().setModel(tableModel);

      // Add the stockquotes in the application data model to the table model
      // The table model is linked to the table widget on the form
      for (StockQuote quote : stockQuoteDataContainer.getStockQuoteList()) {

         // Create a vector object that is one row in the table
         Vector quoteForTable = new Vector();

         // Add the data to the vector
         quoteForTable.add(quote.getTickerSymbol());
         quoteForTable.add(DateFunctions.dateToString(quote.getQuoteDate()));
         quoteForTable.add(NumberFormatter.formatCurrency(quote.getValue()));
         
         // Add the row to the  data model that is connected to the JTable         
         tableModel.addRow(quoteForTable);

      }
   }
}
