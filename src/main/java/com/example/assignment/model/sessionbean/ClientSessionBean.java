package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.entity.Office;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
@Stateless
public class ClientSessionBean implements ClientSessionBeanLocal{
    @PersistenceContext(unitName = "default")
    EntityManager em;
    @Override
    public List<Customer> getAllCustomer() throws EJBException {
        try {
            Query q = em.createNativeQuery("select * from classicmodels.customers;" , Customer.class);
            List<Customer> customer = q.getResultList();
            return customer;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Customer> readCustomer(int currentPage, int recordsPerPage, String keyword, String direction) throws EJBException {
        Query q = null;
        int start = 0;
        //direction = " " + direction;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT * FROM classicmodels.customers order by customername " + direction, Customer.class);
            start = currentPage * recordsPerPage - recordsPerPage;
        } else {
            q = em.createNativeQuery("SELECT * from classicmodels.customers WHERE concat(customername,contactlastname,contactfirstname) LIKE ? order by customernumber " + direction, Customer.class);
            start = currentPage * recordsPerPage - recordsPerPage;
            q.setParameter(1, "%" + keyword + "%");
        }

        List<Customer> results = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
        return results;
    }


    @Override
    public int getNumberOfRows(String keyword) throws EJBException {
        Query q = null;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.customers");
        }
        else {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.customers WHERE concat(customernumber,contactlastname,contactfirstname) LIKE ?");
            q.setParameter(1, "%" + keyword + "%");
        }
        BigInteger results = (BigInteger) q.getSingleResult();
        int i = results.intValue();
        return i;
    }

    @Override
    public Customer findCustomer(String ID) throws EJBException {
        Query q = em.createNamedQuery("Customer.findbyId");
        q.setParameter("id", Integer.parseInt(ID));
        return (Customer) q.getSingleResult();
    }

    @Override
    public void updateCustomer(String[] s) throws EJBException {
        Customer c = findCustomer(s[0]);
        c.setCustomername(s[1]);
        c.setPhone(s[2]);
        c.setAddressline1(s[3]);
        c.setAddressline2(s[4]);
        c.setCity(s[5]);
        c.setState(s[6]);
       c.setCountry(s[7]);

        em.merge(c);
    }

    @Override
    public void deleteCustomer(String id) throws EJBException {
        Customer c = findCustomer(id);
        em.remove(c);
    }


}
