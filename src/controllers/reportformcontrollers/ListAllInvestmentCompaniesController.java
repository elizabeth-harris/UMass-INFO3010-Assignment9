/*
 * Listens for events on the report form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 *
 * Populates the form with data if there is any in the application data model
 */
package controllers.reportformcontrollers;

import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import datacontainers.InvestmentCompanyDataContainer;
import datamodels.Broker;
import datamodels.InvestmentCompany;
import view.reportforms.InvestmentCompanyReportForm;

public class ListAllInvestmentCompaniesController implements ActionListener {

   // The form is created here
   InvestmentCompanyReportForm form = new InvestmentCompanyReportForm();

   // Constructor 
   public ListAllInvestmentCompaniesController(InvestmentCompanyDataContainer investmentCompanyDataContainer) {

      // Link the buttons to the actionPerformed method
      this.form.getCloseButton().addActionListener(this);

      // Call private method that will add all companies to the text area
      this.populateTextArea(investmentCompanyDataContainer);

      // Call private method that will add all companies to the table
      this.populateTable(investmentCompanyDataContainer);

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
    * Private method that will add all companies to the text area
    */
   private void populateTextArea(InvestmentCompanyDataContainer investmentCompanyDataContainer) {

      // Loop through the list and add the companies to the text area,
      // Each time adding a cr/lf between items for readibility.
      String companies = "";
      for (InvestmentCompany company : investmentCompanyDataContainer.getInvestmentCompanyList()) {
         companies = companies + company.getCompanyName() + "\n";
      }
      // Once all the data is in the string, set the text of the text area to the string value
      this.form.getCompaniesTextArea().setText(companies);

   }

   /**
    * Private method that will add all companies to the table
    */
   private void populateTable(InvestmentCompanyDataContainer investmentCompanyDataContainer) {

      // This data model will hold the data for the JTable (this is the M in MVC)
      DefaultTableModel defaultTableModel = new DefaultTableModel();

      // Because it's a table, we have to add columns to table model to describe how 
      // the data is layed out
      defaultTableModel.addColumn("Conpany Name");
      defaultTableModel.addColumn("Brokers");

      // Link the data model to the table
      this.form.getCompaniesTableDisplay().setModel(defaultTableModel);

      // Add the companies to the table model
      for (InvestmentCompany company : investmentCompanyDataContainer.getInvestmentCompanyList()) {

         // Create a vector that is one row in the table
         Vector companyRow = new Vector();

         // Add the company name to the table row
         companyRow.add(company.getCompanyName());

         // Format the broker list, only retrieve the broker names
         String brokerList = "";
         for (Broker broker : company.getListOfBrokers()) {
            brokerList = brokerList + broker.getName() + "\n";
         }
         // Add the broker list to the table row
         companyRow.add(brokerList);

         // Add the vector to the  data model that is connected to the JTable
         defaultTableModel.addRow(companyRow);
      }
   }

}
