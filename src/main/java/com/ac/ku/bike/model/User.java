package com.ac.ku.bike.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String password;
    private String email;
    private String name;
    private String profilePath;
}
