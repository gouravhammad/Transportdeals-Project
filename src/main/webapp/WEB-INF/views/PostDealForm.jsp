<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
  <h1 style="Text-align:center; font-family: century gothic; color: green">POST A DEAL</h1>
  
  
	  <sf:form modelAttribute="booking" action="saveDeal">
	     <pre>
		  Vehicle Reg No   : <sf:select path="vehicle.regNo" items="${regNos}"/> <br>
		  Date             : <sf:input type="date" path="date" /> <br>
		  Fare             : <sf:input type="number" path="fare" /> <br>
		  Source City      : <sf:select path="sourceCity" items="${cities}"/> <br>
		  Destination City : <sf:select path="destinationCity" items="${cities}"/> <br>
		  <input type="submit" value="POST DEAL">
	     </pre>  
	  </sf:form>
	  
	  <br><hr><hr><br>
	  
  <a href="transporterHome">Home</a>
  
</body>
</html>