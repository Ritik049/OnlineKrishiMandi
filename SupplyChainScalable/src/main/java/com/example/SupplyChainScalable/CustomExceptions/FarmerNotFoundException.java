package com.example.SupplyChainScalable.CustomExceptions;

public class FarmerNotFoundException extends RuntimeException {
    public FarmerNotFoundException(String message) {
        super(message);
    }
}
 