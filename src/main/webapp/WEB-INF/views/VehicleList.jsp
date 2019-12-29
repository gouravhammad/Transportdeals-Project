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
	<h1
		style="Text-align: center; font-family: century gothic; color: green">LIST
		OF ALL VEHICLE</h1>

	<table border="2">
		<tr>
			<th>REG-NO</th>
			<th>BRAND</th>
			<th>CAPICITY</th>
			<th>V-TYPE</th>
			<th>TRANSPORTER</th>
			<th>VEHICLE-PICTURE</th>
			<th>RC-BOOK</th>
			<th>UPDATE-VEHICLE</th>
			<th>REMOVE VEHICLE</th>
		</tr>
		<jstl:forEach var="vehicles" items="${vehicles}">
			<tr>
				<td>${vehicles.regNo}</td>
				<td>${vehicles.brand}</td>
				<td>${vehicles.capacity}</td>
				<td>${vehicles.vehicleType}</td>
				<td>${vehicles.transporter.transporterName}</td>
				<td><img src="LoadVehiclePic?regNo=${vehicles.regNo}" height="90" width="150" /></td>
				<td><a href="LoadRCBook?regNo=${vehicles.regNo}">view</a></td>
				<td><a href="updateVehicle?regNo=${vehicles.regNo}">Update</a></td>
				<td><a href="removeVehicle?regNo=${vehicles.regNo}">[X]</a></td>
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