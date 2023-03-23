package com.ac.ku.bike.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Bike {

    @Id
    @GeneratedValue
    private UUID ID;

    private String model;
    private String status;
    private String remainingTime;
    private double longitude;
    private double latitude;
}
