<%@ page import="com.example.assignment.model.entity.Office" %><%--
  Created by IntelliJ IDEA.
  User: Ma Yee Sheng
  Date: 8/8/2022
  Time: 6:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <title>Update and Delete Office</title>

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
    Office office = (Office) request.getAttribute("Office");
%>
<a class="btn btn-primary" href="OfficePagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC">Back</a>
<form action="OfficeController" method="post">
    <table>
        <tr>
            <td>Office Code</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"id\" readonly value=" + office.getId());
                %>
            </td>
        </tr>
        <tr>
            <td>City</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"city\" value=\"" + office.getCity() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"phone\" value=\"" + office.getPhone() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Address Line 1</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"addressline1\" value=\"" + office.getAddressline1() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Address Line 2</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"addressline2\" value=\"" + office.getAddressline2() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>State</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"state\" value=\"" + office.getState() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Country</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"country\" value=\"" + office.getCountry() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Postal Code</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"postalcode\" value=\"" + office.getPostalcode() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Territory</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"territory\" value=\"" + office.getTerritory() + "\"");
                %>
            </td>
        <tr>
    </table>
    <input type="submit" name="UPDATE" value="UPDATE" />
    <input type="submit" name="DELETE" value="DELETE" />
</form>
</body>
</html>
