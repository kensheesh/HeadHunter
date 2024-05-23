package kg.attractor.headhunter.exception;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
    }

    public VacancyNotFoundException(String message) {
        super(message);
    }
}
