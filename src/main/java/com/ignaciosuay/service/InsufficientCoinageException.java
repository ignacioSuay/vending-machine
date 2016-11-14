package com.ignaciosuay.service;

/**
 * This exception is throw in case of insufficient coinage
 */
public class InsufficientCoinageException extends RuntimeException {

    public InsufficientCoinageException(String message) {
        super(message);
    }

    public InsufficientCoinageException(String message, Throwable cause) {
        super(message, cause);
    }
}
