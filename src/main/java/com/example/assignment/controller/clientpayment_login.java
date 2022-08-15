package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJBException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "clientpayment_login", value = "/clientpayment_login")
public class clientpayment_login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CustomerLoginValidator.isCustomerLogin(request)){
            //Get all parameter
            HttpSession session=request.getSession();

            request.setAttribute("login_status", "successful");
            request.getRequestDispatcher("client.jsp").forward(request, response);
        }else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
