package com.example.assignment.controller;

import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.entity.Office;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.model.sessionbean.OfficeSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OfficeController", value = "/OfficeController")
public class OfficeController extends HttpServlet {

    @EJB
    private OfficeSessionBeanLocal officeBean;
    @EJB
    private EmployeeSessionBeanLocal empbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (AdminLoginValidator.isAdminLogin(request)) {
            String id = request.getParameter("id");

            try{
                Office office = officeBean.findOffice(id);
                List<Employee> empList = empbean.findEmployeeByOffice(id);

                request.setAttribute("Office", office);
                request.setAttribute("EMPList", empList);

                if (request.getParameter("btn")!=null){
                    RequestDispatcher req = request.getRequestDispatcher("OfficeEmployee.jsp");
                    req.forward(request, response);
                }
                else {
                    RequestDispatcher req = request.getRequestDispatcher("OfficeUpdate.jsp");
                    req.forward(request, response);
                }

            }
            catch (Exception ex){
                RequestDispatcher req = request.getRequestDispatcher("OfficeDisplay.html");
                req.forward(request, response);
            }
        }
        else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Admin_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String officeID = request.getParameter("id");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String addressline1 = request.getParameter("addressline1");
        String addressline2 = request.getParameter("addressline2");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String postalcode = request.getParameter("postalcode");
        String territory = request.getParameter("territory");
        PrintWriter out = response.getWriter();

        String[] officeData = {officeID, city, phone, addressline1, addressline2, state, country, postalcode, territory};

        //try {
        if (request.getParameter("UPDATE")!=null&&request.getParameter("UPDATE").equals("UPDATE")) {
            officeBean.updateOffice(officeData);
        }
        else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("DELETE")) {
            try {
                officeBean.deleteOffice(officeID);
            }
            catch (EJBException ex){
                out.println("<SCRIPT type=\"text/javascript\">");
                out.println("alert(\"Office cannot be deleted because the Employees still exist\")");
                out.println("</SCRIPT>");
            }
        }
        else {
            officeBean.addOffice(officeData);
        }
        out.println("<SCRIPT type=\"text/javascript\">");
        out.println("alert(\"Record has been updated and url will be redirected\")");
        out.println("window.location.assign(\"OfficePagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC\")");
        out.println("</SCRIPT>");
        /*} catch (EJBException ex) {
        }*/
    }
}
