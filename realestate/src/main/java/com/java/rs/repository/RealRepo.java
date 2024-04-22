package com.java.rs.repository;

import com.java.rs.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealRepo extends JpaRepository<RealEstate, Integer> {
}