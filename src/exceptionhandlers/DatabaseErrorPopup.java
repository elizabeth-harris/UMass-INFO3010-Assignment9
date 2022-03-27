package exceptionhandlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseErrorPopup {

    /**
     * Constructor takes the following data:
     * 
     * @param the main menu form - this is the only form that should display file IO errors
     * @param exception   - the exception that was thrown, create your own but you can add the 
     *                    - build in error too which sometimes helps the developers.
     * 
     * Creates a popup window that displays the error
     */
    public DatabaseErrorPopup(JFrame badFormData, Exception exception) {
        JOptionPane.showMessageDialog(badFormData,
                exception.getMessage(),
                "Datbase Error: ",
                JOptionPane.WARNING_MESSAGE);
    }
}
