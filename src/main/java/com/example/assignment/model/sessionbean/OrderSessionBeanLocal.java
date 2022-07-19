package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Order;

import javax.ejb.EJBException;
import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderSessionBeanLocal {
    public List<Order> getAllOrder() throws EJBException;
    public List<Object[]> findOrderByCustomerID(String id) throws EJBException;

}
