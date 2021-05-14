<%@page import= "com.Order" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/order.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Order Management </h1>
<form id="formOrder" name="formOrder" method="post" action="orders.jsp">
 Order Name : 
<input id="orderName" name="orderName" type="text" 
 class="form-control form-control-sm">
<br> Order Category : 
<input id="orderCategory" name="orderCategory" type="text" 
 class="form-control form-control-sm">
<br> Payment Method : 
<input id="paymentMethod" name="paymentMethod" type="text" 
 class="form-control form-control-sm">
<br> Payment : 
<input id="orderPayment" name="orderPayment" type="text" 
 class="form-control form-control-sm">
 <br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hiduIdSave" name="hiduIdSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divUsersGrid">

<%
 Order userObj = new Order(); 
 out.print(userObj.readOrder()); 
%>
</div>

</div></div></div>

</body>
</html>