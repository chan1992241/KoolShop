package com.example.assignment.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "customers", schema = "classicmodels")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customernumber", nullable = false)
    private Integer id;

    @Column(name = "customername", nullable = false, length = 34)
    private String customername;

    @Column(name = "contactlastname", nullable = false, length = 15)
    private String contactlastname;

    @Column(name = "contactfirstname", nullable = false, length = 11)
    private String contactfirstname;

    @Column(name = "phone", nullable = false, length = 18)
    private String phone;

    @Column(name = "addressline1", nullable = false, length = 32)
    private String addressline1;

    @Column(name = "addressline2", length = 24)
    private String addressline2;

    @Column(name = "city", nullable = false, length = 17)
    private String city;

    @Column(name = "state", length = 13)
    private String state;

    @Column(name = "postalcode", length = 9)
    private String postalcode;

    @Column(name = "country", nullable = false, length = 12)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesrepemployeenumber")
    private Employee salesrepemployeenumber;

    @Column(name = "creditlimit", precision = 8, scale = 2)
    private BigDecimal creditlimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getContactlastname() {
        return contactlastname;
    }

    public void setContactlastname(String contactlastname) {
        this.contactlastname = contactlastname;
    }

    public String getContactfirstname() {
        return contactfirstname;
    }

    public void setContactfirstname(String contactfirstname) {
        this.contactfirstname = contactfirstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Employee getSalesrepemployeenumber() {
        return salesrepemployeenumber;
    }

    public void setSalesrepemployeenumber(Employee salesrepemployeenumber) {
        this.salesrepemployeenumber = salesrepemployeenumber;
    }

    public BigDecimal getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

}