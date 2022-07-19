package com.example.assignment.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", schema = "classicmodels")
public class UserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private UserRoleId id;

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}