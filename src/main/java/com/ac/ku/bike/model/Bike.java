package com.ac.ku.bike.model;

import javax.persistence.*;

@Entity
public class Bike {

    @Id
    @GeneratedValue
    private int bike_id;

    @Column(unique = true)
    private String token;

    private String status = "Not Occupied";

    @OneToOne(mappedBy = "lend_bike")
    private User user = null;

    public void toggleStatus(){
        if(this.status.equals("Occupied"))
            this.status = "Not Occupied";
        else if(this.status.equals("Not Occupied"))
            this.status = "Occupied";
    }

    public int getBike_id() {
        return bike_id;
    }

    public String getToken() {
        return token;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
