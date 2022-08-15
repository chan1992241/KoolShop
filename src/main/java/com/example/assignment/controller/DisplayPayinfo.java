package com.example.assignment.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DisplayPayinfo", value = "/DisplayPayinfo")
public class DisplayPayinfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try (PrintWriter ot = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Display Billing Address</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"margin-left: 400px;margin-top: 200px;background-color:#ffcccc;width:30%\">");
            out.println("<h2>Welcome " + request.getParameter("name")+"</h2>");
            out.println("<h2>Total Amount : " + request.getParameter("total_amount")+"</h2>");
            out.println("<h2> Checkout Successfull"+"</h2>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }

    }
}
