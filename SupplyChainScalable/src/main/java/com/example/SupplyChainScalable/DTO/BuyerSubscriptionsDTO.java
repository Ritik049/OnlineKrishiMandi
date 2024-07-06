package com.example.SupplyChainScalable.DTO;

import java.util.List;

public class BuyerSubscriptionsDTO {
    private String buyerName;
    private List<FarmerDTO> farmers;

    public BuyerSubscriptionsDTO(String buyerName, List<FarmerDTO> farmers) {
        this.buyerName = buyerName;
        this.farmers = farmers;
    }

    // Getters and Setters
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<FarmerDTO> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<FarmerDTO> farmers) {
        this.farmers = farmers;
    }
}
