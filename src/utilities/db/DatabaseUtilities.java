package utilities.db;

/**
 * This class is used to store commonly used database functions such as 
 * connecting to the database.
 */
import controllers.Application;
import java.sql.*;
import java.sql.Connection;

public final class DatabaseUtilities {
   
    /**
     * Registers the driver code for the database.The driver communicates
     * between the Java JDBC and the database. Creates the database connection
     * object using the url string. This string contains the IP address of the
     * database and also the database name. In this case, the database name is
     * "uml".
     *
     * @return connection object
     */
    public static Connection openDatabaseConnection() {

        // Register the MySQL JDBC Driver
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Application.getAPPLICATION_LOGGER().finest("Database driver loaded: " + "uml");

            // This URL specifies we are connecting to a local database named uml.
            String url = "jdbc:mysql://localhost/uml";

            // Make a connection with the database. User is root, no password
            Connection connection = DriverManager.getConnection(url, "root", "user");
            Application.getAPPLICATION_LOGGER().finest("Getting database connection: " + "uml");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            Application.getAPPLICATION_LOGGER().finest("Problem getting database connection to uml: " + e.getMessage());
            return null;
        }
    }   
  
}
