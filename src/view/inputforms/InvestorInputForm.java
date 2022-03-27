package view.inputforms;

import datamodels.StockQuote;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controllers.inputformcontrollers.InputInvestorFormController;

public class InvestorInputForm extends javax.swing.JFrame {

// Form fields
   private JTextField addressField;
   private JButton clearButton;
   private JButton closeButton;
   private JFormattedTextField dateOfBirthFormattedTextField;
   private JFormattedTextField memberSinceFormattedTextField;
   private JLabel nameLabel;
   private JLabel addressLabel;
   private JLabel idLabel;
   private JLabel dateOfBirthLabel;
   private JLabel memberSinceLabel;
   private JLabel hintLabel1;
   private JLabel hintLabel2;
   private JTextField nameField;
   private JButton saveButton;
   private JTextField idField;
   private JLabel addStocksLabel = new JLabel("Add Stocks:");
   private JScrollPane listOfStocksScrollablePanel = new JScrollPane();
   private JList dropdownStockList = new JList();
   private DefaultListModel dropdownListDataModel = new DefaultListModel();
   private InputInvestorFormController controller;
   private DefaultFormatterFactory dateFormatter;

   /**
    * default constructor
    */
   public InvestorInputForm(InputInvestorFormController controller) {

      this.controller = controller;
      
      // Initialize all the components on the form
        // Date text field formatter (needs a try/catch block)
      try {
         dateFormatter = new DefaultFormatterFactory(new MaskFormatter("##/##/####"));
      } catch (Exception exp) {
         // This should never happen!
      }

      nameField = new JTextField();
      addressField = new JTextField();
      idField = new JTextField();
      closeButton = new JButton();
      nameLabel = new JLabel();
      addressLabel = new JLabel();
      idLabel = new JLabel();
      dateOfBirthLabel = new JLabel();
      memberSinceLabel = new JLabel();
      saveButton = new JButton();
      clearButton = new JButton();
      memberSinceFormattedTextField = new JFormattedTextField();
      dateOfBirthFormattedTextField = new JFormattedTextField();
      hintLabel1 = new JLabel();
      hintLabel2 = new JLabel();

      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      getContentPane().add(nameField);
      nameField.setBounds(160, 50, 200, 30);
      getContentPane().add(addressField);
      addressField.setBounds(160, 90, 200, 30);
      
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
      idField.setBounds(160, 130, 200, 30);

      nameLabel.setText("Name:");
      getContentPane().add(nameLabel);
      nameLabel.setBounds(10, 50, 140, 14);

      addressLabel.setText("Address:");
      getContentPane().add(addressLabel);
      addressLabel.setBounds(10, 90, 140, 14);

      idLabel.setText("Id:");
      getContentPane().add(idLabel);
      idLabel.setBounds(10, 130, 140, 14);

      dateOfBirthLabel.setText("Date Of Birth");
      getContentPane().add(dateOfBirthLabel);
      dateOfBirthLabel.setBounds(10, 200, 130, 14);

      memberSinceLabel.setText("Member Since");
      getContentPane().add(memberSinceLabel);
      memberSinceLabel.setBounds(10, 240, 120, 14);

      listOfStocksScrollablePanel.setViewportView(dropdownStockList);

      getContentPane().add(addStocksLabel);
      addStocksLabel.setBounds(10, 270, 260, 14);

      getContentPane().add(listOfStocksScrollablePanel);
      listOfStocksScrollablePanel.setBounds(10, 288, 360, 180);

      // Add all available stocks to the list 
      List<StockQuote> listOfStockQuotes = controller.getStockquoteDataContainer().getStockQuoteList();
     
      for (StockQuote stock : listOfStockQuotes) {
        // dropdownStockList.add(stock);
      }
      
       for (int i = 0; i < listOfStockQuotes.size(); i++) {
         dropdownListDataModel.addElement(listOfStockQuotes.get(i).getTickerSymbol()
                 + " : " + listOfStockQuotes.get(i).getValue());
      }

      // Link the data model to the list on the form
      dropdownStockList.setModel(dropdownListDataModel);

      saveButton.setText("Save");
      getContentPane().add(saveButton);
      saveButton.setBounds(390, 50, 110, 23);

      clearButton.setText("Clear");
      getContentPane().add(clearButton);
      clearButton.setBounds(390, 80, 110, 23);

      closeButton.setText("Close");
      getContentPane().add(closeButton);
      closeButton.setBounds(390, 110, 110, 23);

      memberSinceFormattedTextField.setFormatterFactory(dateFormatter);
      getContentPane().add(memberSinceFormattedTextField);
      memberSinceFormattedTextField.setBounds(160, 230, 130, 30);

      dateOfBirthFormattedTextField.setFormatterFactory(dateFormatter);
      getContentPane().add(dateOfBirthFormattedTextField);
      dateOfBirthFormattedTextField.setBounds(160, 190, 130, 30);

      hintLabel1.setText("MM/DD/YYYY");
      getContentPane().add(hintLabel1);
      hintLabel1.setBounds(300, 240, 160, 14);

      hintLabel2.setText("MM/DD/YYYY");
      getContentPane().add(hintLabel2);
      hintLabel2.setBounds(300, 200, 160, 14);

      this.setTitle("Add Invester");

      // center form
      setLocationRelativeTo(null);

      // Link the controller to the buttons on the form
      this.clearButton.addActionListener(controller);
      this.saveButton.addActionListener(controller);
      this.closeButton.addActionListener(controller);

      // Set the size of the form
      this.setSize(600, 600);

      // Make the form visible
      this.setVisible(true);
   }

   public JTextField getAddressField() {
      return addressField;
   }

   public JButton getClearButton() {
      return clearButton;
   }

   public JFormattedTextField getDateOfBirthFormattedTextField() {
      return dateOfBirthFormattedTextField;
   }

   public JFormattedTextField getMemberSinceFormattedTextField() {
      return memberSinceFormattedTextField;
   }

   public JButton getExitButton() {
      return closeButton;
   }

  public JLabel getMemberSinceLabel() {
      return memberSinceLabel;
   }

   public JLabel getHintLabel2() {
      return hintLabel2;
   }

   public JTextField getNameField() {
      return nameField;
   }

   public JButton getSaveButton() {
      return saveButton;
   }

   public JTextField getIdField() {
      return idField;
   }

   public JList getDropdownStockList() {
      return dropdownStockList;
   }

   
}
