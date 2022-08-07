package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.entity.Orderdetail;
import com.example.assignment.model.entity.Product;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Override
    public List<Order> getOrderHistory(String customer_number, int currentPage, int recordsPerPage, String keyword, String direction){
        Query q = null;
        int start = 0;
        direction = " " + direction;
        try{
            if (keyword.isEmpty()) {
                q = em.createNativeQuery("SELECT * FROM ecommerce.classicmodels.orders WHERE status != 'wait' AND customernumber = " + customer_number + " order by ordernumber " + direction , Order.class);
            }else{
                q = em.createNativeQuery("SELECT * from ecommerce.classicmodels.orders WHERE status != 'wait' AND concat(ordernumber,orderdate,requireddate,shippeddate,status) LIKE '%" + keyword + "%' AND customernumber = " + customer_number + " order by ordernumber " + direction,Order.class);
//            q.setParameter(1, "%" + keyword + "%");
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        start = currentPage * recordsPerPage - recordsPerPage;
        List<Order> order = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
        return order;
    }
    @Override
    public List<Object[]> getOrderHistoryDetails(String customer_number,int currentPage, int recordsPerPage, String direction, String keyword){
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Object[]> order_details = null;
        try{
            if (keyword.isEmpty()){
                Query q = em.createQuery("select p, od, o From Orderdetail od, Product p, Order o " +
                        "WHERE od.productcode = p.id " +
                        "AND od.ordernumber = o.id " +
                        "AND status != 'wait' AND o.customernumber = " + customer_number +
                        " order by od.ordernumber " + direction +"");
                order_details = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
            }else{
                Query q = em.createQuery("select p, od, o From Orderdetail od, Product p, Order o " +
                        "WHERE od.productcode = p.id " +
                        "AND od.ordernumber = o.id " +
                        "AND o.customernumber = " + customer_number +
                        " AND status != 'wait' AND concat(o.id, o.orderdate, o.requireddate ,o.shippeddate,o.status) LIKE '%" + keyword + "%' " +
                        "order by od.ordernumber " + direction +"");
                order_details = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return order_details;
    }
    @Override
    public int getNumberOfRows(String customer_number ,String keyword) throws EJBException {
        Query q = null;
//        q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM ecommerce.classicmodels.orders WHERE customernumber = " + customer_number);
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM ecommerce.classicmodels.orders WHERE status != 'wait' AND customernumber = " + customer_number);
        } else {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from ecommerce.classicmodels.orders WHERE status != 'wait' AND customernumber = " + customer_number + " AND concat(ordernumber,orderdate,requireddate,shippeddate,status) LIKE '%" + keyword + "%'");
//            q.setParameter(1, "%" + keyword + "%");
        }
        BigInteger results = (BigInteger) q.getSingleResult();
        int i = results.intValue();
        return i;
    }
    @Override
    public void addOrder(String customer_number){
        String orderID;
        try {
            int newOrderID = getLargestID() + 1;
            String query = "INSERT INTO ecommerce.classicmodels.orders (ordernumber ,orderdate, requireddate, shippeddate, status, comments, customernumber) \n" +
            "VALUES ("+newOrderID+" ,'empty', 'empty', 'empty', 'wait', '', "+customer_number+");";
            em.createNativeQuery(query).executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public int getLargestID(){
        int i = 0;
        try{
            Query q = em.createNativeQuery("SELECT max(ordernumber) FROM ecommerce.classicmodels.orders;");
            Integer results = (Integer) q.getSingleResult();
            i = results;
        }catch (Exception e){
            System.out.println(e);
        }
        return i;
    };
    @Override
    public void updateOrder(String ordernumber, String orderdate, String requireddate, String shippeddate, String status, String comments, int customernumber ){
        try{
            Order order = (Order) findOrder(ordernumber);
            order.setOrderdate(orderdate);
            order.setRequireddate(requireddate);
            order.setShippeddate(shippeddate);
            order.setStatus(status);
            order.setComments(comments);
            em.merge(order);
        }catch (Exception e){
            System.out.println(e);
        }
    };
    @Override
    public Order findOrder(String orderID){
        Query q = null;
        try{
            q = em.createQuery("SELECT o FROM Order o WHERE ordernumber = " + orderID);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return (Order) q.getSingleResult();
    }

    public void updateProductOrderQuantity(String productcode, String ordernumber, int newQuantity){
        try{
            Query q = em.createQuery("update Orderdetail SET quantityordered = " + newQuantity + " WHERE ordernumber = "+ ordernumber +" " +
                    "AND productcode = '"+ productcode+"'");
            q.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    public void deleteProductFromOrder(String productcode, String ordernumber){
        try{
            Query q = em.createQuery("SELECT od from Orderdetail od" +
                    " where od.ordernumber = "+ ordernumber +" and od.productcode = '"+ productcode+"'");
            Orderdetail od = (Orderdetail) q.getSingleResult();
            em.remove(od);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void addProductToOrder(String productcode, String ordernumber, String quantityOrder ){
        try{
            Query productQ = em.createQuery("SELECT p from Product p where p.id = " + productcode);
            Product product = (Product) productQ.getSingleResult();
            Query q = em.createNativeQuery("INSERT INTO classicmodels.orderdetails (ordernumber, productcode, quantityordered, priceeach, orderlinenumber) VALUES\n" +
                    "("+ordernumber+", '"+productcode+"', "+ quantityOrder +", "+ product.getBuyprice()+", 2);");
            q.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
