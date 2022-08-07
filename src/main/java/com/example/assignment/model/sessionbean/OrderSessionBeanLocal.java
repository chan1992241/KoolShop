package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Order;

import javax.ejb.EJBException;
import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderSessionBeanLocal {
    public List<Order> getAllOrder() throws EJBException;
    public List<Order> findOrderByCustomerIDAndStatus(String id, String status) throws EJBException;
    public List<Object[]> getOrderDetails(String orderID) throws EJBException;
    public List<Order> getOrderHistory(String customer_number, int currentPage, int recordsPerPage, String keyword, String direction);
    public List<Object[]> getOrderHistoryDetails(String customer_number, int currentPage, int recordsPerPage, String direction, String keyword);
    public int getNumberOfRows(String customer_number ,String keyword) throws EJBException;
    public void addOrder(String customer_number);
    public void updateOrder(String orderID);
    public Order findOrder(String orderID);
    public int getLargestID();
}
