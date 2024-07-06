package com.example.SupplyChainScalable.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SupplyChainScalable.DTO.PredictionDataRequestDTO;
import com.example.SupplyChainScalable.Models.Crop;
import com.example.SupplyChainScalable.Models.PredictorData;
import com.example.SupplyChainScalable.Service.CropService;

@RestController
@RequestMapping("/crop")
public class CropController {

	
	 @Autowired
	 CropService service;
	 
	 
	 @GetMapping("/")
	 public List<Crop> getAll()
	 {
		 return service.getAll();
	 }
	 
	 @GetMapping("/{id}")
	 public Crop getById(@PathVariable Long id)
	 {
		 return service.getById(id);
	 }
	 
	 @PostMapping("/create")
	 public Crop create(@RequestBody Crop crop)
	 {
		 return service.create(crop);
	 }
	 
	 @PutMapping("/{cropId}/update")
	 public Crop update(@RequestBody Crop crop, @PathVariable Long cropId)
	 {
		return service.update(cropId, crop) ;
	 }
	 
	 @DeleteMapping("/{cropId}/delete")
	 public String delete(@PathVariable Long cropId)
	 {
	       return service.delete(cropId);
	 }
	 
	 
	 //Buyer
	 @PostMapping("/{buyerId}/addData/{cropId}")
	 public  PredictorData addData(@PathVariable Long buyerId, @PathVariable Long cropId, @RequestBody PredictionDataRequestDTO request)
	 {
		 return service.updateCropPrice(buyerId, cropId, request.getPrice(),request.getYear());
	 }
	 
	 @GetMapping("/{cropId}/getData")
	 public Set<PredictorData> getData(@PathVariable Long cropId)
	 {
		return service.getPredictorDataByCropId(cropId);
	 }
	 
	 
	 @GetMapping("/{cropId}/predictPrice/{year}/{strategy}")
	 public Double getPredictedPrice(@PathVariable Long cropId,@PathVariable Integer year,@PathVariable String strategy)
	 {
		 return service.getPredictedPrice(cropId,year,strategy);
	 }
	 
	 
} 
	 
	 

