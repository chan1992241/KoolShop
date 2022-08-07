package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.sessionbean.OrderSessionBeanLocal;
import com.example.assignment.utils.CustomerLoginValidator;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Customer_Order_History", value = "/Customer_Order_History")
public class Customer_Order_History extends HttpServlet {
    @EJB
    private OrderSessionBeanLocal orderbean;
    @PersistenceContext(unitName = "default")
    EntityManager em;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CustomerLoginValidator.isCustomerLogin(request)){
            //Get all parameter
            HttpSession session=request.getSession();
            int nOfPages= 0;
            int currentPage = Integer.valueOf(request.getParameter("currentPage").trim());
            int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage").trim());
            String keyword = request.getParameter("keyword");
            String direction = request.getParameter("direction");
            String customer_number = (String) session.getAttribute("customer_number");
            try {
                int rows = orderbean.getNumberOfRows(customer_number, keyword);
                nOfPages = rows / recordsPerPage;
                if (rows % recordsPerPage != 0) {
                    nOfPages++;
                }
                if (currentPage > nOfPages && nOfPages != 0) {
                    currentPage = nOfPages;
                }
                List<Order> orders = orderbean.getOrderHistory(customer_number ,currentPage, recordsPerPage, keyword, direction);
                request.setAttribute("orders", orders);
            } catch (EJBException ex) {
                System.out.println(ex);
            }

//            List<Order> order = orderbean.getOrderHistory(customer_number, 1, 70, keyword, "ASC");
            List<Object[]> order_details = orderbean.getOrderHistoryDetails(customer_number,1, 70, "ASC", keyword);
//            request.setAttribute("order", order);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("nOfPages", nOfPages);
            request.setAttribute("keyword", keyword);
            request.setAttribute("direction", direction);
            request.setAttribute("order_details", order_details);
            request.setAttribute("login_status", "successful");
            request.getRequestDispatcher("customer_order_history.jsp").forward(request, response);
        }else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
