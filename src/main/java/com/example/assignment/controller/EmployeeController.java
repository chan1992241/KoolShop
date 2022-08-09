package com.example.assignment.controller;

import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.entity.Office;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.model.sessionbean.OfficeSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EmployeeController", value = "/EmployeeController")
public class EmployeeController extends HttpServlet {

    @EJB
    private EmployeeSessionBeanLocal empbean;
    @EJB
    private OfficeSessionBeanLocal officeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        /*try{*/
        if (AdminLoginValidator.isAdminLogin(request)) {
            Employee emp = empbean.findEmployee(id);
            List<Employee> empList = empbean.getAllEmployees();

            //If have problem, remove code below
            //Office empOffice = officeBean.findOffice(String.valueOf(emp.getOfficecode().getId()));
            List<Office> officeList = officeBean.getAllOffices();

            request.setAttribute("EMP", emp);
            request.setAttribute("EMPList", empList);
            //request.setAttribute("EmpOffice", empOffice);
            request.setAttribute("OfficeList", officeList);

            RequestDispatcher req = request.getRequestDispatcher("EmployeeUpdate.jsp");
            req.forward(request, response);
        }
        else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Admin_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("id");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String extension = request.getParameter("extension");
        String email = request.getParameter("email");
        String officeCode = request.getParameter("officeCode");
        String reportsTo = request.getParameter("reportsTo");
        String jobTitle = request.getParameter("jobTitle");
        PrintWriter out = response.getWriter();

        String[] empData = {eid, lname, fname, extension, email, officeCode, reportsTo, jobTitle};

        /*try {*/
            if (request.getParameter("UPDATE")!=null&&request.getParameter("UPDATE").equals("UPDATE")) {
                empbean.updateEmployee(empData);
            }
            else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("DELETE")) {
                empbean.deleteEmployee(eid);
            }
            else {
                empbean.addEmployee(empData);
            }
            out.println("<SCRIPT type=\"text/javascript\">");
            out.println("alert(\"Record has been updated and url will be redirected\")");
            out.println("window.location.assign(\"EmployeePagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC\")");
            out.println("</SCRIPT>");
        /*} catch (EJBException ex) {
        }*/
    }
}
