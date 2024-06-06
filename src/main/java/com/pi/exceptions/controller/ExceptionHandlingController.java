package com.pi.exceptions.controller;

import com.pi.exceptions.dto.MessageError;
import com.pi.exceptions.exception.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ResponseBody
    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<MessageError> validacaoError(ValidacaoException ex) {
        return List.of(new MessageError(ex.getMessage()));
    }

}
