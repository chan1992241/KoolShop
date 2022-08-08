<%@ page import="com.example.assignment.model.entity.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.assignment.model.entity.Office" %><%--
  Created by IntelliJ IDEA.
  User: Ma Yee Sheng
  Date: 6/8/2022
  Time: 6:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Details</title>
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
    //String direction = (String) request.getAttribute("direction");
%>

<form class="form-inline md-form mr-auto mb-4" action="EmployeePagination" method="get">
    <input class="form-control mr-sm-2" type="text" aria-label="Search"
           name="keyword" />
    <button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info"
            type="submit">Search</button>
    <input type="hidden" name="currentPage" value="<%=currentPage%>" />
    <input type="hidden" name="recordsPerPage" value="<%=recordsPerPage%>" />
    <%--    <input type="hidden" name="direction" value="<%=direction%>" />--%>

</form>
<%--<form class="form-inline md-form mr-auto mb-4"
      action="PaginationServlet" method="get">
    <select class="form-control" id="direction" name="direction">
        <option value="ASC">ascending</option>
        <option value="DESC">descending</option>
    </select>
    <button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info"
            type="submit">Sorting</button>
    <input type="hidden" name="currentPage" value="<%=currentPage%>" />
    <input type="hidden" name="recordsPerPage" value="<%=recordsPerPage%>" />
    <input type="hidden" name="keyword" value="<%=keyword%>" />

</form>--%>
<a class="btn btn-primary" href="EmployeeDisplay.html">Back</a>
<div class="row col-md-12">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th>ID</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Extension</th>
            <th>Email</th>
            <%--<th>Office Address</th>--%>
            <th>Reports To</th>
            <th>Job Title</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <%
            List<Employee> staffPerPage = (List<Employee>) request.getAttribute("EmpPerPage");
            if (staffPerPage.size() != 0) {
                for (Employee t : staffPerPage) {
                    out.println("<tr>");
                    out.println("<td>" + t.getId() + "</td>");
                    out.println("<td>" + t.getLastname() + "</td>");
                    out.println("<td>" + t.getFirstname() + "</td>");
                    out.println("<td>" + t.getExtension() + "</td>");
                    out.println("<td>" + t.getEmail() + "</td>");
                    //out.println("<td>" + t.getOfficecode().getAddressline1() + "</td>");
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
<nav aria-label="Navigation for staffs">
    <ul class="pagination">
        <%
            if (currentPage != 1 && nOfPages != 0) {
        %>
        <%
            out.println("<li class=\"page-item\">");
            out.println("<a class=\"page-link\" href=\"" + "EmployeePagination?recordsPerPage=" + recordsPerPage
                    + "&currentPage=1" + "&keyword=" + keyword +"\">First</a>");
            out.println("</li>");
        %>

        <li class="page-item">
            <%
                out.println("<a class=\"page-link\" href=\"" + "EmployeePagination?recordsPerPage=" + recordsPerPage
                        + "&currentPage=" + (currentPage - 1) + "&keyword=" + "\">Previous</a>");
            %>
        </li>
        <%
            }
        %>
        <%
            if (currentPage < nOfPages) {
                out.println("<li class=\"page-item\">");
                out.println("<a class=\"page-link\" href=\"" + "EmployeePagination?recordsPerPage=" + recordsPerPage
                        + "&currentPage=" + (currentPage + 1) + "&keyword=" + keyword + "\">Next</a>");
                out.println("</li>");
                out.println("<li class=\"page-item\">");
                out.println("<a class=\"page-link\" href=\"" + "EmployeePagination?recordsPerPage=" + recordsPerPage
                        + "&currentPage=" + nOfPages + "&keyword=" + keyword +"\">Last</a>");
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

<button class="open-button" onclick="openForm()">Add Employee</button>
<div class="form-popup" id="myForm">
    <%
        List<Employee> staffs = (List<Employee>) request.getAttribute("EMPList");
        List<Office> officeList = (List<Office>) request.getAttribute("OfficeList");
    %>
    <form action="EmployeeController" class="form-container" method="post">
        <h1>Add Employee</h1>
        <fieldset>
            <legend>Add Employee Details:</legend> <br>
            Last Name: <input type="text" name="fname" /> <br>
            First Name: <input type="text" name="lname" /> <br>
            Extension: <input type="text" name="extension" /> <br>
            Email: <input type="text" name="email" /> <br>
            Office:
           <select name="officeCode">
                <%
                    for (Office t : officeList) {
                        out.println("<option value=" + t.getId() + ">" + t.getAddressline1() + "</option>");
                    }
                %>
            </select> <br>
            Reports To:
            <select name="reportsTo">
                <%
                    for (Employee t : staffs) {
                        out.println("<option value=" + t.getId() + ">" + t.getId() + "</option>");
                    }
                %>
            </select> <br>
            Job Title: <input type="text" name="jobTitle" /> <br>
        </fieldset>
        <button type="submit" class="btn">Submit Test</button>
        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
        <button type="reset" class="btn">Reset</button>
    </form>
</div>

<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";
    }
    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }
</script>
</body>
</html>
