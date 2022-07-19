package com.example.assignment.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails", schema = "classicmodels")
public class Orderdetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private OrderdetailId id;

    @MapsId("ordernumber")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordernumber", nullable = false)
    private Order ordernumber;

    @MapsId("productcode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productcode", nullable = false)
    private Product productcode;

    @Column(name = "quantityordered", nullable = false)
    private Integer quantityordered;

    @Column(name = "priceeach", nullable = false, precision = 5, scale = 2)
    private BigDecimal priceeach;

    @Column(name = "orderlinenumber", nullable = false)
    private Integer orderlinenumber;

    public OrderdetailId getId() {
        return id;
    }

    public void setId(OrderdetailId id) {
        this.id = id;
    }

    public Order getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Order ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Product getProductcode() {
        return productcode;
    }

    public void setProductcode(Product productcode) {
        this.productcode = productcode;
    }

    public Integer getQuantityordered() {
        return quantityordered;
    }

    public void setQuantityordered(Integer quantityordered) {
        this.quantityordered = quantityordered;
    }

    public BigDecimal getPriceeach() {
        return priceeach;
    }

    public void setPriceeach(BigDecimal priceeach) {
        this.priceeach = priceeach;
    }

    public Integer getOrderlinenumber() {
        return orderlinenumber;
    }

    public void setOrderlinenumber(Integer orderlinenumber) {
        this.orderlinenumber = orderlinenumber;
    }

}