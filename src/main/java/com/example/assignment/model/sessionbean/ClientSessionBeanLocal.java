package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Customer;


import javax.ejb.EJBException;
import javax.ejb.Local;
import java.util.List;
@Local
public interface ClientSessionBeanLocal {
    public List<Customer> getAllCustomer() throws EJBException;
    public List<Customer> readCustomer(int currentPage, int recordsPerPage,String keyword,String direction) throws EJBException;
    public int getNumberOfRows(String keyword) throws EJBException;
    public Customer findCustomer(String ID) throws EJBException;
    public void updateCustomer(String[] s) throws EJBException;
    public void deleteCustomer(String id) throws EJBException;



}
