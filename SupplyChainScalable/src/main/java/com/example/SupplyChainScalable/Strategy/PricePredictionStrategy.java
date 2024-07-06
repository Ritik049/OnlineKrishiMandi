package com.example.SupplyChainScalable.Strategy;

import com.example.SupplyChainScalable.Models.PredictorData;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public interface PricePredictionStrategy {
    Double predictPrice(Set<PredictorData> data,int year);
}
