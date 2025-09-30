package com.prakhar.store.exception;

/**
 * @author PrakharChandrakar
 */
public class InvalidOrderException extends  RuntimeException {
    public InvalidOrderException (String message) {
        super(message);
    }
}
