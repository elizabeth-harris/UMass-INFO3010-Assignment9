package view.reportforms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * This class creates a stock quote report form.
 */
public class StockQuoteReportForm extends JFrame {

     /**
       * Form components
       */
     
   /**
    * button to close the form
    */
   private JButton closeButton = new JButton("Close Report");
   /**
    * label for text area
    */
   private JLabel textAreaLabel = new JLabel("List Of Brokers At A Glance");

/**
    * Free form text area
    */
   private JTextArea textArea = new JTextArea(20, 5);

   /**
    * scrollable panel for the table
    */
   private JScrollPane tableScrollPanel = new JScrollPane();
   /**
    * table to display data from the data model
    */
   private JTable tableOfStockQuotes = new JTable();

   
   private JScrollPane textAreaScrollPanel = new JScrollPane();
   /**
    * Constructor to create the form
    */
   public StockQuoteReportForm() {
    
      /**
       * set close function to dispose of the form
       */
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

      /**
       * set the form layout to null, we'll place the components manually using
       * x,y layout positions
       */
      getContentPane().setLayout(null);
         
      /**
       * Add close button to the form
       */
      getContentPane().add(closeButton);
      /**
       * location and size of component on the form
       */
      closeButton.setBounds(320, 220, 120, 23);

      /**
       * Put the table in the scrollable panel
       */
      tableScrollPanel.setViewportView(tableOfStockQuotes);
      /**
       * Add scrollable panels to the form
       */
      getContentPane().add(tableScrollPanel);
      /**
       * location and size of scrollable panel on the form
       */
      tableScrollPanel.setBounds(20, 200, 720, 138);

        /**
       * Put the text area in the scrollable panel
       */
      textAreaScrollPanel.setViewportView(textArea);
      /**
       * Add scrollable panels to the form
       */
      getContentPane().add(textAreaScrollPanel);
      /**
       * location and size of scrollable panel on the form
       */
      tableScrollPanel.setBounds(20, 60, 720, 138);
      
      this.setTitle("Stock Quote Report");
      
      /**
       * set the overall size of the form
       */
      setSize(800, 350);
   }

   // Getters that allow access to the controls on the form via the controller
   public JButton getCloseButton() {
      return this.closeButton;
   }

   public JTable getTableOfStockQuotes() {
      return tableOfStockQuotes;
   }

   public JTextArea getTextArea() {
      return textArea;
   }

   
}
