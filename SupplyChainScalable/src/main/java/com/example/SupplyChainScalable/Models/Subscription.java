package com.example.SupplyChainScalable.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Subscription {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne                                 //joiing to subscription
	    @JoinColumn(name = "farmer_id")
	    
	    private Farmer farmer;

	    @ManyToOne
	    @JoinColumn(name = "buyer_id")

	    private Buyer buyer;

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

		public Buyer getBuyer() {
			return buyer;
		}

		public void setBuyer(Buyer buyer) {
			this.buyer = buyer;
		}

		public Subscription(Long id, Farmer farmer, Buyer buyer) {
			super();
			this.id = id;
			this.farmer = farmer;
			this.buyer = buyer;
		}

		public Subscription() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	    
}
