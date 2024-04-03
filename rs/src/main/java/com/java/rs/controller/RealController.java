package com.java.rs.controller;

import com.java.rs.model.RealEstate;
import com.java.rs.service.RealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<RealEstate>> getAllReals() {
        List<RealEstate> reals = realService.getAllReals();
        if (!reals.isEmpty()) {
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
}
