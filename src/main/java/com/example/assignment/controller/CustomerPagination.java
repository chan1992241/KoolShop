package com.example.assignment.controller;

import com.example.assignment.model.entity.Customer;
import com.example.assignment.model.entity.Employee;
import com.example.assignment.model.sessionbean.ClientSessionBeanLocal;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerPagination", value = "/CustomerPagination")
public class CustomerPagination extends HttpServlet {

    @EJB
    private ClientSessionBeanLocal cusbean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (AdminLoginValidator.isAdminLogin(request)) {
            int nOfPages = 0;
            int currentPage = Integer.valueOf(request.getParameter("currentPage"));
            int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
            String keyword = request.getParameter("keyword");
            String direction = request.getParameter("direction");

            List<Customer> cusList = cusbean.getAllCustomer();

            int rows = cusbean.getNumberOfRows(keyword);
            nOfPages = rows / recordsPerPage;
            if (rows % recordsPerPage != 0) {
                nOfPages++;
            }

            if (currentPage > nOfPages && nOfPages != 0) {
                currentPage = nOfPages;
            }
            List<Customer> lists = cusbean.readCustomer(currentPage, recordsPerPage, keyword, direction);
            request.setAttribute("CusPerPage", lists);

            request.setAttribute("nOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("keyword", keyword);
            request.setAttribute("direction", direction);
            request.setAttribute("cusList", cusList);

            RequestDispatcher req = request.getRequestDispatcher("CustomerPagination.jsp");
            req.forward(request, response);
        }
        else{
            request.setAttribute("login_status", "unsuccessful");
            request.getRequestDispatcher("Admin_Login").forward(request, response);
        }
        }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
