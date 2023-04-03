package com.ac.ku.bike.service;

import com.ac.ku.bike.model.User;
import com.ac.ku.bike.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public User getByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public User login(String username, String password){
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            if(user.verifiedPassword(password)) {
                String generatedString = RandomStringUtils.random(64, true, true);
                user.setToken(generatedString);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    public User loginWithToken(String token) {
        return userRepository.findUserByToken(token);
    }

    public Map<String, Object> register(String username, String password, String email) {
        User checkIfExist = userRepository.findUserByUsername(username);
        if(checkIfExist != null){
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Username already exists.");
            return data;
        }
        User user = new User(username, password, email);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("message", "Username already exists.");
        data.put("user", create(user));
        return data;
    }

    public User logout(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user != null){
            user.setToken(null);
            return userRepository.save(user);
        }
        return null;
    }
}
