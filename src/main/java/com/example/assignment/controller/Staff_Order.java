package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.model.sessionbean.OrderSessionBeanLocal;
import com.example.assignment.utils.CustomerLoginValidator;
import com.example.assignment.utils.StaffLoginValidator;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Staff_Order", value = "/Staff_Order")
public class Staff_Order extends HttpServlet {
    @EJB
    private OrderSessionBeanLocal orderbean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StaffLoginValidator.isStaffLogin(request)){
            HttpSession session=request.getSession();
            int nOfPages= 0;
            int currentPage = Integer.valueOf(request.getParameter("currentPage").trim());
            int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage").trim());
            String keyword = request.getParameter("keyword");
            String direction = request.getParameter("direction");
            String staff_no = (String) session.getAttribute("staff_no");
            try {
                int rows = orderbean.getNumberOfRowsStaffOrder(staff_no, keyword);
                nOfPages = rows / recordsPerPage;
                if (rows % recordsPerPage != 0) {
                    nOfPages++;
                }
                if (currentPage > nOfPages && nOfPages != 0) {
                    currentPage = nOfPages;
                }
                List<Order> orders = orderbean.findOrderByStaff(staff_no ,currentPage, recordsPerPage, keyword, direction);
                request.setAttribute("orders", orders);
            } catch (EJBException ex) {
                System.out.println(ex);
            }
            if (CustomerLoginValidator.isCustomerLogin(request)){
                request.setAttribute("login_status", "successful");
            }else {
                request.setAttribute("login_status", "unsuccessful");
            }
            List<Object[]> order_details = orderbean.findOrderDetailsByStaff(staff_no);
            request.setAttribute("order_details", order_details);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("nOfPages", nOfPages);
            request.setAttribute("keyword", keyword);
            request.setAttribute("direction", direction);
            request.getRequestDispatcher("staff_order.jsp").forward(request, response);
        }else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Customer_Login").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
