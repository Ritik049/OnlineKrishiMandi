package com.example.SupplyChainScalable.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SupplyChainScalable.DTO.FarmerNotificationDTO;
import com.example.SupplyChainScalable.Models.Buyer;
import com.example.SupplyChainScalable.Models.Farmer;
import com.example.SupplyChainScalable.Models.Notification;
import com.example.SupplyChainScalable.Models.Subscription;
import com.example.SupplyChainScalable.Repository.FarmerRepository;
import com.example.SupplyChainScalable.Repository.NotificationRepository;
import com.example.SupplyChainScalable.Repository.SubscriptionRepository;

@Service
public class NotificationService {
   
	
	@Autowired
	private  FarmerRepository farmerRepository;
	
	
	@Autowired
    private NotificationRepository notificationRepository;
	

	@Autowired
	private SubscriptionRepository subscriptionRepository;

    public void notifySubscribers(Long buyerId, String message) {
        for (Subscription subscription : subscriptionRepository.findByBuyerId(buyerId)) {
            createNotification(subscription.getFarmer(), message);
        }
    }

    public void createNotification(Farmer farmer, String message) {
        Notification notification = new Notification();
        notification.setFarmer(farmer);
        notification.setMessage(message);
        notificationRepository.save(notification);
    }
    
    
    public List<Notification> getAll()
    {
    	return notificationRepository.findAll();
    }
    
    public FarmerNotificationDTO getFarmerNotificationsById(Long farmerId)
    {
    	 Farmer farmer = farmerRepository.findById(farmerId).orElseThrow(()->new RuntimeException("Farmer not exisit "));
    	 
    	  List<Notification> lst = notificationRepository.getByFarmerId(farmerId);
    	  
    	  FarmerNotificationDTO  farmerNotifications = new FarmerNotificationDTO(farmer.getName(),lst);
    	  
    	  return farmerNotifications;
    	  
    	 
    }
    
    
}
