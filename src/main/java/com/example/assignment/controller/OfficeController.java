package com.example.assignment.controller;

import com.example.assignment.model.entity.Office;
import com.example.assignment.model.sessionbean.OfficeSessionBeanLocal;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OfficeController", value = "/OfficeController")
public class OfficeController extends HttpServlet {

    @EJB
    private OfficeSessionBeanLocal officeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        /*try{*/
        Office office = officeBean.findOffice(id);

        request.setAttribute("Office", office);

        RequestDispatcher req = request.getRequestDispatcher("OfficeUpdate.jsp");
        req.forward(request, response);

        /*}catch (EJBException ex){

        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String officeID = request.getParameter("id");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String addressline1 = request.getParameter("addressline1");
        String addressline2 = request.getParameter("addressline2");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String postalcode = request.getParameter("postalcode");
        String territory = request.getParameter("territory");
        PrintWriter out = response.getWriter();

        String[] officeData = {officeID, city, phone, addressline1, addressline2, state, country, postalcode, territory};

        //try {
        if (request.getParameter("UPDATE")!=null&&request.getParameter("UPDATE").equals("UPDATE")) {
            officeBean.updateOffice(officeData);
        }
        else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("DELETE")) {
            try {
                officeBean.deleteOffice(officeID);
            }
            catch (EJBException ex){
                out.println("<SCRIPT type=\"text/javascript\">");
                out.println("alert(\"Office cannot be deleted because the Employees still exist\")");
                out.println("</SCRIPT>");
            }
        }
        else {
            officeBean.addOffice(officeData);
        }
        out.println("<SCRIPT type=\"text/javascript\">");
        out.println("alert(\"Record has been updated and url will be redirected\")");
        out.println("window.location.assign(\"OfficePagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC\")");
        out.println("</SCRIPT>");
        /*} catch (EJBException ex) {
        }*/
    }
}
