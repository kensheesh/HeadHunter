package kg.attractor.headhunter.exception;

import java.util.NoSuchElementException;

public class WorkExperienceNotFoundException extends NoSuchElementException {
    public WorkExperienceNotFoundException() {
    }

    public WorkExperienceNotFoundException(String message) {
        super(message);
    }
}
