/*
 * Listens for events on the report form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 *
 * Populates the form with data if there is any in the application data model
 */
package controllers.reportformcontrollers;

import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import datacontainers.InvestorDataContainer;
import view.reportforms.InvestorReportForm;

public class ListAllInvestorsController implements ActionListener, TreeSelectionListener {

   private InvestorReportForm form;
   private InvestorDataContainer investorDataContainer;

   // A table model will hold the data for the JTable (this is the M in MVC)
   private DefaultTableModel defaultTableModel = new DefaultTableModel();

   public ListAllInvestorsController(InvestorDataContainer investorDataContainer) {
      this.investorDataContainer = investorDataContainer;
      form = new InvestorReportForm(this);
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
    * Implements the valueChanged method in the TreeSelectionListener interface.
    */
   public void valueChanged(TreeSelectionEvent e) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.form.getInvestorTree().getLastSelectedPathComponent();

      if (node == null) {
         return;
      }

      Object nodeInfo = node.getUserObject();
      if (node.isLeaf()) {
         // Display investors info
      }
   }

   // Getters used by the controller
   public InvestorReportForm getForm() {
      return form;
   }

   public InvestorDataContainer getInvestorDataContainer() {
      return investorDataContainer;
   }

}
