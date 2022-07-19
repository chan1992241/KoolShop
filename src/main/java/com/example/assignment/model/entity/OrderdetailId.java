package com.example.assignment.model.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderdetailId implements Serializable {
    private static final long serialVersionUID = -2995600057429039172L;
    @Column(name = "ordernumber", nullable = false)
    private Integer ordernumber;

    @Column(name = "productcode", nullable = false, length = 9)
    private String productcode;

    public Integer getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Integer ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderdetailId entity = (OrderdetailId) o;
        return Objects.equals(this.productcode, entity.productcode) &&
                Objects.equals(this.ordernumber, entity.ordernumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productcode, ordernumber);
    }

}