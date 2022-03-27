package view.inputforms;

import controllers.inputformcontrollers.InputStockQuoteFormController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class StockQuoteInputForm extends JFrame {

   // Form components  
   /**
    * use default constructors to create the form components and then use
    * setters to to set the values when the form is layout out
    */
   private JLabel tickerSymbolLabel;
   private JTextField tickerSymbolTextfield;
   private JLabel stockValueLabel;
   private JTextField stockValueTextField;
   private JButton saveButton;
   private JButton clearButton;
   private JButton closeButton;
   private JFormattedTextField quoteDateFormattedTextField;
   private JLabel quoteDataLabel;
    private DefaultFormatterFactory dateFormatter;

   /**
    * Constructor
    */
   public StockQuoteInputForm(InputStockQuoteFormController controller) {

      // Instantiate the components on the form
      tickerSymbolLabel = new JLabel("Ticker Symbol");
      stockValueLabel = new JLabel("Value:");
      tickerSymbolTextfield = new JTextField();
      stockValueTextField = new JTextField();
      quoteDateFormattedTextField = new JFormattedTextField();
      quoteDataLabel = new JLabel("Quote Date:");
      saveButton = new JButton("Save");
      clearButton = new JButton("Clear");
      closeButton = new JButton("Close");

      // Date text field formatter (needs a try/catch block)
      try {
         dateFormatter = new DefaultFormatterFactory(new MaskFormatter("##/##/####"));
      } catch (Exception exp) {
         // This should never happen!
      }
      
      // Link the controller to the buttons on the form
      this.clearButton.addActionListener(controller);
      this.saveButton.addActionListener(controller);
      this.closeButton.addActionListener(controller);

      /**
       * set close function to dispose of the form
       */
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      /**
       * set the form layout to null, we'll place the components manually using
       * x,y layout positions
       */
      getContentPane().setLayout(null);

      // add ticker symbol to form      
      getContentPane().add(tickerSymbolLabel);
      tickerSymbolLabel.setBounds(30, 20, 120, 14);
      getContentPane().add(tickerSymbolTextfield);
      tickerSymbolTextfield.setBounds(120, 20, 100, 20);

      // add value to form
      getContentPane().add(stockValueLabel);
      stockValueLabel.setBounds(30, 50, 120, 14);
      stockValueTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if ( ((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')  &&  (caracter != '.')) {
                    e.consume();
                }
            }
        });
      getContentPane().add(stockValueTextField);
      stockValueTextField.setBounds(120, 50, 100, 20);

      getContentPane().add(quoteDataLabel);
      quoteDataLabel.setBounds(30, 80, 100, 14);

      // add quote date to form
      quoteDateFormattedTextField.setFormatterFactory(dateFormatter);
      getContentPane().add(quoteDateFormattedTextField);
      quoteDateFormattedTextField.setBounds(120, 80, 100, 20);

      // add save button to form
      getContentPane().add(saveButton);
      saveButton.setBounds(40, 130, 100, 23);

      // add clear the button label
      getContentPane().add(clearButton);
      clearButton.setBounds(150, 130, 90, 23);

      // add close button to form
      getContentPane().add(closeButton);
      closeButton.setBounds(250, 130, 90, 23);

      // Set Size after creating
      this.setSize(400, 250);

      // center form
      setLocationRelativeTo(null);
      
      this.setTitle("Add Stock Quote");
   }

   // accessor methods for controller class to use to get the values from the form
   public JTextField getTickerSymbolTextfield() {
      return tickerSymbolTextfield;
   }

   public JTextField getStockValueTextField() {
      return stockValueTextField;
   }

   public JFormattedTextField getQuoteDateFormattedTextField() {
      return quoteDateFormattedTextField;
   }

}
