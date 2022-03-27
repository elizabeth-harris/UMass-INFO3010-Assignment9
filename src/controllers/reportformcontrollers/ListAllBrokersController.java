/*
 * Listens for events on the report form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 *
 * Populates the form with data if there is any in the application data model
 */
package controllers.reportformcontrollers;

import datamodels.*;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import datacontainers.*;
import utilities.date.DateFunctions;
import view.reportforms.BrokerReportForm;

public class ListAllBrokersController implements ActionListener {

   // The form
   BrokerReportForm form;

   public ListAllBrokersController(BrokerDataContainer brokerDataContainer) {

      // Create the form
      form = new BrokerReportForm();

      // Link the buttons to the actionPerformed method
      this.form.getCloseButton().addActionListener(this);

      // Call private method that will add all Broker to the text area
      this.populateTextArea(brokerDataContainer);

      // Call private method that will add all Broker to the table
      this.populateTable(brokerDataContainer);

      // make the form visible
      form.setVisible(true);
   }

   /**
    * actionPerformed method implemented from the ActionListener interface
    *
    * This method figures out which button was clicked based on the text of the
    * button. The button click event is passed in via the ActionEvent object.
    *
    * @param event
    */
   public void actionPerformed(java.awt.event.ActionEvent event) {

      //  Figure out which button was clicked
      String buttonClicked = event.getActionCommand();

      if (buttonClicked.equals("Close Report")) {
         form.dispose();
      }
   }

   /**
    * Private method that will add all Broker to the text area
    */
   private void populateTextArea(BrokerDataContainer brokerDataContainer) {

      // Initialize the string which will hold the data for the text area
      // Start with labels
      String listOfBrokerString = "";

      // Loop through the list and add the broker names to the text area,
      // Each time adding a cr/lf between items for readibility.
      for (Broker abroker : brokerDataContainer.getBrokerList()) {
         listOfBrokerString += abroker.getName() + "\n";
      }
      // Once all the data is in the string, set the text of the text area to the string value
      this.form.getTextAreaOfBrokers().setText(listOfBrokerString);

   }

   /**
    * Private method that will add all Broker to the table
    */
   private void populateTable(BrokerDataContainer brokerDataContainer) {

      // A table model like this will hold the data for the JTable (this is the M in MVC)
      DefaultTableModel tableModel = new DefaultTableModel();

      // Link the data model to the table
      this.form.getTableOfBrokers().setModel(tableModel);

      // Add columns to table model
      tableModel.addColumn("Name");
      tableModel.addColumn("Address");
      tableModel.addColumn("Status");
      tableModel.addColumn("Salary");
      tableModel.addColumn("ID");
      tableModel.addColumn("Date Of Birth");
      tableModel.addColumn("Date Of Hire");
      tableModel.addColumn("Date Of Termination");

      // Add the Broker from the application data model to the table data model
      for (Broker abroker : brokerDataContainer.getBrokerList()) {

         // Create a vector that is one row in the table
         Vector tableRow = new Vector();

         // Add the data to the vector
         tableRow.add(abroker.getName());
         tableRow.add(abroker.getAddress());
         tableRow.add(abroker.getStatus());
         tableRow.add(abroker.getSalary());
         tableRow.add(abroker.getId());
         tableRow.add(DateFunctions.dateToString(abroker.getDateOfBirth()));
         tableRow.add(DateFunctions.dateToString(abroker.getDateOfHire()));
         tableRow.add(DateFunctions.dateToString(abroker.getDateOfTermination()));

         // Format the broker's client data
         String clientList = abroker.getListOfClients().toString();

         tableRow.add(clientList);

         // Add the row of data to the  data model that is connected to the JTable
         tableModel.addRow(tableRow);
      }
   }

}
