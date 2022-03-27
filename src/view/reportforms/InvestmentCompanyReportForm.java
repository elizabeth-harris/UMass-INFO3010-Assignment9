package view.reportforms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

/**
 * This class creates a report form which displays all companies
 */
public class InvestmentCompanyReportForm extends JFrame  {

     // Form controls        
        
    private JButton closeButton;
    private JLabel label1;
    private JLabel label2;
    private JScrollPane scrollPanel1;
    private JScrollPane scrollPanel2;
    private JTable companiesTableDisplay;
    private JTextArea companiesTextArea;

    /**
     * Constructor to create new form
     */
    public InvestmentCompanyReportForm() {
     
        /** use default constructors to create the form components and then use 
         * setters to to set the values 
         */
        label1 = new JLabel();
        scrollPanel1 = new JScrollPane();
        companiesTextArea = new JTextArea();
        label2 = new JLabel();
        closeButton = new JButton();
        scrollPanel2 = new JScrollPane();
        companiesTableDisplay = new JTable();

         /** set close function to dispose of the form */
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        /** set the form layout to null, we'll place the components manually 
         *  using x,y layout positions
         */
        getContentPane().setLayout(null);

        // Set text of label component
        label1.setText("List Of Companies at A Glance");
           /** Add component to the form */
        getContentPane().add(label1);
        label1.setBounds(275, 20, 210, 14);

        companiesTextArea.setColumns(20);
        companiesTextArea.setRows(5);
        scrollPanel1.setViewportView(companiesTextArea);

        getContentPane().add(scrollPanel1);
        scrollPanel1.setBounds(10, 45, 720, 124);

        label2.setText("Company Detailed Data");
        getContentPane().add(label2);
        label2.setBounds(290, 190, 300, 14);

        closeButton.setText("Close Report");
        getContentPane().add(closeButton);
        closeButton.setBounds(300, 380, 120, 23);

        scrollPanel2.setViewportView(companiesTableDisplay);
        companiesTableDisplay.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (companiesTableDisplay.getColumnModel().getColumnCount() > 0) {
            companiesTableDisplay.getColumnModel().getColumn(0).setResizable(false);
            companiesTableDisplay.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(scrollPanel2);
        scrollPanel2.setBounds(10, 220, 720, 138);
        
        this.setTitle("Investment Companies Report");
        
        setSize(750, 450);       
    }

    // Getters to access the components on the form

    public JButton getCloseButton() {
        return closeButton;
    }

    public JTable getCompaniesTableDisplay() {
        return companiesTableDisplay;
    }

    public JTextArea getCompaniesTextArea() {
        return companiesTextArea;
    }
    
  
}
