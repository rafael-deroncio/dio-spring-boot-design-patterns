package one.digitalinnovation.labdesignpatternsspring.core.exceptions;

import org.springframework.http.HttpStatus;
import one.digitalinnovation.labdesignpatternsspring.core.enums.ResponsesErrorType;

public class ApiBusinessException extends RuntimeException {

    private ResponsesErrorType errorType;
    private HttpStatus statusCode;

    public ApiBusinessException(String message, HttpStatus statusCode, ResponsesErrorType errorType) {
        super(message);
        this.errorType = errorType;
        this.statusCode = statusCode;
    }

    public ApiBusinessException(String message, HttpStatus statusCode) {
        this(message, statusCode, ResponsesErrorType.ERROR);
    }

    public ApiBusinessException(String message, Object... params) {
        super(String.format(message, params));
    }

    public ResponsesErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ResponsesErrorType errorType) {
        this.errorType = errorType;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}