package com.example.SupplyChainScalable.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SupplyChainScalable.Models.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {

}
