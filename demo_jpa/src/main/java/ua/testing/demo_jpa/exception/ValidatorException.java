package ua.testing.demo_jpa.exception;

public class ValidatorException extends IllegalArgumentException {

    public ValidatorException(String errorMessage) {
        super(errorMessage);
    }
}
