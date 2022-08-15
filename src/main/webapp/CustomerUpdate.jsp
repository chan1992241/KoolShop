<%@ page import="com.example.assignment.model.entity.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: GM
  Date: 15/8/2022
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <title>Update and Delete Customer</title>

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
    Customer cus = (Customer) request.getAttribute("cus");
    List<Customer> customers = (List<Customer>) request.getAttribute("cusList");

%>

<a class="btn btn-primary" href="CustomerPagination?currentPage=1&recordsPerPage=20&keyword=&direction=ASC">Back</a>
<form action="CustomerController" method="post">
    <table>
        <tr>
            <td>Customer ID</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"id\" readonly value=" + cus.getId());
                %>
            </td>
        </tr>
        <tr>
            <td>Customer Name</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"name\" maxlength=\"25\" required value=\"" + cus.getCustomername() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Phone Number</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"phone\" maxlength=\"20\" required value=\"" + cus.getPhone() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>AddressLine1</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"address1\" maxlength=\"25\" required value=\"" +  cus.getAddressline1() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>AddressLine2</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"address2\" maxlength=\"25\" required value=\"" +  cus.getAddressline2() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>City</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"city\" maxlength=\"25\" required value=\"" +  cus.getCity() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>State</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"state\" maxlength=\"15\" required value=\"" +  cus.getCity() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Postal Code</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"postal\" maxlength=\"15\" required value=\"" +  cus.getPostalcode() + "\"");
                %>
            </td>
        </tr>
        <tr>
            <td>Country</td>
            <td>
                <%
                    out.println("<input type=\"text\" name=\"country\" maxlength=\"25\" required value=\"" +  cus.getCountry() + "\"");
                %>
            </td>
        </tr>

    </table>
    <input type="submit" name="UPDATE" value="UPDATE" />
    <input type="submit" name="DELETE" value="DELETE" />
</form>
</body>
</html>
