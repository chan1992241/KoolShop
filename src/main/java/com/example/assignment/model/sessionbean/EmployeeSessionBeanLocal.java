package com.example.assignment.model.sessionbean;

import javax.ejb.Local;

@Local
public interface EmployeeSessionBeanLocal {
    public boolean checkStaffNumberwithEmail(String employeenumber, String email);
}
