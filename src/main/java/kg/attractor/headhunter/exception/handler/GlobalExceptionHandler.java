package kg.attractor.headhunter.exception.handler;

import kg.attractor.headhunter.exception.*;
import kg.attractor.headhunter.model.WorkExperienceInfo;
import kg.attractor.headhunter.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, List<String>>> createErrorResponse(HttpStatus status, String message) {
        List<String> errors = Collections.singletonList(message);
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, List<String>>> handleAccessDeniedException(AccessDeniedException ex) {
        return createErrorResponse(HttpStatus.FORBIDDEN, "Недостаточно прав для доступа к этому ресурсу");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(UserNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, List<String>>> handleRuntimeException(RuntimeException ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleResumeNotFoundException(ResumeNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EducationInfoNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleEducationInfoNotFoundException(EducationInfoNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(WorkExperienceNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleEducationInfoNotFoundException(WorkExperienceNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ContactInfoNotFound.class)
    public ResponseEntity<Map<String, List<String>>> handleEducationInfoNotFoundException(ContactInfoNotFound ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(VacancyNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleEducationInfoNotFoundException(VacancyNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
