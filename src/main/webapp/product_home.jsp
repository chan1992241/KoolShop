<%--
  Created by IntelliJ IDEA.
  User: GM
  Date: 17/8/2022
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

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
    <link rel="stylesheet" href="css/product.css">
    <script src="js/vendor/modernizr-2.6.2.min.js"></script>

</head>
<body>
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
                            <li><a href="Home_Product">Shop</a></li>
                            <li><a href="Customer_Order">Order</a></li>
                            <li><a href="Staff_Login">Staff Order</a></li>

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
</header>
<br/><br/>

<!--content-->
<div class="main-content" style="background-color:#c0a16b">
    <div class="row">
        <h1 class="text-center" style="color:black;">Explore All Model</h1>
    </div>




            <div class="row">
        <div class="col-lg-6">

            <div class="card">
                <div class="image">
                  <a href="SelectProduct.html">  <image src="images/products/PresidentialLimousine.jpg" alt="Submit" width="100%" height="440px"/></a>
                </div>
            </div>
        </div>

                <div class="col-lg-6">
                    <div class="card">
                        <div class="image">
                            <a href="SelectProduct.html">  <image src="images/products/AstonMartindb5.jpg" alt="Submit" width="100%" height="440px"/></a>
                        </div>
                    </div>
                </div>

            </div>




</div>

<script src="js/plugins.js"></script>
<script src="js/main.js"></script>
</body>
</html>
