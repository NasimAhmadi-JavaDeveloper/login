package ir.tamin.teco.domain.exception;

public class CustomerCreditNotFoundException extends RuntimeException {
    public CustomerCreditNotFoundException(String message) {
        super(message);
    }
}