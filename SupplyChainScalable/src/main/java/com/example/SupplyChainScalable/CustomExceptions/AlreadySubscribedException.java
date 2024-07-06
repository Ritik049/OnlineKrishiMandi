package com.example.SupplyChainScalable.CustomExceptions;

public class AlreadySubscribedException extends RuntimeException {
    
	public AlreadySubscribedException(String message) {
        super(message);
    }
}