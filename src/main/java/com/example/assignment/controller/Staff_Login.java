package com.example.assignment.controller;

import com.example.assignment.model.entity.Order;
import com.example.assignment.model.sessionbean.CustomerSessionBeanLocal;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.model.sessionbean.OrderSessionBeanLocal;
import com.example.assignment.utils.CustomerLoginValidator;
import com.example.assignment.utils.StaffLoginValidator;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Staff_Login", value = "/Staff_Login")
public class Staff_Login extends HttpServlet {
    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;
    @EJB
    private OrderSessionBeanLocal orderbean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("staff_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String staff_no = request.getParameter("staff_no");
        String staff_email = request.getParameter("staff_email");
        boolean isCorrectAuthentication = employeeSessionBean.checkStaffNumberwithEmail(staff_no, staff_email);
        if(isCorrectAuthentication == true){
            session.setAttribute("staff_no", staff_no);
            if (StaffLoginValidator.isStaffLogin(request)){
                request.setAttribute("staff_login_status", "successful");
            }if (CustomerLoginValidator.isCustomerLogin(request)){
                request.setAttribute("login_status", "successful");
            }

            // Pagination attribute
            int nOfPages= 0;
            int currentPage = 1;
            int recordsPerPage = 1;
            String keyword = "";
            String direction = "ASC";
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
            List<Object[]> order_details = orderbean.findOrderDetailsByStaff(staff_no);
            request.setAttribute("order_details", order_details);
            request.setAttribute("currentPage", 1);
            request.setAttribute("recordsPerPage", 1);
            request.setAttribute("nOfPages", nOfPages);
            request.setAttribute("keyword", keyword);
            request.setAttribute("direction", direction);
            request.getRequestDispatcher("staff_order.jsp").forward(request, response);
        }
        else{
            request.setAttribute("login_status", "Wrong staff number or phone number");
            request.getRequestDispatcher("staff_login.jsp").include(request, response);
        }
    }
}
