<%@ page import="com.example.assignment.model.entity.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: GM
  Date: 15/8/2022
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Detail</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        /* Button used to open the contact form - fixed at the bottom of the page */
        .open-button {
            background-color: #555;
            color: white;
            padding: 16px 20px;
            border: none;
            cursor: pointer;
            opacity: 0.8;
            position: fixed;
            bottom: 23px;
            right: 28px;
            width: 280px;
        }

        /* The popup form - hidden by default */
        .form-popup {
            overflow-x: hidden;
            overflow-y: auto;
            height: 400px;
            display: none;
            position: fixed;
            top: 60%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

        /* Add styles to the form container */
        .form-container {
            max-width: 500px;
            padding: 10px;
            background-color: white;
        }

        /* Full-width input fields */
        .form-container input[type=text], .form-container input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            border: none;
            background: #f1f1f1;
        }

        /* When the inputs get focus, do something */
        .form-container input[type=text]:focus, .form-container input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        /* Set a style for the submit button */
        .form-container .btn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom: 10px;
            opacity: 0.8;
        }

        /* Add a red background color to the cancel button */
        .form-container .cancel {
            background-color: red;
        }

        /* Add some hover effects to buttons */
        .form-container .btn:hover, .open-button:hover {
            opacity: 1;
        }

        pageref {
            text-align: center;
            font-weight: bold;
        }
    </style>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body class="m-3">
<%
    int currentPage = (int) request.getAttribute("currentPage");
    int recordsPerPage = (int) request.getAttribute("recordsPerPage");
    int nOfPages = (int) request.getAttribute("nOfPages");
    String keyword = (String) request.getAttribute("keyword");
    String direction = (String) request.getAttribute("direction");
%>

<form class="form-inline md-form mr-auto mb-4" action="CustomerPagination" method="get">
    <input class="form-control mr-sm-2" type="text" aria-label="Search"
           name="keyword" />
    <button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info"
            type="submit">Search</button>
    <input type="hidden" name="currentPage" value="<%=currentPage%>" />
    <input type="hidden" name="recordsPerPage" value="<%=recordsPerPage%>" />
    <input type="hidden" name="direction" value="<%=direction%>" />

</form>

<form class="form-inline md-form mr-auto mb-4"
      action="CustomerPagination" method="get">
    <select class="form-control" id="direction" name="direction">
        <option value="ASC">ascending</option>
        <option value="DESC">descending</option>
    </select>
    <button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info"
            type="submit">Sorting</button>
    <input type="hidden" name="currentPage" value="<%=currentPage%>" />
    <input type="hidden" name="recordsPerPage" value="<%=recordsPerPage%>" />
    <input type="hidden" name="keyword" value="<%=keyword%>" />

</form>

<a class="btn btn-primary" href="employee_home.html">Back</a>
<div class="row col-md-12">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th>ID</th>
            <th>Customer Name</th>
            <th>Phone</th>
             <th>Address</th>
            <th>City</th>
            <th>State</th>

            <th>Update</th>
            <th>Delete</th>
        </tr>
        <%
            List<Customer> CusPerPage = (List<Customer>) request.getAttribute("CusPerPage");
            if (CusPerPage.size() != 0) {
                for (Customer C : CusPerPage) {
                    out.println("<tr>");
                    out.println("<td>" + C.getId() + "</td>");
                    out.println("<td>" + C.getCustomername() + "</td>");
                    out.println("<td>" + C.getPhone() + "</td>");
                    out.println("<td>" + C.getAddressline1() + " "+ C.getAddressline2()+"</td>");
                    out.println("<td>" + C.getCity() + "</td>");
                    out.println("<td>" + C.getState() + "</td>");



                    out.println("<td><a href=\"CustomerController?id=" + C.getId() + "\">Update</a></td>");
                    out.println("<td><a href=\"CustomerController?id=" + C.getId() + "\">Delete</a></td>");
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

<nav aria-label="Navigation for customer">
    <ul class="pagination">
        <%
            if (currentPage != 1 && nOfPages != 0) {
        %>
        <%
            out.println("<li class=\"page-item\">");
            out.println("<a class=\"page-link\" href=\"" + "CustomerPagination?recordsPerPage=" + recordsPerPage
                    + "&currentPage=1" + "&keyword=" + keyword + "&direction=" + direction + "\">First</a>");
            out.println("</li>");
        %>

        <li class="page-item">
            <%
                out.println("<a class=\"page-link\" href=\"" + "CustomerPagination?recordsPerPage=" + recordsPerPage
                        + "&currentPage=" + (currentPage - 1) + "&keyword=" + keyword + "&direction=" + direction + "\">Previous</a>");
            %>
        </li>
        <%
            }
        %>
        <%
            if (currentPage < nOfPages) {
                out.println("<li class=\"page-item\">");
                out.println("<a class=\"page-link\" href=\"" + "CustomerPagination?recordsPerPage=" + recordsPerPage
                        + "&currentPage=" + (currentPage + 1) + "&keyword=" + keyword + "&direction=" + direction + "\">Next</a>");
                out.println("</li>");
                out.println("<li class=\"page-item\">");
                out.println("<a class=\"page-link\" href=\"" + "CustomerPagination?recordsPerPage=" + recordsPerPage
                        + "&currentPage=" + nOfPages + "&keyword=" + keyword + "&direction=" + direction + "\">Last</a>");
                out.println("</li>");
            }
        %>
    </ul>
</nav>
<%
    if (nOfPages != 0) {
        out.println("<p class=\"pageref\">");
        out.println(currentPage + " of " + nOfPages);
        out.println("</p>");
    }
%>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.js"></script>


</body>
</html>
