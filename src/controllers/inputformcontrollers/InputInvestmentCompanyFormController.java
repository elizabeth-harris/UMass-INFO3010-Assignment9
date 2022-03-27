/*
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers.inputformcontrollers;

import datacontainers.BrokerDataContainer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datacontainers.InvestmentCompanyDataContainer;
import datamodels.Broker;
import datamodels.InvestmentCompany;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

import controllers.Application;
import view.inputforms.InvestmentCompanyInputForm;

public class InputInvestmentCompanyFormController implements ActionListener {

   // The data datacontainers needed for this form are passed in via the constructor
   InvestmentCompanyDataContainer investmentCompanyDataContainer;
   BrokerDataContainer brokerDataContainer;

   // The form is created here
   private InvestmentCompanyInputForm form;

   /**
    * Constructor
    *
    */
   public InputInvestmentCompanyFormController(InvestmentCompanyDataContainer investmentCompanyDataContainer,
           BrokerDataContainer brokerDataContainer) {

      // Store the passed in data datacontainers
      this.investmentCompanyDataContainer = investmentCompanyDataContainer;
      this.brokerDataContainer = brokerDataContainer;

      // Create the form
      form = new InvestmentCompanyInputForm(this);

      // make the form visible
      this.form.setVisible(true);
   }

   /**
    * actionPerformed method implemented from the ActionListener interface
    *
    * This method figures out which button was clicked based on the text of the
    * button. The button click event is passed in via the ActionEvent object.
    *
    * @param event
    */
   public void actionPerformed(ActionEvent event) {
      if (event.getActionCommand().equals("Save")) {
         this.saveData();
      } else if (event.getActionCommand().equals("Clear")) {
         this.clearForm();
      } else if (event.getActionCommand().equals("Close")) {
         this.closeForm();
      }
   }

   /**
    * Private method called from the actionPerformed method to save the daa
    */
   private void saveData() {

      // Create a new InvestmentCompany object
      InvestmentCompany newInvestmentCompany = new InvestmentCompany();

      // DONE: - Add exception handling inside one or more try/catch blocks for the fields that require validation      
      try {
      
      // Retrieve the company name
      String companyName = this.form.getCompanyNameField().getText();

      // Set the company name in the object
      newInvestmentCompany.setCompanyName(companyName);

      // Retrieve all selected brokers and add to drop down list
      // The list only contains broker names and ids.  Need to look them up
      // in the broker data container and add pointers to the objects in
      // the data container
      // Retrieve the selected brokers (the returned list is a list of generic objects)
      List selectedBrokerList = (List) this.form.getListOfBrokers().getSelectedValuesList();

      // Convert the generic objects to Strings and look them up in the broker data container
      for (Object selectedBroker : selectedBrokerList) {
         String selectedBrokerString = (String) selectedBroker;
         // This is where it probably would have been better to store the brokers
         // as a map instead of a list but oh well, the damage is done...

         // Split the string into name and id.  We'll use the unique id to find
         // the broker in the broker data container
         String[] name_id = selectedBrokerString.split(":");

         //Hold on to the id
         long selectedId = Integer.parseInt(name_id[1].trim());

         // Find the broker in the broker data container based on the id
         // Temporay  arraylist of brokers from the broker data container
         List<Broker> brokerList = brokerDataContainer.getBrokerList();
         for (Broker broker : brokerList) {
            if (broker.getId() == selectedId) {
               newInvestmentCompany.addBroker(broker);
            }
         }
      }

      // DONE: - Add the new investment company to the data container
      this.investmentCompanyDataContainer.getInvestmentCompanyList().add(newInvestmentCompany);
      
   // Don't log it until it actually happens!
      // log
    Application.getAPPLICATION_LOGGER().finest("Creating investment company with the following values:\n"+
            newInvestmentCompany.toString());
      
      } catch (InvalidDataException exp) {
          // If we caught any errors, create an error popup, passing the form
          // that caused the error along with the actual error
          new ErrorPopup(form, exp);
       }
   }

   /**
    * Private method to clear the data
    */
   private void clearForm() {
      this.form.getCompanyNameField().setText("");
      this.form.getListOfBrokers().setSelectedIndex(0);
   }

   /**
    * Private method to close the form
    */
   private void closeForm() {
      this.form.dispose();
   }

   public BrokerDataContainer getBrokeDataContainer() {
      return brokerDataContainer;
   }

   public InvestmentCompanyDataContainer getInvestmentCompanyDataContainer() {
      return investmentCompanyDataContainer;
   }

   public BrokerDataContainer getBrokerDataContainer() {
      return brokerDataContainer;
   }

   public InvestmentCompanyInputForm getForm() {
      return form;
   }
   
}
