package one.digitalinnovation.labdesignpatternsspring.core.handlers;

import one.digitalinnovation.labdesignpatternsspring.core.exceptions.AddressException;
import one.digitalinnovation.labdesignpatternsspring.core.exceptions.ClientException;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.ExceptionResponse;

import java.lang.reflect.UndeclaredThrowableException;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Resource
    private MessageSource messageSource;

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ExceptionResponse exceptionResponse(String message, String type, int code) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(type);
        exceptionResponse.setError(message);
        exceptionResponse.setStatusCode(code);
        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        if (e instanceof UndeclaredThrowableException) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            Throwable undeclaredThrowable = exception.getUndeclaredThrowable();
    
            if (undeclaredThrowable instanceof ClientException) {
                return handleApiException((ClientException) undeclaredThrowable, request);
            } else if (undeclaredThrowable instanceof AddressException) {
                return handleApiException((AddressException) undeclaredThrowable, request);
            }
        }
    
        // If it's not a known exception type, handle it as a general exception
        String message = messageSource.getMessage("error.server", new Object[] { e.getMessage() }, null);
        ExceptionResponse error = exceptionResponse(message, "error", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    

    @ExceptionHandler({ ClientException.class })
    private ResponseEntity<Object> handleApiException(ClientException exception, WebRequest request) {
        ExceptionResponse response = exceptionResponse(
                exception.getMessage().toString(),
                exception.getErrorType().toString(),
                exception.getStatusCode().value());
        return handleExceptionInternal(exception, response, headers(), exception.getStatusCode(), request);
    }

    @ExceptionHandler({ AddressException.class })
    private ResponseEntity<Object> handleApiException(AddressException exception, WebRequest request) {
        ExceptionResponse response = exceptionResponse(
                exception.getMessage().toString(),
                exception.getErrorType().toString(),
                exception.getStatusCode().value());
        return handleExceptionInternal(exception, response, headers(), exception.getStatusCode(), request);
    }
}