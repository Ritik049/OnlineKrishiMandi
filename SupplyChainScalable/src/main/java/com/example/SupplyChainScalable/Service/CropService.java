package com.example.SupplyChainScalable.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SupplyChainScalable.Models.Buyer;
import com.example.SupplyChainScalable.Models.Crop;
import com.example.SupplyChainScalable.Models.PredictorData;
import com.example.SupplyChainScalable.Repository.BuyerRepository;
import com.example.SupplyChainScalable.Repository.CropRepository;
import com.example.SupplyChainScalable.Repository.PredictorRepository;

@Service
public class CropService {

    @Autowired 
    private CropRepository repo;

    @Autowired
    private PredictorRepository predictorRepo;

    @Autowired
    private BuyerRepository buyerRepo;
    
    @Autowired
    private PredictorService predictorService;
    
    @Autowired
    private NotificationService notificationService; 

    public Crop create(Crop crop) {
        if (crop == null) {
            throw new RuntimeException("Crop data is invalid.");
        }
        return repo.save(crop);
    }

    public List<Crop> getAll() {
        return repo.findAll();
    }

    public Crop getById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Crop not found with id: " + id));
    }

    public Crop update(Long id, Crop crop) {
        Crop existingCrop = repo.findById(id)
                                .orElseThrow(() -> new RuntimeException("Crop not found with id: " + id));
        if (crop == null) {
            throw new RuntimeException("Crop data is invalid.");
        }
        existingCrop.setName(crop.getName());
        existingCrop.setDescription(crop.getDescription());
        
        return repo.save(existingCrop);
    }
    
    public String delete(Long id)
    {
    	Crop existingCrop = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id: " + id));
    	
    	repo.deleteById(id);
    	return "Successfully deleted";
    }

    // buyer
    public PredictorData updateCropPrice(Long buyerId, Long cropId, Double price,Integer year) {
        Buyer buyer = buyerRepo.findById(buyerId)
                               .orElseThrow(() -> new RuntimeException("Buyer not found with id: " + buyerId));
        
        Crop crop = repo.findById(cropId)
                        .orElseThrow(() -> new RuntimeException("Crop not found with id: " + cropId));

        if (price == null || price <= 0) {
            throw new RuntimeException("Price is invalid.");
        }

        PredictorData data = new PredictorData(price, year, buyer, crop);
        
        //Notification send when crop price updated.
        notificationService.notifySubscribers(buyerId,"Crop price has been updated "+buyer.getId());
        
        
        return predictorRepo.save(data);
    }

    public List<PredictorData> getAllPrices() {
        return predictorRepo.findAll();
    }
    
    

    public Set<PredictorData> getPredictorDataByCropId(Long cropId) {
        if (repo.existsById(cropId)) {
            return predictorRepo.findByCropId(cropId);
        } else {
            throw new RuntimeException("Crop not found with id: " + cropId);
        }
    }
    
    
    
    public Double getPredictedPrice(Long cropId,int year,String strategy)
    {
    	 Set<PredictorData> st = getPredictorDataByCropId(cropId);
    	 
    	return  predictorService.predictedPrice(st,year,strategy);
    }
}
