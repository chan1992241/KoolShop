package com.example.assignment.controller;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Product;
import com.example.assignment.model.sessionbean.ClientSessionBeanLocal;
import com.example.assignment.model.sessionbean.CustomerSessionBeanLocal;
import com.example.assignment.model.sessionbean.ProductSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    @EJB
    private ProductSessionBeanLocal productbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (CustomerLoginValidator.isCustomerLogin(request)) {
            Product pro = productbean.findProduct(id);
            List<Product> productList = productbean.getAllProduct();
            request.setAttribute("pro", pro);
            request.setAttribute("proList", productList);

            RequestDispatcher req = request.getRequestDispatcher("customer_order.jsp");
            req.forward(request, response);
        }
        else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
