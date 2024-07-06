package com.example.SupplyChainScalable.CustomExceptions;

public class BuyerNotFoundException extends RuntimeException {
   
	public BuyerNotFoundException(String message)
	{
		super(message);
	}
}
