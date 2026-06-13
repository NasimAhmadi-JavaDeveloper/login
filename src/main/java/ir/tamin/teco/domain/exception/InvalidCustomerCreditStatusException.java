package ir.tamin.teco.domain.exception;

public class InvalidCustomerCreditStatusException extends RuntimeException {
    public InvalidCustomerCreditStatusException(String message) {
        super(message);
    }
}