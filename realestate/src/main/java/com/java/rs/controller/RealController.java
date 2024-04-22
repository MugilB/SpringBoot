package com.java.rs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.rs.model.RealEstate;
import com.java.rs.service.RealService;

@RestController
@RequestMapping("/api")
public class RealController {
    
    @Autowired
    private RealService realService;

    @PostMapping("/real")
    public ResponseEntity<RealEstate> add(@RequestBody RealEstate real) {
        RealEstate newReal = realService.createReal(real);
        if (newReal != null) {
            return new ResponseEntity<>(newReal, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reals")
    public ResponseEntity<Page<RealEstate>> getAllReals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<RealEstate> reals = realService.getAllReals(pageable);
        if (reals.hasContent()) {
            return new ResponseEntity<>(reals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/reals/sorted")
    public ResponseEntity<Page<RealEstate>> getAllRealsSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size);

        Page<RealEstate> reals = realService.getAllRealsSorted(sortBy, pageable);
        if (reals.hasContent()) {
            return new ResponseEntity<>(reals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/real/{realId}")
    public ResponseEntity<RealEstate> updateReal(@PathVariable int realId, @RequestBody RealEstate real) {
        boolean updated = realService.updateReal(realId, real);
        if (updated) {
            return new ResponseEntity<>(real, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/real/{realId}")
    public ResponseEntity<Boolean> deleteReal(@PathVariable int realId) {
        boolean deleted = realService.deleteReal(realId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    //new
    @PostMapping("/realestate")
    public ResponseEntity<RealEstate> addRealEstate(@RequestBody RealEstate realEstate) {
        RealEstate newRealEstate = realService.createRealEstate(realEstate);
        if (newRealEstate != null) {
            return new ResponseEntity<>(newRealEstate, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/realestates")
    public ResponseEntity<List<RealEstate>> getAllRealEstates() {
        List<RealEstate> realEstates = realService.getAllRealEstates();
        if (!realEstates.isEmpty()) {
            return new ResponseEntity<>(realEstates, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}
