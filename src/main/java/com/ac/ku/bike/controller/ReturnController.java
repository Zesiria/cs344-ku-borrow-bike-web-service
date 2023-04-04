package com.ac.ku.bike.controller;

import com.ac.ku.bike.model.Bike;
import com.ac.ku.bike.model.User;
import com.ac.ku.bike.service.BikeService;
import com.ac.ku.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/return")
public class ReturnController {

    @Autowired
    UserService userService;

    @Autowired
    BikeService bikeService;

    @RequestMapping
    public Map<String, Object> returnBike(@RequestBody Map<String, String> body){
        User user = userService.getByUsername(body.get("username"));
        Bike bike = bikeService.getByToken(body.get("token"));

        Map<String, Object> data = new HashMap<String, Object>();
        if(bike != null && bike.getStatus().equals("Occupied") && user.getLend_bike() == bike) {
            bike.toggleStatus();
            bikeService.update(bike);
            user.setLend_bike(null);
            user.setLendTimeNull();
            userService.update(user);
            data.put("message", "Successfully return the bike.");
        }
        else{
            data.put("message", "Unable to return the bike.");
        }

        return data;
    }
}
