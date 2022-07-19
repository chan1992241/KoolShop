package com.example.assignment.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "classicmodels")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username", nullable = false, length = 7)
    private String id;

    @Column(name = "password", nullable = false, length = 7)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}