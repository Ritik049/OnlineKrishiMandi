package com.example.SupplyChainScalable.Models;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Buyer extends User {
  
	
	@OneToMany(mappedBy= "buyer",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties({"farmer", "buyer"})
	private  Set<Subscription>subscriptions = new HashSet<>();
	
	@OneToMany(mappedBy="buyer",cascade= CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties({"buyer","crop"})
	private Set<PredictorData>predictorData = new HashSet<>();
	
	

	
	public Buyer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Buyer(String name, String email, String password, String location, String image, String role) {
		super(name, email, password, location, image, role);
		// TODO Auto-generated constructor stub
	}
	
	 public Set<Subscription> getSubscriptions() {
	        return subscriptions;
	    }

//	   public void setSubscriptions(Set<Subscription> subscriptions) {
//	        this.subscriptions = subscriptions;
//	    }

}
