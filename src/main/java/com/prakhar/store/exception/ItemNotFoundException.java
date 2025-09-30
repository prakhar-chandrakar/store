package com.prakhar.store.exception;

/**
 * @author PrakharChandrakar
 */
public class ItemNotFoundException extends RuntimeException  {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
