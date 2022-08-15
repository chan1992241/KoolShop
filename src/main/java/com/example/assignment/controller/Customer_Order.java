package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.entity.Orderdetail;
import com.example.assignment.model.entity.Product;
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
import java.util.ArrayList;
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
        String action = request.getParameter("action");
        String orderID = (String) request.getParameter("orderID");
        String productCodeDelete = request.getParameter("deleteProduct");
        if (productCodeDelete != null){
            orderbean.deleteProductFromOrder(productCodeDelete, orderID);
            doGet(request, response);
        }
        if (action.equals("update")){
            List<Object[]> order_details = orderbean.getOrderDetails(orderID);
            for (Object[] order_detail: order_details){
                Product product = (Product) order_detail[0];
                String newQuantity = request.getParameter(product.getId());
                orderbean.updateProductOrderQuantity(product.getId(), orderID, Integer.parseInt(newQuantity));
            }
            doGet(request, response);
        } else {
            // Pay
            String total_amount = request.getParameter("total_amount");
            Order order = orderbean.findOrder(orderID);
            orderbean.updateOrder(order.getId().toString(),
                    order.getOrderdate(),
                    order.getRequireddate(),
                    order.getShippeddate(),
                    "In Process",
                    order.getComments(),
                    order.getCustomernumber());
            request.setAttribute("login_status", "unsuccessful");
            request.setAttribute("total_amount", total_amount);
            request.getRequestDispatcher("client.jsp").forward(request,response);
//            doGet(request, response);
        }
    }
}
