package one.digitalinnovation.labdesignpatternsspring.core.exceptions;

import org.springframework.http.HttpStatus;

import one.digitalinnovation.labdesignpatternsspring.core.enums.ResponsesErrorType;

public class ClientException extends ApiBusinessException{
    public ClientException(String message, HttpStatus statusCode, ResponsesErrorType errorType) {
        super(message, statusCode, errorType);
    }

        public ClientException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
