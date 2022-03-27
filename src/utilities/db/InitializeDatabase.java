package utilities.db;

/**
 * This class is used to initialize the database. It has methods to drop all the
 * tables and create all the tables. NOTE: the database must already exist in
 * order to create and drop tables
 */
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import controllers.Application;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;

public final class InitializeDatabase {

    /**
     * Table creation Strings Used to initialize the database tables
     */
    private static final String createTables[] = {
        "CREATE TABLE stockquote (tickersymbol varchar(5), value float, date DateTime, PRIMARY KEY (tickersymbol))",
        "CREATE TABLE broker (id int, name varchar(80), address varchar(100), dateOfBirth DateTime, dateOfHire DateTime, dateOfTermination DateTime, salary float, status varchar(25), PRIMARY Key (id))",
        "CREATE TABLE investmentcompany (name varchar(80), PRIMARY KEY (name))",
        "CREATE TABLE investor (id int, name varchar(80), address varchar(100), dateOfBirth DateTime, memberSince DateTime, PRIMARY KEY (id))",
        "CREATE TABLE investorstockquote (id int, tickersymbol varchar(5))"
    };

    /**
     * Table deletion strings Used to delete the tables from the database.
     * Dropping tables will also remove all the data!
     */
    private static final String dropTables[] = {
        "DROP TABLE stockquote",
        "DROP TABLE broker",
        "DROP TABLE investmentcompany",
        "DROP TABLE investor",
        "DROP TABLE investorstockquote"
        };


    /**
     * Drops the tables listed in the dropTables array and removes all the data
     *
     * @throws SQLException
     */
    public static void dropTables() throws SQLException {
        Connection connection = DatabaseUtilities.openDatabaseConnection();
        try (Statement statement = connection.createStatement()) {
            for (int i = 0; i < createTables.length; i++) {
                try {
                    statement.execute(dropTables[i]);
                    Application.getAPPLICATION_LOGGER().finest("Database tables dropped: " + " uml");
                } catch (SQLException sqlException) {
                    Application.getAPPLICATION_LOGGER().finest("Problem dropping database tables for uml: " + sqlException.getMessage());
                }
            }
        }
    }

    /**
     * Create the tables listed in the createTables array
     */
    public static void createTables() throws SQLException {
        Connection connection = DatabaseUtilities.openDatabaseConnection();
        try ( Statement statement = connection.createStatement()) {
            for (String createTable : createTables) {
                try {
                    statement.execute(createTable);
                    Application.getAPPLICATION_LOGGER().finest("Database tables created: " + "uml");
                }catch (SQLException sqlException) {
                    Application.getAPPLICATION_LOGGER().finest("Problem creating database tables for uml: " + sqlException.getMessage());
                }
            }
        }
    }

    /**
     * Restore database to initial settings
     */
    public static void resetDatabase() throws SQLException {
        Application.getAPPLICATION_LOGGER().finest("Dropping and creating database tables for: " + " uml");
        dropTables();
        createTables();
    }

    /**
     * This method is available if you want to initialize the database external
     * to the application.
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            resetDatabase();
            Application.getAPPLICATION_LOGGER().finest("Resetting database uml:" + "");
        } catch (SQLException e) {
            Application.getAPPLICATION_LOGGER().finest("Problem resetting database uml: " + e.getMessage());
        }
    }

}
