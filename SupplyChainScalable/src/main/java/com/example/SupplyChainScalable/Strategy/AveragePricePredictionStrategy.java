package com.example.SupplyChainScalable.Strategy;

import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.SupplyChainScalable.Models.PredictorData;


@Service("Average")
public class AveragePricePredictionStrategy  implements PricePredictionStrategy{

	 @Override
	   public Double predictPrice(Set<PredictorData> data, int year) {
	        Double price = 0.0;
	        int cnt = 0;

	        for (PredictorData d : data) {
	         if(d.getYear()==year)	
	            {price += d.getPrice();
	            cnt++;}
	        }
	        if (cnt == 0) return 0.0;

	        return price / cnt;
	    }
}
