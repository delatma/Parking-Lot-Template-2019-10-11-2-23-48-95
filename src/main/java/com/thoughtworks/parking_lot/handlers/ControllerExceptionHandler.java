package com.thoughtworks.parking_lot.handlers;

import com.thoughtworks.parking_lot.errors.CustomErrors;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public CustomErrors handleNotFoundException(NotFoundException e){
        CustomErrors customError = new CustomErrors();
        customError.setErrorCode(HttpStatus.NOT_FOUND.value());
        customError.setMessage(e.getMessage());

        return customError;
    }
}