package com.fy.tecnotreedemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CatchDataFailedException extends RuntimeException{

    private final HttpStatus httpStatus;
    private String message;

    public CatchDataFailedException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
