package exceptions;

public class InvalidContactDetails extends Exception{
    public InvalidContactDetails() {
    }

    public InvalidContactDetails(String message) {
        super(message);
    }
}
