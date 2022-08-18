package com.example.assignment.controller;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Product;
import com.example.assignment.model.sessionbean.ClientSessionBeanLocal;
import com.example.assignment.model.sessionbean.ProductSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductPagination", value = "/ProductPagination")


public class ProductPagination extends HttpServlet {

    @EJB
    private ProductSessionBeanLocal productbean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CustomerLoginValidator.isCustomerLogin(request)) {
            int nOfPages = 0;
            int currentPage = Integer.valueOf(request.getParameter("currentPage"));
            int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
            String keyword = request.getParameter("keyword");
            String direction = request.getParameter("direction");

            List<Product> cusList = productbean.getAllProduct();

            int rows = productbean.getNumberOfRows(keyword);
            nOfPages = rows / recordsPerPage;
            if (rows % recordsPerPage != 0) {
                nOfPages++;
            }

            if (currentPage > nOfPages && nOfPages != 0) {
                currentPage = nOfPages;
            }

            List<Product> productList = productbean.getAllProduct();
            List<Product> lists = productbean.readProduct(currentPage, recordsPerPage, keyword, direction);
            request.setAttribute("ProductPerPage", lists);
            request.setAttribute("nOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("keyword", keyword);
            request.setAttribute("direction", direction);
            request.setAttribute("productList", productList);
            request.setAttribute("login_status", "successful");
            RequestDispatcher req = request.getRequestDispatcher("Product_Display.jsp");
            req.forward(request, response);
        } else {
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
