package com.example.assignment.model.sessionbean;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanLocal{
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public boolean checkStaffNumberwithEmail(String employeenumber, String email) throws EJBException {
        Query query = em.createNativeQuery("select * from ecommerce.classicmodels.employees where employeenumber = " + employeenumber +
                " and email = '"+ email+"';");
        int numberOfRow = query.getResultList().size();
        if (numberOfRow < 1){
            return false;
        }
        return true;
    }
}
