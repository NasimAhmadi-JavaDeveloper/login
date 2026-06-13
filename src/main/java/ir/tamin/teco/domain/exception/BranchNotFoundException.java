package ir.tamin.teco.domain.exception;

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(String message) {
        super(message);
    }
}