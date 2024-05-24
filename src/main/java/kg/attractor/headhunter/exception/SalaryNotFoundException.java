package kg.attractor.headhunter.exception;

import java.util.NoSuchElementException;

public class SalaryNotFoundException extends NoSuchElementException {
    public SalaryNotFoundException() {
    }

    public SalaryNotFoundException(String message) {
        super(message);
    }
}
