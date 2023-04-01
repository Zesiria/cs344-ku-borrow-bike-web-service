package com.ac.ku.bike.controller;

import com.ac.ku.bike.model.Bike;
import com.ac.ku.bike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeService service;

    @RequestMapping
    public List<Bike> getAll(){
        return service.getAll();
    }

    @PostMapping
    public Bike create(@RequestBody Bike bike){
        return  service.create(bike);
    }
}
