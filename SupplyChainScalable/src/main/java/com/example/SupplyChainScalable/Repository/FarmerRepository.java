package com.example.SupplyChainScalable.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SupplyChainScalable.Models.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer,Long> {

}
