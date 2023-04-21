package com.tkt.Exception;

public class BusNotFoundException extends RuntimeException{
    public BusNotFoundException() {
    }

    public BusNotFoundException(String message) {
        super(message);
    }
}
