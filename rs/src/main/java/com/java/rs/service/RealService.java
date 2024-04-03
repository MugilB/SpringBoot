package com.java.rs.service;

import com.java.rs.model.RealEstate;
import com.java.rs.repository.RealRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RealService {

    @Autowired
    private RealRepo RealEstateRepo;

    public RealEstate createReal(@NonNull RealEstate RealEstate) {
        return RealEstateRepo.save(RealEstate);
    }

    public List<RealEstate> getAllReals() {
        return RealEstateRepo.findAll();
    }

    public RealEstate getRealById(@NonNull Integer id) {
        return RealEstateRepo.findById(id).orElse(null);
    }

    public boolean updateReal(int id, RealEstate RealEstate) {
        if (getRealById(id) == null) {
            return false;
        }
        try {
            RealEstateRepo.save(RealEstate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteReal(int id) {
        if (getRealById(id) == null) {
            return false;
        }
        RealEstateRepo.deleteById(id);
        return true;
    }
}
