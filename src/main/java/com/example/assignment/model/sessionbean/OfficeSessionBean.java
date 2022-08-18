package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Office;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Stateless
public class OfficeSessionBean implements OfficeSessionBeanLocal{
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Office> getAllOffices() throws EJBException{
        Query q = em.createNativeQuery("select * from classicmodels.offices;", Office.class);
        List<Office> offices = q.getResultList();
        return offices;
    }

    public Office findOffice(String ID) throws EJBException{
        Query q = em.createNamedQuery("Office.findbyId");

        q.setParameter("id", Integer.parseInt(ID));
        return (Office) q.getSingleResult();
    }

    public void addOffice(String[] s) throws EJBException{

        int officeCode = ((int)(Math.random()*(10000 - 1000))) + 1000;

        Query q = em.createNativeQuery("INSERT INTO classicmodels.offices (officecode, city, phone, addressline1, addressline2, state, country, postalcode, territory) VALUES (" +
                officeCode + ", '" + s[1] + "', '" + s[2] + "', '" + s[3] + "', '" + s[4] + "', '" + s[5] + "', '" + s[6] + "', '" + s[7] + "', '" + s[8] + "');");
        q.executeUpdate();
    }

    public void updateOffice(String[] s) throws EJBException{
        Office o = findOffice(s[0]);
        o.setCity(s[1]);
        o.setPhone(s[2]);
        o.setAddressline1(s[3]);
        o.setAddressline2(s[4]);
        o.setState(s[5]);
        o.setCountry(s[6]);
        o.setPostalcode(s[7]);
        o.setTerritory(s[8]);

        em.merge(o);
    }

    public void deleteOffice(String id) throws EJBException{
        Office o = findOffice(id);
        em.remove(o);
    }

    public int getNumberOfRows(String keyword) throws EJBException{
        Query q = null;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.offices");
        }
        else {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.offices WHERE concat(officecode,city,country) LIKE ?");
            q.setParameter(1, "%" + keyword + "%");
        }
        BigInteger results = (BigInteger) q.getSingleResult();
        int i = results.intValue();
        return i;
    }

    public List<Office> readOffice(int currentPage, int recordsPerPage,String keyword,String direction) throws EJBException{
        Query q = null;
        int start = 0;
        //direction = " " + direction;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT * FROM classicmodels.offices order by officecode " + direction, Office.class);
            start = currentPage * recordsPerPage - recordsPerPage;
        } else {
            q = em.createNativeQuery("SELECT * from classicmodels.offices WHERE concat(officecode,city,country) LIKE ? order by officecode " + direction, Office.class);
            start = currentPage * recordsPerPage - recordsPerPage;
            q.setParameter(1, "%" + keyword + "%");
        }

        List<Office> results = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
        return results;
    }
}
