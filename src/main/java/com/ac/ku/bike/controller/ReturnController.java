package com.ac.ku.bike.controller;

import com.ac.ku.bike.model.Bike;
import com.ac.ku.bike.model.User;
import com.ac.ku.bike.service.BikeService;
import com.ac.ku.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
public class ReturnController {

    @Autowired
    UserService userService;

    @Autowired
    BikeService bikeService;

    @RequestMapping
    public User returnBike(@RequestBody String username){
        User user = userService.getByUsername(username);
        Bike bike = user.getLend_bike();
        if(bike != null && bike.getStatus().equals("Occupied"))
            bike.toggleStatus();
        user.setLend_bike(null);

        return user;
    }
}
