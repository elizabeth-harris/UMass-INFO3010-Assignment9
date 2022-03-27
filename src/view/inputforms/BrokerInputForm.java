package view.inputforms;

import datacontainers.InvestorDataContainer;
import datamodels.Investor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controllers.inputformcontrollers.InputBrokerFormController;

public class BrokerInputForm extends JFrame {

// Form fields
   private JTextField addressField;
   private JButton clearButton;
   private JButton closeButton;
   private JLabel salaryLabel;
   private JLabel statusLabel;
   private JLabel salaryHint;
   private JLabel nameLabel;
   private JLabel addressLabel;
   private JScrollPane jScrollPane1;
   private JTextArea jTextArea1;
   private JTextField nameField;
   private JFormattedTextField salaryField;
   private JButton saveButton;
   private JComboBox statusField;
   private JTextField idField;
   private JLabel idLabel;
   private JLabel hintLabel1;
   private JLabel hintLabel2;
   private JLabel hintLabel3;
   private JFormattedTextField dateOfBirthFormattedTextField;
   private JFormattedTextField dateOfHireFormattedTextField;
   private JFormattedTextField dateOfTerminationFormattedTextField;
   private JLabel dateOfBirthLabel;
   private JLabel hireDateLabel;
   private JLabel terminationDateLabel;
   private JLabel addClientsLabel = new JLabel("Add Clients:");
   private JScrollPane listOfClientsScrollablePanel = new JScrollPane();
   private JList dropdownClientList = new JList();
   private DefaultListModel dropdownListDataModel = new DefaultListModel();
   private DefaultFormatterFactory dateFormatter;
   private InputBrokerFormController controller;
 
   /**
    * default constructor
    */
   public BrokerInputForm(InputBrokerFormController controller) {

      // Store a local copy of the controller
      this.controller = controller;

      // Initialize all the components on the form
      jScrollPane1 = new JScrollPane();
      jTextArea1 = new JTextArea();
      nameField = new JTextField();
      addressField = new JTextField();
      closeButton = new JButton("Close");
      nameLabel = new JLabel("Name:");
      addressLabel = new JLabel("Address:");
      saveButton = new JButton("Save");
      clearButton = new JButton("Clear");
      statusLabel = new JLabel("Status:");
      statusField = new JComboBox();
      salaryField = new JFormattedTextField();
      salaryLabel = new JLabel("Salary");
      salaryHint = new JLabel();
      idLabel = new JLabel("Id:");
      idField = new JTextField();
      dateOfHireFormattedTextField = new JFormattedTextField();
      dateOfBirthFormattedTextField = new JFormattedTextField();
      dateOfTerminationFormattedTextField = new JFormattedTextField();
      hintLabel1 = new JLabel();
      hintLabel2 = new JLabel();
      hintLabel3 = new JLabel();
      dateOfBirthFormattedTextField = new JFormattedTextField();
      dateOfHireFormattedTextField = new JFormattedTextField();
      dateOfTerminationFormattedTextField = new JFormattedTextField();
      dateOfBirthLabel = new JLabel();
      hireDateLabel = new JLabel();
      terminationDateLabel = new JLabel();

      jTextArea1.setColumns(20);
      jTextArea1.setRows(5);
      jScrollPane1.setViewportView(jTextArea1);

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

      getContentPane().setLayout(null);

      getContentPane().add(nameLabel);
      nameLabel.setBounds(10, 50, 130, 14);

      getContentPane().add(nameField);
      nameField.setBounds(160, 50, 200, 20);

      getContentPane().add(addressLabel);
      addressLabel.setBounds(10, 80, 140, 14);

      getContentPane().add(addressField);
      addressField.setBounds(160, 80, 200, 20);

      idLabel.setText("Id:");
      getContentPane().add(idLabel);
      idLabel.setBounds(10, 110, 100, 14);

        idField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')) {
                    e.consume();
                }
            }
        });
      getContentPane().add(idField);
      idField.setBounds(160, 110, 200, 20);

      dateOfBirthLabel.setText("Date Of Birth");
      getContentPane().add(dateOfBirthLabel);
      dateOfBirthLabel.setBounds(10, 150, 130, 14);

      // Date text field formatter (needs a try/catch block)
      try {
         dateFormatter = new DefaultFormatterFactory(new MaskFormatter("##/##/####"));
      } catch (Exception exp) {
         // This should never happen!
      }

      dateOfBirthFormattedTextField.setFormatterFactory(dateFormatter);
      getContentPane().add(dateOfBirthFormattedTextField);
      dateOfBirthFormattedTextField.setBounds(160, 150, 130, 30);

      hintLabel1.setText("MM/DD/YYYY");
      getContentPane().add(hintLabel1);
      hintLabel1.setBounds(300, 155, 160, 14);

      hireDateLabel.setText("Date of Hire");
      getContentPane().add(hireDateLabel);
      hireDateLabel.setBounds(10, 190, 120, 14);

      dateOfHireFormattedTextField.setFormatterFactory(dateFormatter);
      getContentPane().add(dateOfHireFormattedTextField);
      dateOfHireFormattedTextField.setBounds(160, 190, 130, 30);

      hintLabel2.setText("MM/DD/YYYY");
      getContentPane().add(hintLabel2);
      hintLabel2.setBounds(300, 195, 160, 14);

      terminationDateLabel.setText("Date of Termination");
      getContentPane().add(terminationDateLabel);
      terminationDateLabel.setBounds(10, 230, 120, 14);

      dateOfTerminationFormattedTextField.setFormatterFactory(dateFormatter);
      getContentPane().add(dateOfTerminationFormattedTextField);
      dateOfTerminationFormattedTextField.setBounds(160, 230, 130, 30);

      hintLabel3.setText("MM/DD/YYYY");
      getContentPane().add(hintLabel3);
      hintLabel3.setBounds(300, 235, 160, 14);

      getContentPane().add(salaryLabel);
      salaryLabel.setBounds(10, 265, 130, 14);

       salaryField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if ( ((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')  &&  (caracter != '.')) {
                    e.consume();
                }
            }
        });
      getContentPane().add(salaryField);
      salaryField.setBounds(160, 265, 130, 20);

      salaryHint.setText("Enter Single Digits, no spaces, no commas");
      getContentPane().add(salaryHint);
      salaryHint.setBounds(160, 285, 330, 14);

      getContentPane().add(statusLabel);
      statusLabel.setBounds(10, 310, 130, 14);

      statusField.setModel(new DefaultComboBoxModel(new String[]{"Full Time", "Part Time"}));
      getContentPane().add(statusField);
      statusField.setBounds(160, 310, 130, 20);

      listOfClientsScrollablePanel.setViewportView(dropdownClientList);

      getContentPane().add(addClientsLabel);
      addClientsLabel.setBounds(10, 345, 260, 14);
      
      getContentPane().add(listOfClientsScrollablePanel);
      listOfClientsScrollablePanel.setBounds(10, 360, 360, 180);

      // Add all available clients to the list 
      InvestorDataContainer listOfClientsDataContainer = controller.getInvestorDataContainer();
      List<Investor> listOfClients = listOfClientsDataContainer.getInvestorList();

      for (int i = 0; i < listOfClients.size(); i++) {
         dropdownListDataModel.addElement(listOfClients.get(i).getName()
                 + " : " + listOfClients.get(i).getId());
      }

      // Link the data model to the list on the form
      dropdownClientList.setModel(dropdownListDataModel);

      getContentPane().add(closeButton);
      closeButton.setBounds(400, 110, 110, 23);

      getContentPane().add(saveButton);
      saveButton.setBounds(400, 50, 110, 23);

      getContentPane().add(clearButton);
      clearButton.setBounds(400, 80, 110, 23);

      this.setTitle("Add Broker");

      this.setSize(500, 1000);

      // center form
      setLocationRelativeTo(null);

      // Link the controller to the buttons on the form
      this.clearButton.addActionListener(controller);
      this.saveButton.addActionListener(controller);
      this.closeButton.addActionListener(controller);

      // Set the size of the window
      this.setSize(600, 800);

      // Make the form visible
      this.setVisible(true);
   }

   public JTextField getAddressField() {
      return addressField;
   }

   public JButton getClearButton() {
      return clearButton;
   }

   public JButton getCloseButton() {
      return closeButton;
   }

   public JButton getExitButton() {
      return closeButton;
   }

   public JScrollPane getjScrollPane1() {
      return jScrollPane1;
   }

   public JTextArea getjTextArea1() {
      return jTextArea1;
   }

   public JTextField getNameField() {
      return nameField;
   }

   public JTextField getSalaryField() {
      return salaryField;
   }

   public JButton getSaveButton() {
      return saveButton;
   }

   public JComboBox getStatusField() {
      return statusField;
   }

   public JTextField getIdField() {
      return idField;
   }

   public JFormattedTextField getDateOfBirthFormattedTextField() {
      return dateOfBirthFormattedTextField;
   }

   public JFormattedTextField getDateOfHireFormattedTextField() {
      return dateOfHireFormattedTextField;
   }

   public JFormattedTextField getDateOfTerminationFormattedTextField() {
      return dateOfTerminationFormattedTextField;
   }

   public JList getDropdownClientList() {
      return dropdownClientList;
   }

   
}
