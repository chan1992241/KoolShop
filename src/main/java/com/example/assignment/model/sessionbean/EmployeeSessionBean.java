package com.example.assignment.model.sessionbean;

import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.entity.Office;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanLocal{
    @PersistenceContext(unitName = "default")
    EntityManager em;
    @EJB
    private OfficeSessionBeanLocal officeBean;
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
    public boolean checkEmployeeNumberwithExtension(String employeeNumber, String extension) throws EJBException {
        Query query = em.createNativeQuery("select * from classicmodels.employees " +
                "where employeenumber = " +
                employeeNumber + " and extension = '" +
                extension + "';");
        int numberOfRow = query.getResultList().size();
        if (numberOfRow < 1){
            return false;
        }
        return true;
    }

    public List<Employee> getAllEmployees() throws EJBException {
        //Execute the query named "Employee.findAll" and return the results. The query is at Employee entity class
        Query q = em.createNativeQuery("select * from ecommerce.classicmodels.employees;", Employee.class);
        List<Employee> employees = q.getResultList();
        return employees;
    }

    public Employee findEmployee(String ID) throws EJBException{
        Query q = em.createNamedQuery("Employee.findbyId");
        q.setParameter("id", Integer.parseInt(ID));
        return (Employee) q.getSingleResult();
    }

    public void addEmployee(String[] s) throws EJBException{
        int empID = ((int)(Math.random()*(10000 - 2000))) + 2000;
        int officeCode = Integer.parseInt(s[5]);

        Query q = em.createNativeQuery("INSERT INTO classicmodels.employees (employeenumber, lastname, firstname, extension, email, officecode, reportsto, jobtitle) VALUES (" +
                empID + ", '" + s[1] + "', '" + s[2] + "', '" + s[3] + "', '" + s[4] + "', " + officeCode + ", '" + s[6] + "', '" + s[7] + "');");
        q.executeUpdate();
    }

    public void updateEmployee(String[] s) throws EJBException{
        Employee e = findEmployee(s[0]);
        e.setFirstname(s[1]);
        e.setLastname(s[2]);
        e.setExtension(s[3]);
        e.setEmail(s[4]);

        Office office = officeBean.findOffice(s[5]);
        e.setOfficecode(office);

        e.setReportsto(s[6]);
        e.setJobtitle(s[7]);

        em.merge(e);
    }

    public void deleteEmployee(String id) throws EJBException{
        Employee e = findEmployee(id);
        em.remove(e);
    }

    public int getNumberOfRows(String keyword) throws EJBException{
        Query q = null;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM ecommerce.classicmodels.employees");
        }
        else {
            q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from ecommerce.classicmodels.employees WHERE concat(employeenumber,lastname,firstname) LIKE ?");
            q.setParameter(1, "%" + keyword + "%");
        }
        BigInteger results = (BigInteger) q.getSingleResult();
        int i = results.intValue();
        return i;
    }

    public List<Employee> readEmployee(int currentPage, int recordsPerPage,String keyword) throws EJBException{
        Query q = null;
        int start = 0;
        //direction = " " + direction;
        if (keyword.isEmpty()) {
            q = em.createNativeQuery("SELECT * FROM ecommerce.classicmodels.employees order by employeenumber", Employee.class);
            start = currentPage * recordsPerPage - recordsPerPage;
        } else {
            q = em.createNativeQuery("SELECT * from ecommerce.classicmodels.employees WHERE concat(employeenumber,lastname,firstname) LIKE ? order by employeenumber",Employee.class);
            start = currentPage * recordsPerPage - recordsPerPage;
            q.setParameter(1, "%" + keyword + "%");
        }

        List<Employee> results = q.setFirstResult(start).setMaxResults(recordsPerPage).getResultList();
        return results;
    }
}
