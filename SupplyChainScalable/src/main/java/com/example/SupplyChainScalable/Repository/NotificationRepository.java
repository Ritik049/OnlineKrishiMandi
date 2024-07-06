package com.example.SupplyChainScalable.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SupplyChainScalable.Models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> getByFarmerId(Long farmerId);
}
