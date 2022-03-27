package exceptionhandlers;

public class DatabaseException extends Exception {

    public DatabaseException() {
        super();
    }
    
    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }
}
