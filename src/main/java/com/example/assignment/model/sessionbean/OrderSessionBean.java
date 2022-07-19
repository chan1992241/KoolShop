package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Order;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class OrderSessionBean implements OrderSessionBeanLocal{
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Order> getAllOrder() throws EJBException {
        Query q = em.createNativeQuery("select * from ecommerce.classicmodels.orders;", Order.class);
        List<Order> results = q.getResultList();
        return results;
    }

    @Override
    public List<Object[]> findOrderByCustomerID(String id) throws EJBException {
        Query q = em.createNativeQuery("select * from ecommerce.classicmodels.orders where customernumber = "+ id +" ;");
        List<Object[]> results = q.getResultList();
        return results;
    }
}
