<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/8/2022
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.assignment.model.entity.Order" %>
<%@ page import="com.example.assignment.model.entity.Orderdetail" %>
<%@ page import="com.example.assignment.model.entity.Product" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/7/2022
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <!--
    Kool Store Template
    http://www.templatemo.com/preview/templatemo_428_kool_store
    -->
    <meta charset="utf-8">
    <title>Kool Store</title>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/normalize.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/templatemo-misc.css">
    <link rel="stylesheet" href="css/templatemo-style.css">

    <script src="js/vendor/modernizr-2.6.2.min.js"></script>

</head>
<body>
<!--[if lt IE 7]>
<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to
    improve your experience.</p>
<![endif]-->


<header class="site-header">
    <div class="main-header">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-sm-6 col-xs-8">
                    <div class="logo">
                        <h1><a href="#">Kool Store</a></h1>
                    </div> <!-- /.logo -->
                </div> <!-- /.col-md-4 -->
            </div> <!-- /.row -->
        </div> <!-- /.container -->
    </div> <!-- /.main-header -->
    <div class="main-nav">
        <div class="container">
            <div class="row">
                <div class="col-md-11">
                    <div class="list-menu">
                        <ul>
                            <li><a href="Servlet">Shop</a></li>
                            <li><a href="Customer_Order">Order</a></li>
                            <li><a href="#">Staff</a></li>
                        </ul>
                    </div> <!-- /.list-menu -->
                </div> <!-- /.col-md-6 -->
                <div class="col-md-1">
                    <div class="list-menu">
                        <ul>
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

<div class="content-section">
    <div class="container">
        <div class="d-flex">
            <form action="Customer_Order" method="get">
                <button type="submit" type="submit" class="btn btn-info mb-3">Current Order</button>
            </form>
            <form action="Customer_Order_History" method="get">
                <input type="hidden" name="currentPage" value="1" />
                <input type="hidden" name="recordsPerPage" value="10" />
                <input type="hidden" name="direction" value="ASC" />
                <input type="hidden" name="keyword" value="" />
                <button type="submit" class="btn btn-secondary ms-3 mb-3">Order History</button>
            </form>
        </div>
        <div class="list-group">
            <% List<Order> orders = (List<Order>) request.getAttribute("orders"); %>
            <% List<Object[]> customer_orders = (List<Object[]>) request.getAttribute("order_details"); %>
            <% for (Order order: orders){ %>
                <form method="post" class="mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title">Order ID: <%= order.getId() %>
                            </h2>
                            <h5 class="card-subtitle mb-2 text-muted">Status: <%= order.getStatus() %>
                            </h5>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Product Name</th>
                                    <th scope="col">Price Each</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total (RM)</th>
                                </tr>
                                </thead>
                                <tbody>

                                <% BigDecimal total = new BigDecimal(0); %>
                                <% for (Object[] customer_order : customer_orders) { %>
                                    <% Product product = (Product) customer_order[0]; %>
                                    <% Orderdetail orderdetail = (Orderdetail) customer_order[1]; %>
                                    <% int orderDetailOrderNo = orderdetail.getOrdernumber().getId(); %>
                                    <% int orderID = order.getId(); %>
                                    <% if (orderDetailOrderNo == orderID){ %>
                                            <% total = total.add(orderdetail.getPriceeach().multiply(new BigDecimal(orderdetail.getQuantityordered())));%>
                                            <tr>
                                                <td><%= product.getProductname() %>
                                                </td>
                                                <td>RM<%= orderdetail.getPriceeach() %>
                                                </td>
                                                <td><input type="number" step="1" value="<%= orderdetail.getQuantityordered() %>"/></td>
                                                <td><%= orderdetail.getPriceeach().multiply(new BigDecimal(orderdetail.getQuantityordered())) %>
                                                </td>
                                            </tr>
                                        <% }%>
                                    <% } %>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td class="text-end">Total</td>
                                    <td><%= total%>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
<%--                            <button type="button" type="submit" class="btn btn-success mt-3">Update</button>--%>
<%--                            <button type="button" type="submit" class="btn btn-primary mt-3">Pay</button>--%>
                        </div>
                    </div>
                </form>
            <% } %>

        </div>

    </div> <!-- /.container -->
</div> <!-- /.content-section -->
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>


</body>
</html>

