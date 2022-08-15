package com.example.assignment.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "payment_controller", value = "/payment_controller")
public class payment_controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postal = request.getParameter("postal");
        //TODO: used your total payment
        String total_amount = request.getParameter("total_amount");
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("address", address);
        request.setAttribute("city", city);
        request.setAttribute("state", state);
        request.setAttribute("postal", postal);

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || postal.isEmpty()) {
            out.println("<SCRIPT type=\"text/javascript\">");
            out.println("alert(\"Please enter your information\")");
            out.println("</SCRIPT>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Payment </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"margin-left: 400px;margin-top: 200px;background-color: #ffcccc;width:30%\">");
            out.println("BILLING ADDRESS");
            out.println("<p>");
            out.println("Full Name: " + name);
            out.println("<br>");
            out.println("Email: " + email);
            out.println("<br>");
            out.println("Address: " + address);
            out.println("<br>");
            out.println("City: " + city);
            out.println("<br>");
            out.println("State: " + state);
            out.println("<br>");
            out.println("Postal Code: " + postal);
            out.println("<br>");
            out.println("Total amount: " + total_amount);
            out.println("<br>");
            out.println("</p>");

            out.println("<FORM METHOD=\"POST\" ACTION=\"DisplayPayinfo\">");
            out.println("<BR><INPUT TYPE=hidden name=\"name\" value=\""+ name + "\">");
            out.println("<BR><INPUT TYPE=submit value=\"Confirm\">");
            out.println("</FORM>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
