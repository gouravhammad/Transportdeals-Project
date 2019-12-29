<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<!DOCTYPE html>
<html>
<body>
  <h1>EMAIL VERIFICATION</h1><br>
  <h3>An OTP Was sent to ${email}  Please enter the OTP to verify your account!!</h3>
  <form action="checkOTPTransporter">
     <pre>
     Enter OTP :  <input type="number" name="OTPCode" /> <br>
    			<input type="submit" value="SEND" />
     </pre>
  </form>

</body>
</html>