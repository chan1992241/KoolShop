package com.example.assignment.controller;

import com.example.assignment.utils.CustomerLoginValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Home_Product", value = "/Home_Product")
public class Home_Product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CustomerLoginValidator.isCustomerLogin(request)){
            request.setAttribute("login_status", "successful");
            request.getRequestDispatcher("product_home.jsp").forward(request, response);
        }else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
