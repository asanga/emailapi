package com.aviation.emailapi.controller.exception;

import com.aviation.emailapi.model.response.ErrorResponse;
import com.aviation.emailapi.model.response.ValidationError;
import com.aviation.emailapi.model.response.Violation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationError onConstraintValidationException(ConstraintViolationException e) {
        ValidationError error = new ValidationError();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse onInvalidException(InvalidRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(exception.getErrorCode());
        errorResponse.setErrorMsg(exception.getErrorMsg());
        return errorResponse;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    ErrorResponse onCommonException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        errorResponse.setErrorMsg(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        return errorResponse;
    }
}
