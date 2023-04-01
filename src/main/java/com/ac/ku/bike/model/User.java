package com.ac.ku.bike.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    @Column
    private String password;
    private String email;
    private String token = null;
    private String lendDateTime = null;

    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private Bike lend_bike = null;

    public User(){}
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Nullable
    public Bike getLend_bike() {
        return lend_bike;
    }

    public void setLend_bike(@Nullable Bike lend_bike) {
        this.lend_bike = lend_bike;
    }

    public boolean verifiedPassword(String password){
        return password.equals(this.password);
    }

    public boolean verifiedToken(String token){
        return token.equals(this.token);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLendDateTime() {
        return lendDateTime;
    }

    public void setLendTime(String lendDateTime) {
        this.lendDateTime = lendDateTime;
    }
}
