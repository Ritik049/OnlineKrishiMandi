package com.example.SupplyChainScalable.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
         
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
     
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Notification(Long id, Farmer farmer, String message) {
		super();
		this.id = id;
		this.farmer = farmer;
		this.message = message;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
    

}
