package com.example.assignment.model.sessionbean;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerSessionBean implements CustomerSessionBeanLocal{
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public boolean checkCustomerNumberwithPhoneNumber(String customerNumber, String phoneNumber) throws EJBException {
        Query query = em.createNativeQuery("select * from classicmodels.customers " +
                "where customernumber = " +
                customerNumber + " and phone = '" +
                phoneNumber + "';");
        int numberOfRow = query.getResultList().size();
        if (numberOfRow < 1){
            return false;
        }
        return true;
    }
}
