package com.example.SupplyChainScalable.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PredictorData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double price;
    
    private Integer year;  // New attribute

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    
    @ManyToOne
    @JoinColumn(name  = "crop_id")
    private Crop crop;

    public PredictorData() {
        super();
    }

    public PredictorData(Double price, Integer year, Buyer buyer, Crop crop) {
        super();
        this.price = price;
        this.year = year;
        this.buyer = buyer;
        this.crop = crop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
