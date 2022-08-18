package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.entity.Product;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Stateless
public class ProductSessionBean implements ProductSessionBeanLocal {


    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public void addProduct(String[] s) throws EJBException {
        int productID = ((int)(Math.random()*(10000 - 2000))) + 2000;
        int officeCode = Integer.parseInt(s[5]);

        Query q = em.createNativeQuery("INSERT INTO classicmodels.products (productcode, productname, productline, productscale, productvendor, productdescription, quantityinstock, buyprice,msrp) VALUES (" +
                productID + ", '" + s[1] + "', '" + s[2] + "', '" + s[3] + "', '" + s[4] + "', " + officeCode + ", '" + s[6] + "', '" + s[7] + "', '" +s[8] + "');");
        q.executeUpdate();
    }

    @Override
    public List<Product> getAllProduct() throws EJBException {

        try {
            Query q = em.createNativeQuery("select * from ecommerce.classicmodels.products;" , Product.class);
            List<Product> products = q.getResultList();

            return products;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;

    }

    @Override
    public int getNumberOfRows(String keyword) throws EJBException {
        Query q = null;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM ecommerce.classicmodels.products");
        }
        else {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from ecommerce.classicmodels.products WHERE concat(productname,productdescription,quantityinstock,buyprice) LIKE ?");
            q.setParameter(1, "%" + keyword + "%");
        }
        BigInteger results = (BigInteger) q.getSingleResult();
        int i = results.intValue();
        return i;
    }

    @Override
    public Product findProduct(String ID) throws EJBException {
        Query q = em.createNamedQuery("product");
        q.setParameter("id", Integer.parseInt(ID));
        return (Product) q.getSingleResult();
    }


    @Override
    public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword, String direction) throws EJBException {
        Query q = null;
        int start = 0;
        //direction = " " + direction;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT * FROM ecommerce.classicmodels.products order by productname " + direction, Product.class);
            start = currentPage * recordsPerPage - recordsPerPage;
        } else {
            q = em.createNativeQuery("SELECT * from ecommerce.classicmodels.customers WHERE concat(productname,productdescription,buyprice) LIKE ? order by customernumber " + direction, Customer.class);
            start = currentPage * recordsPerPage - recordsPerPage;
            q.setParameter(1, "%" + keyword + "%");
        }

        List<Product> results = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
        return results;
    }
}


