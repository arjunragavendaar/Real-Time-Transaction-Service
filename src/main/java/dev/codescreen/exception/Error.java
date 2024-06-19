package dev.codescreen.exception;

import lombok.Getter;

public class Error {
    
   @Getter   
    public enum ERROR{
        SERVER_ERROR(500,"Internal Server Error");
        private int errorCode;
        private String errorMessage;

        ERROR(int errorCode,String errorMessage){
            this.errorCode=errorCode;
            this.errorMessage=errorMessage;
        }
    }
}
