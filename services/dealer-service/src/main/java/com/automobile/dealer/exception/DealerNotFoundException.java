package com.automobile.dealer.exception;

public class DealerNotFoundException extends RuntimeException {
    public DealerNotFoundException(String message) { super(message); }
}