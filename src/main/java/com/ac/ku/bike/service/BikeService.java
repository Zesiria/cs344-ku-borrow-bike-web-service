package com.ac.ku.bike.service;

import com.ac.ku.bike.model.Bike;
import com.ac.ku.bike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike create(Bike bike){
        return bikeRepository.save(bike);
    }
}
