<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> 
  <h1 style="Text-align:center; font-family: century gothic; color: green">ADMIN Login</h1>
  
    <sf:form action="saveAdmin" modelAttribute="admin">
       <pre>
      	 Admin Name      <sf:input type="email" path="email"/>
      	 Admin Password  <sf:input type="password" path="password"/>
                         <input type="submit" value="Submit"/>
       </pre>
     </sf:form>
  
  <a href="home">Go to Home</a>
</body>
</html>
