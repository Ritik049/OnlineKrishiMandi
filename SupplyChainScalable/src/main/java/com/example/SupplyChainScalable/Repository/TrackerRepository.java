package com.example.SupplyChainScalable.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SupplyChainScalable.Models.Tracker;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long> {
	
	List<Tracker> findByFarmerId(Long farmerId);

}
