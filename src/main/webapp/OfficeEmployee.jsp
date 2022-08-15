<%@ page import="com.example.assignment.model.entity.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ma Yee Sheng
  Date: 15/8/2022
  Time: 8:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <title>View Office Employees</title>
</head>
<body class="m-3">
<h2>List of Employees in the Office</h2>
<a class="btn btn-primary" href="OfficePagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC">Back</a>
<div class="row col-md-12">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th>ID</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Extension</th>
            <th>Email</th>
            <th>Office Code</th>
            <th>Reports To</th>
            <th>Job Title</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <%
            List<Employee> staffs = (List<Employee>) request.getAttribute("EMPList");
            if (staffs.size() != 0) {
                for (Employee t : staffs) {
                    out.println("<tr>");
                    out.println("<td>" + t.getId() + "</td>");
                    out.println("<td>" + t.getLastname() + "</td>");
                    out.println("<td>" + t.getFirstname() + "</td>");
                    out.println("<td>" + t.getExtension() + "</td>");
                    out.println("<td>" + t.getEmail() + "</td>");
                    out.println("<td>" + t.getOfficecode().getId() + "</td>");
                    out.println("<td>" + t.getReportsto() + "</td>");
                    out.println("<td>" + t.getJobtitle() + "</td>");
                    out.println("<td><a href=\"EmployeeController?id=" + t.getId() + "\">Update</a></td>");
                    out.println("<td><a href=\"EmployeeController?id=" + t.getId() + "\">Delete</a></td>");
                    out.println("</tr>");

                }
            }
            else {
                out.println("<tr>");
                String status = "No records";
                for (int i = 0; i < 8; i++) {
                    out.println("<td>" + status + "</td>");
                }
                out.println("</tr>");
            }
        %>
    </table>
</div>
</body>
</html>
