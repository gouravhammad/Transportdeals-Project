<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
  <h1 style="Text-align:center; font-family: century gothic; color: green">SEARCH DEAL BY CITY RANGE FORM</h1>
  
  
	  <sf:form modelAttribute="booking" action="saveSearchDealCityRange">
	     <pre>
		  Source City      : <sf:select path="sourceCity" items="${cities}"/> <br>
		  Destination City : <sf:select path="destinationCity" items="${cities}"/> <br>
		  <input type="submit" value="SEARCH">
	     </pre>  
	  </sf:form>
	  
	  <br><hr><hr><br>
	  
  <a href="transporterHome">Home</a>
  
</body>
</html>