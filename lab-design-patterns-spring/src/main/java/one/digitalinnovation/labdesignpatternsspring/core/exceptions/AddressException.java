package one.digitalinnovation.labdesignpatternsspring.core.exceptions;

import org.springframework.http.HttpStatus;

import one.digitalinnovation.labdesignpatternsspring.core.enums.ResponsesErrorType;

public class AddressException extends ApiBusinessException{
    public AddressException(String message, HttpStatus statusCode, ResponsesErrorType errorType) {
        super(message, statusCode, errorType);
    }

        public AddressException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
