package com.example.SupplyChainScalable.DTO;

import com.example.SupplyChainScalable.Models.User;

public class SubscriptionResponse {
   
	private  User user;
	private  String message;
	public SubscriptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubscriptionResponse(User user, String message) {
		super();
		this.user = user;
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
