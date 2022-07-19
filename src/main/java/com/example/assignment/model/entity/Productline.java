package com.example.assignment.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "productlines", schema = "classicmodels")
public class Productline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productline", nullable = false, length = 16)
    private String id;

    @Column(name = "textdescription", length = 735)
    private String textdescription;

    @Column(name = "htmldescription", length = 1)
    private String htmldescription;

    @Column(name = "image", length = 10)
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextdescription() {
        return textdescription;
    }

    public void setTextdescription(String textdescription) {
        this.textdescription = textdescription;
    }

    public String getHtmldescription() {
        return htmldescription;
    }

    public void setHtmldescription(String htmldescription) {
        this.htmldescription = htmldescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}