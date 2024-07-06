package com.example.SupplyChainScalable.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SupplyChainScalable.Models.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Set<Subscription> findByFarmerId(Long farmerId);
    Set<Subscription> findByBuyerId(Long buyerId);
    Subscription findByFarmerIdAndBuyerId(Long farmerId, Long buyerId);
}