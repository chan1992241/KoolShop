package com.example.assignment.controller;

import com.example.assignment.model.sessionbean.EmployeeSessionBeanLocal;
import com.example.assignment.utils.AdminLoginValidator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Admin_Login", value = "/Admin_Login")
public class Admin_Login extends HttpServlet {
    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        PrintWriter out = response.getWriter();

        if(username.equals("admin") && password.equals("admin")){
            HttpSession session=request.getSession();
            session.setAttribute("username", username);

            if (AdminLoginValidator.isAdminLogin(request)){
                request.setAttribute("login_status", "successful");
                request.getRequestDispatcher("admin_home.html").forward(request, response);
            }
        }
        else{
            out.println("<SCRIPT type=\"text/javascript\">");
            out.println("alert(\"Wrong Admin username and password\")");
            out.println("</SCRIPT>");
            request.getRequestDispatcher("admin_login.jsp").include(request, response);
        }
    }
}
