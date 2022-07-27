package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.sessionbean.OrderSessionBeanLocal;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    @EJB
    private OrderSessionBeanLocal orderbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Order> allOrder = orderbean.getAllOrder();
//        request.setAttribute("login_status", allOrder.get(0));
        if (CustomerLoginValidator.isCustomerLogin(request)){
            request.setAttribute("login_status", "successful");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
