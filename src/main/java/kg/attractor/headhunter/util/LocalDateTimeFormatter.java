package kg.attractor.headhunter.util;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter implements TemplateMethodModelEx {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy:MM:dd");

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() != 1) {
            throw new TemplateModelException("Invalid number of arguments. Expected 1.");
        }

        Object arg = arguments.get(0);
        if (arg instanceof LocalDateTime) {
            return formatter.format((LocalDateTime) arg);
        } else {
            throw new TemplateModelException("Argument must be of type LocalDateTime.");
        }
    }
}
