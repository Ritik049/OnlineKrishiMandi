package com.example.SupplyChainScalable.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SupplyChainScalable.Models.Tracker;
import com.example.SupplyChainScalable.Service.TrackerService;

@RestController
@RequestMapping("/tracker")
public class TrackerController {
	
	@Autowired
	TrackerService service;
	
	@GetMapping("/")
	public List<Tracker>getAll()
	{
		return service.getAll();
	}
	
	@PostMapping("/{id}/create")
	public Tracker create(@PathVariable Long id , @RequestBody Tracker tracker)
	{
		return service.create(id, tracker);
	}
	
	
//	@GetMapping("/farmer/{farmerId}")
//	public Set<Tracker> findByFarmerId(@PathVariable Long farmerId)
//	{
//		return service.getByFarmerId(farmerId);
//	}
	
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Long id)
//    {
//    	return service.delete(id);
//    }
 
}
