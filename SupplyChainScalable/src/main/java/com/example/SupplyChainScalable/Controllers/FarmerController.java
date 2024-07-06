package com.example.SupplyChainScalable.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SupplyChainScalable.CustomExceptions.AlreadySubscribedException;
import com.example.SupplyChainScalable.CustomExceptions.NotSubscribedException;
import com.example.SupplyChainScalable.DTO.FarmerNotificationDTO;
import com.example.SupplyChainScalable.DTO.FarmerSubscriptionsDTO;
import com.example.SupplyChainScalable.DTO.FarmerTrackerDTO;
import com.example.SupplyChainScalable.Models.Tracker;
import com.example.SupplyChainScalable.Service.NotificationService;
import com.example.SupplyChainScalable.Service.SubscriptionService;
import com.example.SupplyChainScalable.Service.TrackerService;
import com.example.SupplyChainScalable.Service.UserService;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserService userService;
    
    @Autowired
    TrackerService trackerService;
    
    @Autowired
    NotificationService notificationService;
    
    //Subscription

    @PostMapping("/{farmerId}/subscribe/{buyerId}")
    public ResponseEntity<String> subscribe(@PathVariable Long farmerId, @PathVariable Long buyerId) {
        try {
            subscriptionService.subscribe(farmerId, buyerId);
            return new ResponseEntity<>("Subscribed successfully", HttpStatus.OK);
        } catch (AlreadySubscribedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{farmerId}/unsubscribe/{buyerId}")
    public ResponseEntity<String> unsubscribe(@PathVariable Long farmerId, @PathVariable Long buyerId) {
        try {
            subscriptionService.unsubscribe(farmerId, buyerId);
            return new ResponseEntity<>("Unsubscribed successfully", HttpStatus.OK);
        } catch (NotSubscribedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{farmerId}/subscriptions")
    public ResponseEntity<FarmerSubscriptionsDTO> getSubscriptions(@PathVariable Long farmerId) {
        try {
            FarmerSubscriptionsDTO subscriptions = subscriptionService.getSubscriptionsByFarmer(farmerId);
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
   
    
    @GetMapping("/{farmerId}/getNotifications")
    public ResponseEntity<FarmerNotificationDTO> getNotifications(@PathVariable Long farmerId)
    {
    	 try {
             FarmerNotificationDTO notifications = notificationService.getFarmerNotificationsById(farmerId);
             return new ResponseEntity<>(notifications, HttpStatus.OK);
         } catch (RuntimeException e) {
             return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
         }
    }
    
    
    
    //Tracker
    @PostMapping("/{farmerId}/tracker/create")
    public ResponseEntity<Tracker> createTracker(@PathVariable Long farmerId, @RequestBody Tracker tracker)
    {
    	try {
    		 return new ResponseEntity<>(trackerService.create(farmerId, tracker),HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		throw new RuntimeException(e.getMessage());
    	}
    }
    
    @GetMapping("/{farmerId}/tracker/getTrackerList")
    public ResponseEntity<FarmerTrackerDTO> getTrackerList(@PathVariable Long farmerId)
    {
    	try {
    		
    		return new ResponseEntity<>(trackerService.getByFarmerId(farmerId),HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		throw new RuntimeException(e.getMessage());
    	}
    }
    
    @PutMapping("{farmerId}/tracker/updateTracker/{trackerId}")
    public ResponseEntity<Tracker> updateTracker(@PathVariable Long farmerId, @PathVariable Long trackerId, @RequestBody Tracker tracker)
    {
    	try {
    		
    		 return new ResponseEntity<>(trackerService.update(farmerId,trackerId, tracker),HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		throw new RuntimeException(e.getMessage());
    	}
    }
    
    @DeleteMapping("{farmerId}/tracker/deleteTracker/{trackerId}")
    public ResponseEntity<String> deleteTracker(@PathVariable Long  farmerId, @PathVariable Long trackerId)
    {
    	try {
    		return new ResponseEntity<>(trackerService.delete(farmerId,trackerId),HttpStatus.OK);
    	}
    	
    	catch(Exception e)
    	{
    		throw new RuntimeException(e.getMessage());
    	}
    }

    
}
