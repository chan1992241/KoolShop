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
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
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

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/normalize.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/templatemo-misc.css">
    <link rel="stylesheet" href="css/templatemo-style.css">

    <script src="js/vendor/modernizr-2.6.2.min.js"></script>

</head>
<body>
<!--[if lt IE 7]>
<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
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
                            <li><a href="Staff_Login">Staff Order</a></li>
                            <li><a href="clientpayment_login">Customer</a></li>
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
        <form id="quickSearchForm">
            <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="submit" id="quickSearchBtn">Quick Search</button>
                <input type="hidden" name="action" value="quickSearch"/>
                <input type="text" class="form-control" placeholder="Enter Order ID" id="orderID" name="orderID" aria-label="Example text with button addon" aria-describedby="button-addon1">
            </div>
        </form>
        <table class="table" id="order_details">

        </table>
        <hr/>
        <h1>My Current Order</h1>
        <div class="d-flex">
            <form action="Customer_Order" method="get">
                <button type="submit" type="submit" class="btn btn-info mb-3">Current Order</button>
            </form>
            <form action="Customer_Order_History" method="get">
                <input type="hidden" name="currentPage" value="1" />
                <input type="hidden" name="recordsPerPage" value="3" />
                <input type="hidden" name="direction" value="ASC" />
                <input type="hidden" name="keyword" value="" />
                <button type="submit" class="btn btn-secondary ms-3 mb-3">Order History</button>
            </form>
        </div>
        <div class="list-group">
            <% List<Order> orders = (List<Order>) request.getAttribute("order"); %>
            <% Order order = orders.get(0); %>
            <form method="post" action="Customer_Order">
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title">Order ID: <%= order.getId() %></h2>
                        <h5 class="card-subtitle mb-2 text-muted">Status: <%= order.getStatus() %></h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Product Name</th>
                                <th scope="col">Price Each</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total (RM)</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                        <% List<Object[]> customer_orders = (List<Object[]>) request.getAttribute("order_details"); %>
                        <% BigDecimal total = new BigDecimal(0); %>
                        <% for (Object[] customer_order: customer_orders){ %>
                            <% Product product = (Product) customer_order[0]; %>
                            <% Orderdetail orderdetail = (Orderdetail) customer_order[1]; %>
                            <% total = total.add(orderdetail.getPriceeach().multiply(new BigDecimal(orderdetail.getQuantityordered())));%>
                            <tr>
                                <td><%= product.getProductname() %></td>
                                <td>RM<%= orderdetail.getPriceeach() %></td>
                                <td><input type="number" step="1" name="<%= product.getId() %>" value="<%= orderdetail.getQuantityordered() %>"/></td>
                                <td><%= orderdetail.getPriceeach().multiply(new BigDecimal(orderdetail.getQuantityordered())) %></td>
                                <td><button type="submit" name="deleteProduct" class="btn btn-primary" value="<%=product.getId()%>">Delete</button></td>
                            </tr>
                        <% } %>
                            <tr>
                                <td></td>
                                <td></td>
                                <td class="text-end">Total</td>
                                <td><input type="hidden" name="total_amount" value="<%=total%>"/><%= total%></td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="hidden" name="orderID" value="<%= order.getId() %>" />
                        <button type="submit" class="btn btn-success mt-3" name="action" value="update">Update</button>
                        <button type="submit"  class="btn btn-primary mt-3" name="action" value="pay">Pay</button>
                    </div>
                </div>
            </form>
        </div>

    </div> <!-- /.container -->
</div> <!-- /.content-section -->
<script src="js/plugins.js"></script>
<script src="js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    $(document).ready(function () {
        $("#quickSearchBtn").click(function () {
            let form_data = $("#quickSearchForm").serialize()
            console.log(form_data)
            $("#order_details").empty();
            $.ajax({
                url: 'Customer_Order',
                type: 'POST',
                dataType: 'json',
                data: form_data,
                success: function (data) {
                        if (data === null) {
                            alert("Invalid employeeid");
                        } else {
                            let htmlString = '<thead><tr><th scope="col">Product Name</th><th scope="col">Price Each</th><th scope="col">Quantity</th><th scope="col">Total (RM)</th></tr></thead><tbody>'
                            let total_payment = 0;
                            for (let item of data.data){
                                let current_total = item[1].priceeach * item[1].quantityordered
                                total_payment += current_total
                                htmlString += "<tr><td>" + item[0].productname + "</td><td>" + item[1].priceeach + "</td><td>" + item[1].quantityordered+ "</td><td>" + current_total + "</td></tr>"
                            }
                            htmlString += '<tr><td></td><td></td><td>Total</td><td>'+total_payment+'</td></tr></tbody>'
                            $("#order_details").append(htmlString);
                        }
                    }, error: function (e) {
                    alert('Invalid id');
                }
            });
            return false;
        })
    })
</script>

</body>
</html>
