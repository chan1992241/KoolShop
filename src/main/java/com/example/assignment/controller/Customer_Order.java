package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.sessionbean.OrderSessionBeanLocal;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Customer_Order", value = "/Customer_Order")
public class Customer_Order extends HttpServlet {
    @EJB
    private OrderSessionBeanLocal orderbean;
    @PersistenceContext(unitName = "default")
    EntityManager em;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CustomerLoginValidator.isCustomerLogin(request)){
            HttpSession session=request.getSession();
            String customer_number = (String) session.getAttribute("customer_number");
            List<Order> order = orderbean.findOrderByCustomerIDAndStatus(customer_number, "wait");
            if (order.size() == 0){
                orderbean.addOrder(customer_number);
                order = orderbean.findOrderByCustomerIDAndStatus(customer_number, "wait");
            }
            String orderID = order.get(0).getId().toString();
            List<Object[]> order_details = orderbean.getOrderDetails(orderID);
            request.setAttribute("order", order);
            request.setAttribute("order_details", order_details);
            request.setAttribute("login_status", "successful");
            request.getRequestDispatcher("customer_order.jsp").forward(request, response);
        }else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
