package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.entity.Product;

import javax.ejb.EJBException;
import java.util.List;

public interface ProductSessionBeanLocal {
    public void addProduct(String[] s) throws EJBException;
    public List<Product> getAllProduct() throws EJBException;
    public int getNumberOfRows(String keyword) throws EJBException;
    public Product findProduct(String ID) throws EJBException;
    public List<Product> readProduct(int currentPage, int recordsPerPage,String keyword,String direction) throws EJBException;
}
