package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Office;

import javax.ejb.EJBException;
import javax.ejb.Local;
import java.util.List;

@Local
public interface OfficeSessionBeanLocal {
    public List<Office> getAllOffices() throws EJBException;
    public Office findOffice(String ID) throws EJBException;
    public void addOffice(String[] s) throws EJBException;
    public void updateOffice(String[] s) throws EJBException;
    public void deleteOffice(String id) throws EJBException;
    public int getNumberOfRows(String keyword) throws EJBException;
    public List<Office> readOffice(int currentPage, int recordsPerPage,String keyword,String direction) throws EJBException;
}
