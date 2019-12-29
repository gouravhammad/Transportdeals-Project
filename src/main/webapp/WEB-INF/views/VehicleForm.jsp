<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
  <h1 style="Text-align:center; font-family: century gothic; color: green">VEHICLE ENTRY</h1>
  
	  <sf:form modelAttribute="vehicle" action="saveVehicle" method="POST" enctype="multipart/form-data" >
	     <pre>
		  Registration No   : <sf:input type="text" path="regNo"/> <br>
		  Brand      	    : <sf:select path="brand" items="${brands}"/> <br>
		  Capacity     	    : <sf:input type="number" path="capacity" /> <br>
		  Vehicle Type      : <sf:select path="vehicleType" items="${vTypes}" /> <br>
		  Vehicle Picture   : <input type="file" name="vehiclePic" /> <br>
		  RC-Book (PDF)     : <input type="file" name="rcbook" /> <br>
		  <input type="submit" value="submit">
	     </pre>  
	  </sf:form>
	  
	  <br><hr><hr><br>
	  
  <a href="transporterHome">Home</a>
  
</body>
</html>