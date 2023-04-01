package com.ac.ku.bike.repository;

import com.ac.ku.bike.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BikeRepository extends JpaRepository<Bike, UUID> {
    Bike findBikeByToken(String token);
}
