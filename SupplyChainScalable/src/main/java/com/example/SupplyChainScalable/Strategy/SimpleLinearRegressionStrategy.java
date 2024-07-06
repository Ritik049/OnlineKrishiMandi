package com.example.SupplyChainScalable.Strategy;



import com.example.SupplyChainScalable.Models.PredictorData;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("Regression")
public class SimpleLinearRegressionStrategy implements PricePredictionStrategy {

    @Override
    public  Double predictPrice(Set<PredictorData> data, int year) {
        if (data == null || data.isEmpty()) {
            return 0.0;
        }

        // Separate data points by year
        Map<Integer, List<Double>> yearDataMap = new HashMap<>();
        for (PredictorData pd : data) {
            int pdYear = pd.getYear();
            if (!yearDataMap.containsKey(pdYear)) {
                yearDataMap.put(pdYear, new ArrayList<>());
            }
            yearDataMap.get(pdYear).add(pd.getPrice());
        }

        // Prepare input arrays for Polynomial Regression
        int n = yearDataMap.size();
        double[] years = new double[n];
        double[] prices = new double[n];

        int index = 0;
        for (Map.Entry<Integer, List<Double>> entry : yearDataMap.entrySet()) {
            int pdYear = entry.getKey();
            List<Double> pricesList = entry.getValue();

            // Use average price for the year (you can adjust this as needed)
            double averagePrice = calculateAverage(pricesList);
            years[index] = pdYear;
            prices[index] = averagePrice;
            index++;
        }

        // Perform Polynomial Regression
        double[] regressionParams = calculatePolynomialRegression(years, prices);

        // Use regression parameters to predict price for the specified year
        double predictedPrice = predictPriceForYear(year, regressionParams);
        return predictedPrice;
    }

    // Method to calculate average of a list of prices
    private static double calculateAverage(List<Double> pricesList) {
        double sum = 0.0;
        for (double price : pricesList) {
            sum += price;
        }
        return sum / pricesList.size();
    }

    // Method to perform Polynomial Regression
    private static double[] calculatePolynomialRegression(double[] x, double[] y) {
        
        return simpleLinearRegression(x, y);
    }

    // Example: Simple linear regression (replace with actual polynomial regression)
    private static double[] simpleLinearRegression(double[] x, double[] y) {
        int n = x.length;
        double sumX = 0.0, sumY = 0.0, sumX2 = 0.0, sumXY = 0.0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumX2 += x[i] * x[i];
            sumXY += x[i] * y[i];
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        return new double[]{slope, intercept};
    }

    // Method to predict price for a given year using regression parameters
    private static double predictPriceForYear(int year, double[] regressionParams) {
        double slope = regressionParams[0];
        double intercept = regressionParams[1];
        return slope * year + intercept;
    }
}

