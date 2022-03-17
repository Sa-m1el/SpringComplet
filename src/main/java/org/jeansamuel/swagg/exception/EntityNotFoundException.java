package org.jeansamuel.swagg.exception;

import lombok.Getter;

public class EntityNotFoundException extends Exception{

    @Getter
    private ErrorCodes errorCodes;

    public EntityNotFoundException(String message){
        super(message);
    }
    public EntityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public EntityNotFoundException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }
}
