<%@ page import="com.example.assignment.model.entity.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.assignment.model.entity.Office" %><%--
  Created by IntelliJ IDEA.
  User: Ma Yee Sheng
  Date: 8/8/2022
  Time: 3:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <title>Update and Delete Employee</title>

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<%
    Employee emp = (Employee) request.getAttribute("EMP");
    //Office office = (Office) request.getAttribute("empOffice");
    List<Employee> staffs = (List<Employee>) request.getAttribute("EMPList");
    List<Office> officeList = (List<Office>) request.getAttribute("OfficeList");
%>
<a class="btn btn-primary" href="EmployeePagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC">Back</a>
<form action="EmployeeController" method="post">
    <table>
        <tr>
            <td>Employee ID</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"id\" readonly value=" + emp.getId());
                %>
            </td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"lname\" maxlength=\"9\" required value=\"" + emp.getLastname() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>First Name</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"fname\" maxlength=\"8\" required value=\"" + emp.getFirstname() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Extension</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"extension\" maxlength=\"5\" required value=\"" + emp.getExtension() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><%
                out.println("<input type=\"email\" name=\"email\" maxlength=\"31\" required value=\"" + emp.getEmail() + "\"");
            %>
            </td> </tr>
        <tr>
            <td>Office</td>
            <td>
                <select name="officeCode">
                    <%
                        for (Office t : officeList) {
                            out.println("<option value=" + t.getId() + ">" + t.getId() + " | " + t.getCity() + "</option>");
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Reports To (Employee ID)</td>
            <td>
                <select name="reportsTo">
                    <%
                        for (Employee t : staffs) {
                            out.println("<option value=" + t.getId() + ">" + t.getId() + "</option>");
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Job Title</td>
            <td><%
                out.println("<input type=\"text\" name=\"jobTitle\" maxlength=\"20\" required value=\"" + emp.getJobtitle() + "\"");
            %>
            </td> </tr>
        <tr>
        <tr>
    </table>
    <input type="submit" name="UPDATE" value="UPDATE" />
    <input type="submit" name="DELETE" value="DELETE" />
</form>
</body>
</html>
