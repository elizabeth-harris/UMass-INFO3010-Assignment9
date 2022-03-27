package view.reportforms;

import controllers.reportformcontrollers.ListAllInvestorsController;
import datamodels.Investor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import utilities.date.DateFunctions;

/**
 * This class contains all the code for the investor report form. It creates the
 * form and populates the form with the data from the investor data model
 */
public class InvestorReportForm extends JFrame {

    // Form components
    
    private JButton closeButton = new JButton("Close Report");
    private JScrollPane treeScrollPane;
    private JTree investorTree;
    private DefaultMutableTreeNode topLevelNode;
    private JTextArea investorData;

    public InvestorReportForm(ListAllInvestorsController controller) {

        // Closes the form by default
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Add to form in the bottom of the panel
        getContentPane().add(closeButton, BorderLayout.SOUTH);
        topLevelNode = new DefaultMutableTreeNode("Investors");

        // Create the tree with the top level node
        investorTree = new JTree(topLevelNode);
        // Create a scroll panel for the tree
        treeScrollPane = new JScrollPane(investorTree);
        // Allow only one tree selection at a time
        investorTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Listen for when the selection changes.
        investorTree.addTreeSelectionListener(controller);

        // Set up the text area for investor data
        investorData = new JTextArea();

        //Add the tree pane and the investor detail pain to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeScrollPane);
        splitPane.setBottomComponent(investorData);

        // Set the default split location and size of the pane
        splitPane.setDividerLocation(175);
        splitPane.setPreferredSize(new Dimension(500, 300));

        // Add the split pane to the form
        getContentPane().add(splitPane, BorderLayout.CENTER);

        // Once the form is initialized, retrieve the data from the passed in 
        // data model
        List<Investor> listOfInvestors = controller.getInvestorDataContainer().getInvestorList();
                
        // Call private method that will add all investors to the tree
        this.populateTree(listOfInvestors);

        // Link the controller to the button
        closeButton.addActionListener(controller);

         this.setTitle("Investor Report");
         
        // Set the size of the form
        setSize(800, 350);

    }

    private void populateTree(List<Investor> listOfInvestors) {

        // Add all investors to the tree
        for (Investor investor : listOfInvestors) {

            // Top level node for each investor is the name field
            DefaultMutableTreeNode oneIinvestor = new DefaultMutableTreeNode(investor.getName());

            // All the other meta data fields are child nodes of the name field
            DefaultMutableTreeNode address = new DefaultMutableTreeNode("Address:"+investor.getAddress());
            DefaultMutableTreeNode id = new DefaultMutableTreeNode("Id:"+investor.getId());
            DefaultMutableTreeNode dateOfBirth = 
                    new DefaultMutableTreeNode("Date of Birth:"+DateFunctions.dateToString(investor.getDateOfBirth()));
            DefaultMutableTreeNode memberSince = 
                    new DefaultMutableTreeNode("Member since:"+DateFunctions.dateToString(investor.getMemberSince()));
            DefaultMutableTreeNode accountValue = new DefaultMutableTreeNode("Account value:"+investor.getAccountValue());

            oneIinvestor.add(address);
            oneIinvestor.add(id);
            oneIinvestor.add(dateOfBirth);
            oneIinvestor.add(memberSince);
            oneIinvestor.add(accountValue);

            // Add the investor node to the tree
            topLevelNode.add(oneIinvestor);
        }
    }

    // Getters to access the controls on the form
    public JButton getCloseButton() {
        return closeButton;
    }

    public JTree getInvestorTree() {
        return investorTree;
    }

    public DefaultMutableTreeNode getTopLevelNode() {
        return topLevelNode;
    }

}
