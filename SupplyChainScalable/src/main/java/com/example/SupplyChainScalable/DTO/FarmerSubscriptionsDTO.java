package com.example.SupplyChainScalable.DTO;

import java.util.List;

public class FarmerSubscriptionsDTO {
    private String farmerName;
    private List<BuyerDTO> subscriptions;

    public FarmerSubscriptionsDTO(String farmerName, List<BuyerDTO> subscriptions) {
        this.farmerName = farmerName;
        this.subscriptions = subscriptions;
    }

    // Getters and Setters
    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public List<BuyerDTO> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<BuyerDTO> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
