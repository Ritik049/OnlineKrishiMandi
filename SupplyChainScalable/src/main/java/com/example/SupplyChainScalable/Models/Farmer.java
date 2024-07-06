package com.example.SupplyChainScalable.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Farmer  extends User{
    
	@OneToMany(mappedBy = "farmer",  cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"farmer", "buyer"})
	private Set<Subscription> subscriptions = new HashSet<>();
	
	@OneToMany(mappedBy = "farmer",  cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"farmer", "buyer"})
	private List<Tracker>trackerList = new ArrayList<>();
	
	
	
	@OneToMany(mappedBy = "farmer",  cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"farmer"})
	private List<Notification>notificationList= new ArrayList<>();
	
	

	public Farmer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Farmer(String name, String email, String password, String location, String image, String role) {
		super(name, email, password, location, image, role);

		// TODO Auto-generated constructor stub
	}
	 public Set<Subscription> getSubscriptions() {
	        return subscriptions;
	    }

	    public void setSubscriptions(Set<Subscription> subscriptions) {
	        this.subscriptions = subscriptions;
	    }
    
	    
	    private List<Tracker> getTracker(){
	    	return trackerList;
	    }
	  
	//putting buyer interaction with buyer in separate class subscription and notification
	
	
	//
	 
} 
