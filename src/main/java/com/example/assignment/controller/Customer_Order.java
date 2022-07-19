package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.sessionbean.OrderSessionBeanLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Customer_Order", value = "/Customer_Order")
public class Customer_Order extends HttpServlet {
    @EJB
    private OrderSessionBeanLocal orderbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("customer_id");
        String id = "363";
        List<Object[]> customer_order = orderbean.findOrderByCustomerID(id);
        request.setAttribute("customer_order", customer_order);
        request.getRequestDispatcher("customer_order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
