package exceptionhandlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileIOErrorPopup {

    /**
     * Constructor takes the following data:
     * 
     * @param the main menu form - this is the only form that should display file IO errors
     * @param exception   - the exception that was thrown, create your own but you can add the 
     *                    - build in error too which sometimes helps the developers.
     * 
     * Creates a popup window that displays the error
     */
    public FileIOErrorPopup(JFrame badFormData, Exception exception) {
        JOptionPane.showMessageDialog(badFormData,
                exception.getMessage(),
                "FIle IO Error: ",
                JOptionPane.WARNING_MESSAGE);
    }
}
