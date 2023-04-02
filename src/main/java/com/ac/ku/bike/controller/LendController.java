package com.ac.ku.bike.controller;

import com.ac.ku.bike.model.Bike;
import com.ac.ku.bike.model.User;
import com.ac.ku.bike.service.BikeService;
import com.ac.ku.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/lend")
public class LendController {

    @Autowired
    BikeService bikeService;

    @Autowired
    UserService userService;

    @RequestMapping
    public Bike lend(@RequestBody Map<String, String> body){
        Bike bike = bikeService.getByToken(body.get("token"));
        if(bike != null){
            User user = userService.getByUsername(body.get("username"));
            user.setLend_bike(bike);
            bike.toggleStatus();
            userService.update(user);
            bikeService.update(bike);
        }
        return bike;
    }
}
