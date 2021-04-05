package com.example.movietracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFound(final NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler({ResourceForbiddenException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String handleResourceForbidden(final ResourceForbiddenException e) {
        return e.getMessage();
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleBadRequest(final BadRequestException e) {
        return e.getMessage();
    }
}
