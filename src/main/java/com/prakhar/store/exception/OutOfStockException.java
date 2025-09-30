package com.prakhar.store.exception;

/**
 * @author PrakharChandrakar
 */
public class OutOfStockException extends  RuntimeException {
    public OutOfStockException(String message ) {
        super(message);
    }
}
