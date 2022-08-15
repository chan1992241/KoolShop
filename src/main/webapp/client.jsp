<%--
  Created by IntelliJ IDEA.
  User: GM
  Date: 14/8/2022
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Kool Store</title>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="css/normalize.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/client.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/templatemo-misc.css">
    <link rel="stylesheet" href="css/templatemo-style.css">

    <script src="js/vendor/modernizr-2.6.2.min.js"></script>

</head>
<body>
<header class="site-header">
    <div class="main-header">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-sm-6 col-xs-8">
                    <div class="logo">
                        <h1><a href="Servlet">Kool Store</a></h1>
                    </div> <!-- /.logo -->
                </div> <!-- /.col-md-4 -->
            </div> <!-- /.row -->
        </div> <!-- /.container -->
    </div> <!-- /.main-header -->
    <div class="main-nav">
        <div class="container">
            <div class="row">
                <div class="col-md-10">
                    <div class="list-menu">
                        <ul>
                            <li><a href="Servlet">Shop</a></li>
                            <li><a href="Customer_Order">Order</a></li>
                            <li><a href="Staff_Login">Staff Order</a></li>
                            <li><a href="clientpayment_login">Customer</a></li>
                        </ul>
                    </div> <!-- /.list-menu -->
                </div> <!-- /.col-md-6 -->
                <div class="col-md-2">
                    <div class="list-menu">
                        <ul style="display: flex">
                            <% if (!(request.getAttribute("login_status").equals("successful"))) { %>
                            <li><a href="Admin_Login">Admin Login</a></li>
                            <% } %>
                            <li>
                                <% if (request.getAttribute("login_status").equals("successful")) { %>
                                <a href="Customer_Logout">logout</a>
                                <% } else { %>
                                <a href="Customer_Login">Login</a>
                                <% } %>
                            </li>
                        </ul>
                    </div> <!-- /.list-menu -->
                </div> <!-- /.col-md-6 -->
            </div> <!-- /.row -->
        </div> <!-- /.container -->
    </div> <!-- /.main-nav -->
</header> <!-- /.site-header -->

<div class="form-popup" id="myForm">
    <form action="payment_controller" class="form-container" >
        <h1 class="text-left">BILLING ADDRESS</h1>
        <table class="tbl-full">
            <tbody>
            <tr>
                <td>Full Name:</td>
            </tr>
            <tr>
                <td > <input type="text" id="name" name="name" placeholder="Enter FullName"></td>
            </tr>

            <tr>
                <td >Email: </td>
            </tr>
            <tr>
                <td ><input type="email" id="email" name="email" placeholder="Enter Email"></td>
            </tr>
            <tr>
                <td >Address</td>
            </tr>
            <tr>
                <td ><input type="text" id="address" name="address" placeholder="Enter Address"></td>
            </tr>
            <tr>
                <td >City:</td>
            </tr>
            <tr>
                <td ><input type="text" id="city" name="city" placeholder="Enter City"></td>
            </tr>
            <tr>
                <td  >State: </td>
                <td >Postal Code: </td>
            </tr>
            <tr>
                <td ><input type="text" id="state" name="state" placeholder="Enter State"></td>
                <td ><input type="text" id="postal" name="postal" placeholder="Enter Postal Code"></td>
            </tr>


            </tbody>
        </table>
        <input type="hidden" name="total_amount" value="<%= request.getParameter("total_amount")%>"/>
        <button type="submit" class="btn-primary">Check Out</button>
        <button type="reset" class="btn-primary">Reset</button>
    </form>
</div>



</body>
</html>
