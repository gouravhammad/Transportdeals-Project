<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <h1 style="Text-align:center; font-family: century gothic; color: green">Customer Details</h1>
    
    
      <pre>
      Name    : ${customer.userName}
      Email   : ${customer.email}
      City    : ${customer.city}
      State   : ${customer.state}
      Mobile  : ${customer.mobile}
      Address : ${customer.residentialAddress}
      </pre>
      <img src="LoadCustomerDP?email=${customer.email}" height="250" width="250"/>
      
      <br><hr><br>
      <a href="customerHome">Home</a>
</body>
</html>