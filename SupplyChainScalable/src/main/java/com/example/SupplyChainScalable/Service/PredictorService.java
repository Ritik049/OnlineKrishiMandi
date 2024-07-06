package com.example.SupplyChainScalable.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SupplyChainScalable.Models.PredictorData;
import com.example.SupplyChainScalable.Strategy.PricePredictionStrategy;

@Service
public class PredictorService {
     
	private final Map<String, PricePredictionStrategy> strategies;

    public PredictorService(Map<String, PricePredictionStrategy> strategies) {
        this.strategies = strategies;
    }

    public double predictedPrice(Set<PredictorData>st, int year,String strategyName) {
        PricePredictionStrategy strategy = strategies.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found with name: " + strategyName);
        }
        return strategy.predictPrice(st,year);
    }
	  
}
