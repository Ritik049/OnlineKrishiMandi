package com.example.SupplyChainScalable.CustomExceptions;

public class NotSubscribedException extends RuntimeException {
    public NotSubscribedException(String message) {
        super(message);
    }
}
