package com.fy.tecnotreedemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentNotFoundException extends RuntimeException{

    private final HttpStatus httpStatus;
    private String message;

    public CommentNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
