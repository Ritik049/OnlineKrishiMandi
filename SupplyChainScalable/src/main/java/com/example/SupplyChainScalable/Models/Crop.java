package com.example.SupplyChainScalable.Models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Crop {
        
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy="crop",cascade= CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties({"buyer","crop"})
	private Set<PredictorData>predictorData = new HashSet<>();
	
	
	public Crop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Crop(String name, String description) {
		super();
	
		this.name = name;
		this.description = description;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
	
}
