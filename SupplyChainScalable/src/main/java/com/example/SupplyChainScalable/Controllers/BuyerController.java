package com.example.SupplyChainScalable.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SupplyChainScalable.CustomExceptions.NotSubscribedException;
import com.example.SupplyChainScalable.DTO.BuyerSubscriptionsDTO;
import com.example.SupplyChainScalable.Models.Buyer;
import com.example.SupplyChainScalable.Models.Notification;
import com.example.SupplyChainScalable.Models.Subscription;
import com.example.SupplyChainScalable.Repository.BuyerRepository;
import com.example.SupplyChainScalable.Service.NotificationService;
import com.example.SupplyChainScalable.Service.SubscriptionService;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
     SubscriptionService subscriptionService;

    @Autowired
     NotificationService notificationService;
    
    @Autowired
     BuyerRepository buyerRepository;

   
    
   @GetMapping("/allNotifications")
   public List<Notification> getAll()
   {
	   return notificationService.getAll();
   }

    
   @GetMapping("/{buyerId}/subscriptions")
   public ResponseEntity<BuyerSubscriptionsDTO> getSubscriptions(@PathVariable Long buyerId) {
       try {
           BuyerSubscriptionsDTO subscriptions = subscriptionService.getSubscriptionsByBuyer(buyerId);
           return new ResponseEntity<>(subscriptions, HttpStatus.OK);
       } catch (RuntimeException e) {
           return new ResponseEntity<>(null,  HttpStatus.NOT_FOUND);
       }
   }

   @PostMapping("/{buyerId}/unsubscribe/{farmerId}")
   public ResponseEntity<String> unsubscribe(@PathVariable Long buyerId, @PathVariable Long farmerId) {
       try {
           subscriptionService.buyerUnsubscribe(buyerId, farmerId);
           return new ResponseEntity<>("Unsubscribed successfully", HttpStatus.OK);
       } catch (NotSubscribedException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       } catch (RuntimeException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
   }
}