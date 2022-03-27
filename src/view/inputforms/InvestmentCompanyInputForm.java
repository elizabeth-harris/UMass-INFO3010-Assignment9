/**
 * This class is used to create the investment company input form
 */
package view.inputforms;

import datacontainers.BrokerDataContainer;
import datamodels.Broker;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controllers.inputformcontrollers.InputInvestmentCompanyFormController;

public class InvestmentCompanyInputForm extends JFrame {

   private JTextField companyNameField = new JTextField();
   private JButton closeButton = new JButton("Close");
   private JButton clearButton = new JButton("Clear");
   private JButton saveButton = new JButton("Save");
   private JLabel companyNameFieldLabel = new JLabel("Company Name:");
   private JLabel addBrokersLabel = new JLabel("Add Brokers:");
   private JScrollPane listOfBrokersScrollablePanel = new JScrollPane();
   private JList dropdownBrokerList = new JList();

   private DefaultListModel dropdownListDataModel = new DefaultListModel();

   public InvestmentCompanyInputForm(InputInvestmentCompanyFormController controller) {

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

      getContentPane().setLayout(null);

      getContentPane().add(companyNameFieldLabel);
      companyNameFieldLabel.setBounds(30, 30, 100, 14);

      getContentPane().add(companyNameField);
      companyNameField.setBounds(150, 30, 250, 20);

      getContentPane().add(addBrokersLabel);
      addBrokersLabel.setBounds(30, 60, 260, 14);

      listOfBrokersScrollablePanel.setViewportView(dropdownBrokerList);

      getContentPane().add(listOfBrokersScrollablePanel);
      listOfBrokersScrollablePanel.setBounds(30, 80, 360, 200);

      BrokerDataContainer listOfBrokersDataContainer = controller.getBrokeDataContainer();
      List<Broker> listOfBrokers = listOfBrokersDataContainer.getBrokerList();

      for (int i = 0; i < listOfBrokers.size(); i++) {
         dropdownListDataModel.addElement(listOfBrokers.get(i).getName() + 
                 " : " + listOfBrokers.get(i).getId());
      }

      // Link the data model to the list on the form
      dropdownBrokerList.setModel(dropdownListDataModel);

      getContentPane().add(saveButton);
      saveButton.setBounds(450, 30, 80, 23);

      getContentPane().add(clearButton);
      clearButton.setBounds(450, 60, 80, 23);

      getContentPane().add(closeButton);
      closeButton.setBounds(450, 90, 80, 23);

      // Link this controller to the buttons on the form
      this.clearButton.addActionListener(controller);
      this.saveButton.addActionListener(controller);
      this.closeButton.addActionListener(controller);

      // Set the size of the form
      this.setSize(600, 350);

      // center form
      setLocationRelativeTo(null);

      this.setTitle("Add Investment Company");
   }

   // Accessor methods used by the controller class to retrieve the data from
   // the form
   public JTextField getCompanyNameField() {
      return companyNameField;
   }

   public JList getListOfBrokers() {
      return dropdownBrokerList;
   }

   
}
