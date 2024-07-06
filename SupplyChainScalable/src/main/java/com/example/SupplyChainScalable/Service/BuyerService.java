package com.example.SupplyChainScalable.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.SupplyChainScalable.Models.Buyer;
import com.example.SupplyChainScalable.Repository.BuyerRepository;

@Service
public class BuyerService {

	 
	 @Autowired
     BuyerRepository buyerRepository;
	 
	 @Autowired
	 NotificationService notificationService;

  
    public void notifyFarmers( Long buyerId, String message) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
        notificationService.notifySubscribers(buyerId, message);
    }
    
    
    
} 
