package com.example.assignment.controller;

import com.example.assignment.model.entity.Office;
import com.example.assignment.model.sessionbean.OfficeSessionBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OfficePagination", value = "/OfficePagination")
public class OfficePagination extends HttpServlet {

    @EJB
    private OfficeSessionBeanLocal officeBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nOfPages= 0;
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        String keyword = request.getParameter("keyword");
        //String direction = request.getParameter("direction");

        int rows = officeBean.getNumberOfRows(keyword);
        nOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage != 0) {
            nOfPages++;
        }

        if (currentPage > nOfPages && nOfPages != 0) {
            currentPage = nOfPages;
        }
        List<Office> lists = officeBean.readOffice(currentPage, recordsPerPage, keyword);
        request.setAttribute("OfficePerPage", lists);

        request.setAttribute("nOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("keyword", keyword);
        //request.setAttribute("direction", direction);

        RequestDispatcher req = request.getRequestDispatcher("OfficePagination.jsp");
        req.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
