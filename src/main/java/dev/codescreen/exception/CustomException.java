package dev.codescreen.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {
     Error.ERROR error;

    public CustomException(Error.ERROR errorCode) {
        super(errorCode.getErrorMessage());
        this.error = errorCode;
    }

    
}