<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <h1 style="Text-align:center; font-family: century gothic; color: green">Transporter Details</h1>
    
    
      <pre>
      Name    : ${transporter.transporterName}
      Email   : ${transporter.email}
      City    : ${transporter.city}
      State   : ${transporter.state}
      Mobile  : ${transporter.mobile}
      Address : ${transporter.residentialAddress}
      </pre>
      <img src="LoadTransporterDP?email=${transporter.email}" height="250" width="250"/>
      
      <br><hr><br>
      <a href="transporterHome">Home</a>
</body>
</html>