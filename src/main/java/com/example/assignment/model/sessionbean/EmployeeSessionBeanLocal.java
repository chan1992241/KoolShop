package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Employee;

import javax.ejb.EJBException;
import javax.ejb.Local;
import java.util.List;

@Local
public interface EmployeeSessionBeanLocal {
    public boolean checkStaffNumberwithEmail(String employeenumber, String email);
    public boolean checkEmployeeNumberwithExtension(String employeeNumber, String extension) throws EJBException;
    public List<Employee> getAllEmployees() throws EJBException;
    public Employee findEmployee(String ID) throws EJBException;
    public void addEmployee(String[] s) throws EJBException;
    public void updateEmployee(String[] s) throws EJBException;
    public void deleteEmployee(String id) throws EJBException;
    public int getNumberOfRows(String keyword) throws EJBException;
    public List<Employee> readEmployee(int currentPage, int recordsPerPage,String keyword) throws EJBException;
}
