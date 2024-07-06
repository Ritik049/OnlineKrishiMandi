package com.example.SupplyChainScalable.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SupplyChainScalable.Models.Crop;
import com.example.SupplyChainScalable.Models.PredictorData;

@Repository
public interface PredictorRepository extends JpaRepository<PredictorData, Long> {
	
	Set<PredictorData> findByCropId(Long cropId);

}
