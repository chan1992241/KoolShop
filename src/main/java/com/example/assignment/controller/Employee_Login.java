package com.example.assignment.controller;
import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.utils.EmployeeLoginValidator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Employee_Login", value = "/Employee_Login")
public class Employee_Login extends HttpServlet {
    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("employee_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employee_number=request.getParameter("employee_number");
        String extension=request.getParameter("extension");
        boolean isCorrectAuthentication = employeeSessionBean.checkEmployeeNumberwithExtension(employee_number, extension);
        if(isCorrectAuthentication == true){
            HttpSession session=request.getSession();
            session.setAttribute("employee_number", employee_number);

            if (EmployeeLoginValidator.isEmployeeLogin(request)){
                request.setAttribute("login_status", "successful");
                request.getRequestDispatcher("employee_home.html").forward(request, response);
            }
        }
        else{
            request.setAttribute("login_status", "Wrong employee number or extension");
            request.getRequestDispatcher("employee_login.jsp").include(request, response);
        }
    }
}
