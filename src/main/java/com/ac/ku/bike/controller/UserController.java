package com.ac.ku.bike.controller;

import com.ac.ku.bike.model.User;
import com.ac.ku.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginUser){
        User user = service.login(loginUser.getUsername(), loginUser.getPassword());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", HttpStatus.OK);
        if(user != null) {
            map.put("message", "Successfully logged in");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());
            data.put("token", user.getToken());

            map.put("data", data);
        }
        else {
            map.put("message", "Fail to logged in");
            map.put("status", HttpStatus.OK);
        }

        return map;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User newUser){
        User user = service.register(newUser.getUsername(), newUser.getPassword(), newUser.getEmail());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", HttpStatus.OK);
        if(user != null) {
            map.put("message", "Successfully registered");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());

            map.put("data", data);
        }
        else {
            map.put("message", "Fail to registered");
            map.put("status", HttpStatus.OK);
        }

        return map;
    }

    @PostMapping("/login/token")
    public Map<String, Object> loginWithToken(@RequestBody Map<String, String> body){
        User user = service.loginWithToken(body.get("token"));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", HttpStatus.OK);
        if(user != null) {
            map.put("message", "Successfully logged in");

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());

            map.put("data", data);
        }
        else {
            map.put("message", "Fail to logged in");
            map.put("status", HttpStatus.OK);
        }

        return map;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody Map<String, String> body){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = service.logout(body.get("username"));
        if(body.get("username") == null){
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("message", "No username provided.");
        }

        else if(user == null){
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("message", "This username doesn't exist.");
        }
        else{
            map.put("status", HttpStatus.OK);
            map.put("message", "Successfully logout.");
        }
        return map;
    }
}
