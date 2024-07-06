package com.example.SupplyChainScalable.DTO;

public class PredictionDataRequestDTO {
    private Double price;
    private Integer year;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
