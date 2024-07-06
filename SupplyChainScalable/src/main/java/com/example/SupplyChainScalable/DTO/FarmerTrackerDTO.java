package com.example.SupplyChainScalable.DTO;

import java.util.List;

import com.example.SupplyChainScalable.Models.Tracker;

public class FarmerTrackerDTO {
    
	private String name;
	
	private List<Tracker> trackerList;

	public FarmerTrackerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FarmerTrackerDTO(String name, List<Tracker> trackerList) {
		super();
		this.name = name;
		this.trackerList = trackerList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tracker> getTrackerList() {
		return trackerList;
	}

	public void setTrackerList(List<Tracker> trackerList) {
		this.trackerList = trackerList;
	}
	
	
}
