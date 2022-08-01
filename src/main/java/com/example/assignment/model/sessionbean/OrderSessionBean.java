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
    public List<Order> findOrderByCustomerIDAndStatus(String customer_number, String status) throws EJBException {
        Query order_query = em.createQuery("select o from Order o where o.customernumber = " + customer_number + " and o.status = '"+ status +"'");
        List<Order> order = order_query.getResultList();
        return order;
    }
    @Override
    public List<Object[]> getOrderDetails(String orderID){
        Query order_detail_query = em.createQuery("select p, od From Orderdetail od, Product p \n" +
                "WHERE od.productcode = p.id\n" +
                "and od.ordernumber = " + orderID);
        List<Object[]> order_details = order_detail_query.getResultList();
        return order_details;
    }
}
