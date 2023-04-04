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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lend")
public class LendController {

    @Autowired
    BikeService bikeService;

    @Autowired
    UserService userService;

    @RequestMapping
    public Map<String, Object> lend(@RequestBody Map<String, String> body){
        Bike bike = bikeService.getByToken(body.get("token"));
        Map<String, Object> data = new HashMap<String, Object>();
        if(bike != null && bike.getStatus().equals("Not Occupied")){
            User user = userService.getByUsername(body.get("username"));
            bike.toggleStatus();
            user.setLend_bike(bike);
            user.setLendTimeNow();
            userService.update(user);
            bikeService.update(bike);
            data.put("message", "Successfully lend bike.");
            data.put("bike", bike);
            data.put("user", user);
        }
        else{
            data.put("message","This bike is already occupied.");
        }
        return data;
    }
}
