package kg.attractor.headhunter.exception;

public class VacancyNotFoundException extends Exception{
    public VacancyNotFoundException() {
    }

    public VacancyNotFoundException(String message) {
        super(message);
    }
}
