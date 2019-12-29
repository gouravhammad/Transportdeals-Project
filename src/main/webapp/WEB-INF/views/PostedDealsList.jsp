<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1	style="Text-align: center; font-family: century gothic; color: green">MY POSTED DEALS</h1>

	<table border="2">
		<tr>
			<th>REG-NO</th>
			<th>DATE</th>
			<th>SOURCE CITY</th>
			<th>DESTINATION CITY</th>
			<th>FARE</th>
			<th>UPDATE-DEAL</th>
			<th>REMOVE DEAL</th>
		</tr>
		<jstl:forEach var="deal" items="${deals}">
			<tr>
				<td>${deal.vehicle.regNo}</td>
				<td>${deal.date}</td>
				<td>${deal.sourceCity}</td>
				<td>${deal.destinationCity}</td>
				<td>${deal.fare}</td>
				<td><a href="updateDeal?bookingCode=${deal.bookingCode}">Update</a></td>
				<td><a href="removeDeal?bookingCode=${deal.bookingCode}">[X]</a></td>
			</tr>
		</jstl:forEach>
	</table>

	<br>
	<hr>
	<hr>
	<br>

	<a href="transporterHome">Home</a>

</body>
</html>