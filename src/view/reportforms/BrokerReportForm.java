package view.reportforms;

import datamodels.Broker;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * This class contains all the code for the broker report form
 */
public class BrokerReportForm extends JFrame {

   // Form controls
   /**
    * button to close the form
    */
   private JButton closeButton = new JButton("Close Report");

   /**
    * label for free text area on form
    */
   private JLabel label1 = new JLabel("List Of Brokers At A Glance");

   // Title of form
   private JLabel label2 = new JLabel("Broker Detail Data");

   /**
    * scrollable panels for the table and free form text area
    */
   private JScrollPane textAreaScrollPanel = new JScrollPane();
   private JScrollPane tableScrollPanel = new JScrollPane();

   /**
    * table to display data from the data model
    */
   private JTable tableOfBrokers = new javax.swing.JTable();

   /**
    * Free form text area
    */
   private JTextArea textAreaOfBrokers = new JTextArea(20, 5);

   /**
    * Constructor to create the form
    */
   public BrokerReportForm() {

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
       * Add label component to the form
       */
      getContentPane().add(label1);
      label1.setBounds(300, 20, 210, 14);

      /**
       * Put the table in the scrollable panel
       */
      textAreaScrollPanel.setViewportView(textAreaOfBrokers);

      /**
       * Add component to the form
       */
      getContentPane().add(textAreaScrollPanel);
      textAreaScrollPanel.setBounds(10, 45, 720, 124);

      /**
       * Add label component to the form
       */
      getContentPane().add(label2);
      label2.setBounds(380, 190, 270, 14);

      /**
       * Add component to the form
       */
      getContentPane().add(closeButton);
      closeButton.setBounds(500, 380, 120, 23);

      tableOfBrokers.setModel(new javax.swing.table.DefaultTableModel(
              new Object[][]{
                 {null, null, null, null, null, null, null, null},
                 {null, null, null, null, null, null, null, null},
                 {null, null, null, null, null, null, null, null},
                 {null, null, null, null, null, null, null, null},
                 {null, null, null, null, null, null, null, null},
                 {null, null, null, null, null, null, null, null},
                 {null, null, null, null, null, null, null, null},},
              new String[]{
                 "Name", "Address", "Status", "Salary", "ID", "Date of Birth", "Date of Hire", "Date of Termination"
              }
      ) {
         Class[] types = new Class[]{
            java.lang.String.class, java.lang.String.class, java.lang.String.class, 
            java.lang.Double.class, java.lang.Double.class, java.lang.String.class, 
            java.lang.String.class, java.lang.String.class
         };
         boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false
         };

         public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
         }

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
         }
      });
      tableOfBrokers.setColumnSelectionAllowed(true);
      // Put the table in a scrollablepanel
      tableScrollPanel.setViewportView(tableOfBrokers);

      // Add the panel to the form
      getContentPane().add(tableScrollPanel);
      tableScrollPanel.setBounds(10, 220, 1200, 138);

      this.setTitle("Broker Report");
      
      /**
       * set the overall size of the form
       */
      setSize(1250, 500);
   }

   // Getters that allow access to the controls on the form
   public JButton getCloseButton() {
      return closeButton;
   }

   public JTable getTableOfBrokers() {
      return tableOfBrokers;
   }

   public JTextArea getTextAreaOfBrokers() {
      return textAreaOfBrokers;
   }

}
