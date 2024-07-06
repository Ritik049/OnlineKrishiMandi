package com.example.SupplyChainScalable.Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Tracker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Double input;
	private Double output;
	private String crop;
	private int year;
	
	
	
	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private Farmer farmer; 
	
	
	
	public void setFarmer(Farmer farmer)
	{
		this.farmer = farmer;
	}
	
	public Farmer getFarmer()
	{
		return farmer;
	}
	
	public Tracker() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tracker(Double input, Double output, String crop,Integer year) {
		super();
	
		this.input = input;
		this.output = output;
		this.crop = crop;
		this.year  = year;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Double getInput() {
		return input;
	}
	public void setInput(Double input) {
		this.input = input;
	}
	public Double getOutput() {
		return output;
	}
	public void setOutput(Double output) {
		this.output = output;
	}
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	
	
	

}
