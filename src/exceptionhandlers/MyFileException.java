package exceptionhandlers;

public class MyFileException extends Exception {

    public MyFileException() {
        super();
    }
    
    public MyFileException(String errorMessage) {
        super(errorMessage);
    }
}
