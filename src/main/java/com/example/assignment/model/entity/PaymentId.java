package com.example.assignment.model.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PaymentId implements Serializable {
    private static final long serialVersionUID = 8966860875954803542L;
    @Column(name = "customernumber", nullable = false)
    private Integer customernumber;

    @Column(name = "checknumber", nullable = false, length = 11)
    private String checknumber;

    public Integer getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(Integer customernumber) {
        this.customernumber = customernumber;
    }

    public String getChecknumber() {
        return checknumber;
    }

    public void setChecknumber(String checknumber) {
        this.checknumber = checknumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentId entity = (PaymentId) o;
        return Objects.equals(this.customernumber, entity.customernumber) &&
                Objects.equals(this.checknumber, entity.checknumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customernumber, checknumber);
    }

}