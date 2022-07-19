package com.example.assignment.controller;

import com.example.assignment.model.sessionbean.CustomerSessionBean;
import com.example.assignment.model.sessionbean.CustomerSessionBeanLocal;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Customer_Login", value = "/Customer_Login")
public class Customer_Login extends HttpServlet {
    @EJB
    private CustomerSessionBeanLocal customerSessionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("customer_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customer_number=request.getParameter("customer_number");
        String phone=request.getParameter("phone");
        boolean isCorrectAuthentication = customerSessionBean.checkCustomerNumberwithPhoneNumber(customer_number, phone);
        if(isCorrectAuthentication == true){
            HttpSession session=request.getSession();
            session.setAttribute("customer_number", customer_number);
//            if (!session.getAttribute("customer_number").equals("")){
//                request.setAttribute("login_status", "successful");
//                request.getRequestDispatcher("home.jsp").forward(request, response);
//            }
            if (CustomerLoginValidator.isCustomerLogin(request)){
                request.setAttribute("login_status", "successful");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        }
        else{
            request.setAttribute("login_status", "Wrong customer number or phone number");
            request.getRequestDispatcher("customer_login.jsp").include(request, response);
        }
    }
}
