package com.example.SupplyChainScalable.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SupplyChainScalable.CustomExceptions.AlreadySubscribedException;
import com.example.SupplyChainScalable.CustomExceptions.BuyerNotFoundException;
import com.example.SupplyChainScalable.CustomExceptions.FarmerNotFoundException;
import com.example.SupplyChainScalable.CustomExceptions.NotSubscribedException;
import com.example.SupplyChainScalable.DTO.BuyerDTO;
import com.example.SupplyChainScalable.DTO.BuyerSubscriptionsDTO;
import com.example.SupplyChainScalable.DTO.FarmerDTO;
import com.example.SupplyChainScalable.DTO.FarmerSubscriptionsDTO;
import com.example.SupplyChainScalable.Models.Buyer;
import com.example.SupplyChainScalable.Models.Farmer;
import com.example.SupplyChainScalable.Models.Subscription;
import com.example.SupplyChainScalable.Repository.BuyerRepository;
import com.example.SupplyChainScalable.Repository.FarmerRepository;
import com.example.SupplyChainScalable.Repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    
    //subscribe
    public void subscribe(Long farmerId, Long buyerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer with ID " + farmerId + " not found"));
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Subscription existingSubscription = subscriptionRepository.findByFarmerIdAndBuyerId(farmer.getId(), buyer.getId());
        if (existingSubscription != null) {
            throw new AlreadySubscribedException("Farmer is already subscribed to this buyer.");
        }

        Subscription subscription = new Subscription();
        subscription.setFarmer(farmer);
        subscription.setBuyer(buyer);

        subscriptionRepository.save(subscription);
    }

    
    //Unsubscribe by farmer
    public void unsubscribe(Long farmerId, Long buyerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer with ID " + farmerId + " not found"));
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Subscription subscription = subscriptionRepository.findByFarmerIdAndBuyerId(farmer.getId(), buyer.getId());
        if (subscription == null) {
            throw new NotSubscribedException("Buyer is not subscribed to this farmer.");
        }

        subscriptionRepository.delete(subscription);
    }
    
    
    //List of subscription
    public FarmerSubscriptionsDTO getSubscriptionsByFarmer(Long farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer with ID " + farmerId + " not found"));

        Set<Subscription> subscriptions = subscriptionRepository.findByFarmerId(farmerId);
        List<BuyerDTO> buyerDTOs = subscriptions.stream()
                .map(subscription -> {
                    Buyer buyer = subscription.getBuyer();
                    return new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail(), buyer.getLocation());
                })
                .collect(Collectors.toList());

        return new FarmerSubscriptionsDTO(farmer.getName(), buyerDTOs);
    }

    public BuyerSubscriptionsDTO getSubscriptionsByBuyer(Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new BuyerNotFoundException("Buyer with ID " + buyerId + " not found"));

        Set<Subscription> subscriptions = subscriptionRepository.findByBuyerId(buyerId);
        List<FarmerDTO> farmerDTOs = subscriptions.stream()
                .map(subscription -> {
                    Farmer farmer = subscription.getFarmer();
                    return new FarmerDTO(farmer.getId(), farmer.getName(), farmer.getEmail(), farmer.getLocation());
                })
                .collect(Collectors.toList());

        return new BuyerSubscriptionsDTO(buyer.getName(), farmerDTOs);
    }
    
    public void buyerUnsubscribe(Long buyerId, Long farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer with ID " + farmerId + " not found"));
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new BuyerNotFoundException("Buyer with ID " + buyerId + " not found"));

        Subscription subscription = subscriptionRepository.findByFarmerIdAndBuyerId(farmerId, buyerId);
        if (subscription == null) {
            throw new NotSubscribedException("Buyer is not subscribed to this farmer.");
        }
  
        subscriptionRepository.delete(subscription);
    }


}
