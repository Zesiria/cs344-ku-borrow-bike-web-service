package com.ac.ku.bike.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ServiceLocation {
    @Id
    @GeneratedValue
    private UUID Id;

    private String name;
    private double longitude;
    private double latitude;
}
