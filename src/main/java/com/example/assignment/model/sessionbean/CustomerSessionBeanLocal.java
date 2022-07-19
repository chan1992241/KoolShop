package com.example.assignment.model.sessionbean;

import javax.ejb.EJBException;
import javax.ejb.Local;

@Local
public interface CustomerSessionBeanLocal {
    public boolean checkCustomerNumberwithPhoneNumber(String customerNumber, String phoneNUmber) throws EJBException;
}
