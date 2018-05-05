<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
  <link rel="shortcut icon" href="${baseurl }/abhee/images/icon.png" type="icon"/>
</head>
<body>

hi leela

<a href="userlogin">userlogin</a>

 <form action="/test" method="POST">
 loginid  <input type="text" name="lname" id="lanme">
 password <input type="password" name="lpassword" id="lpassword">
      <input type="submit" name="submit" >
 
 </form>



<%-- <form:form action="/summary" method="POST" modelAttribute="adminForm">
<div class="row">
 <div>name</div> 
  <div><form:input path ="name" /> </div>

</div>

<div class="row">
 <div>password</div>
  <div><form:password  path ="password" /> </div>

</div>
<input type="submit" value="Submit"/>
</form:form>
 --%>
</body>
</html>