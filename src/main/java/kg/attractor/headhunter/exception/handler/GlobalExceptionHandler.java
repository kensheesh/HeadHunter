package kg.attractor.headhunter.exception.handler;

import kg.attractor.headhunter.exception.ErrorResponseBody;
import kg.attractor.headhunter.exception.UserNotFoundException;
import kg.attractor.headhunter.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> userNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(errorService.makeResponse(exception), HttpStatus.NOT_FOUND);
    }
}
