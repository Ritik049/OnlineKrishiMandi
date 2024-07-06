package com.example.SupplyChainScalable.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SupplyChainScalable.CustomExceptions.FarmerNotFoundException;
import com.example.SupplyChainScalable.DTO.FarmerTrackerDTO;
import com.example.SupplyChainScalable.Models.Farmer;
import com.example.SupplyChainScalable.Models.Tracker;
import com.example.SupplyChainScalable.Repository.FarmerRepository;
import com.example.SupplyChainScalable.Repository.TrackerRepository;

@Service
public class TrackerService {
    
	  //CRUD
	@Autowired 
	TrackerRepository repo;
	
	@Autowired
	FarmerRepository farmerRepo;
	
	public List<Tracker> getAll()
	{
		return repo.findAll();
	}
	
	
	
	public Tracker getById(Long id)     //This is particular with id
	{
	
		return repo.findById(id).orElseThrow(()->new RuntimeException("Tracker Not found with given id "+ id));	 
	}
	
	
	
	public FarmerTrackerDTO getByFarmerId(Long farmerId)
	{
	   Farmer farmer = farmerRepo.findById(farmerId).orElseThrow(()->new FarmerNotFoundException("Farmer not exist"));
	     
	   List<Tracker> trackerList =  repo.findByFarmerId(farmerId);
	   
	   FarmerTrackerDTO  farmerTrackerDTO = new FarmerTrackerDTO(farmer.getName(),trackerList);
	   
	   return farmerTrackerDTO;
	}
	
	
	
	public Tracker create (Long farmerId, Tracker tracker)
	{
	   
		Farmer farmer  =  farmerRepo.findById(farmerId).orElseThrow(()->new FarmerNotFoundException("Farmer not exist"));
	     
		tracker.setFarmer(farmer);
		
		return repo.save(tracker);
	}
	
	
	public Tracker update(Long farmerId ,Long trackerId, Tracker tracker )
	{
		Farmer farmer  =  farmerRepo.findById(farmerId).orElseThrow(()->new FarmerNotFoundException("Farmer not exist"));
		
		
		Tracker existingTracker = repo.findById(trackerId).orElseThrow(()->new RuntimeException("Tracker does not exist"));
		
		if(existingTracker.getFarmer().getId()!=farmerId) 
		{
			throw new RuntimeException("Tracker does not exist with this farmerId");
		}
		
		existingTracker.setCrop(tracker.getCrop());
		existingTracker.setInput(tracker.getInput());
		existingTracker.setOutput(tracker.getOutput());
		
		return repo.save(existingTracker);
	}
	
	
	public String delete(Long farmerId,Long trackerId)
	{
		Farmer farmer  =  farmerRepo.findById(farmerId).orElseThrow(()->new FarmerNotFoundException("Farmer not exist"));
		
		Tracker existingTracker = repo.findById(trackerId).orElseThrow(()->new RuntimeException("Tracker does not exist"));
		
		repo.deleteById(trackerId);
		
		return "Successfully Deleted";
		
		  
	}
}
