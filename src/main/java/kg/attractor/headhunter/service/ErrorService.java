package kg.attractor.headhunter.service;

import kg.attractor.headhunter.exception.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult exception);
}
