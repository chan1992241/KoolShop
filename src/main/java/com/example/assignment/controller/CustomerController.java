package com.example.assignment.controller;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.sessionbean.ClientSessionBeanLocal;
import com.example.assignment.model.sessionbean.CustomerSessionBeanLocal;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "CustomerController", value = "/CustomerController")
public class CustomerController extends HttpServlet {
    @EJB
    private ClientSessionBeanLocal cusbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (AdminLoginValidator.isAdminLogin(request)) {
            Customer cus = cusbean.findCustomer(id);
            List<Customer> cusList = cusbean.getAllCustomer();
            request.setAttribute("cus", cus);
            request.setAttribute("cusList", cusList);

            RequestDispatcher req = request.getRequestDispatcher("CustomerUpdate.jsp");
            req.forward(request, response);
        }
        else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Admin_Login").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postal = request.getParameter("postal");
        String country = request.getParameter("country");
        PrintWriter out = response.getWriter();

        String[] cusData = {cid,name,phone,address1,address2,city,state,postal,country};
        if (request.getParameter("UPDATE")!=null&&request.getParameter("UPDATE").equals("UPDATE")) {
            cusbean.updateCustomer(cusData);
        }
        else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("DELETE")) {
            cusbean.deleteCustomer(cid);
        }

        out.println("<SCRIPT type=\"text/javascript\">");
        out.println("alert(\"Record has been updated and url will be redirected\")");
        out.println("window.location.assign(\"CustomerPagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC\")");
        out.println("</SCRIPT>");


    }
}
