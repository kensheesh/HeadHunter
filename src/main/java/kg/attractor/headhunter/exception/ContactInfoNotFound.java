package kg.attractor.headhunter.exception;

import java.util.NoSuchElementException;

public class ContactInfoNotFound extends NoSuchElementException {
    public ContactInfoNotFound() {
    }

    public ContactInfoNotFound(String message) {
        super(message);
    }
}
