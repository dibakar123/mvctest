<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/mycss.css">

<title>Login</title>
</head>
<body>
<div class="header">
  <h1>Header</h1>
  <p>Resize the browser window to see the responsive effect.</p>
</div>

<div class="topnav">
<%@include file="menu.html" %>
</div>

<div class="row">
<div class="column side1">
<img src="img/pic1.jpg" height="30%" width="30%">
<img src="img/pic2.jpg" height="30%" width="30%">
<br>
<img src="img/pic3.jpg" height="30%" width="30%">
<img src="img/pic4.jpg" height="30%" width="30%">
</div>

<div class="column side2">
    <center>Login For User</center>
	<form id="loginForm" modelAttribute="user"  action="loginProcess.do"	method="post">
		<table align="center">
			<tr>
				<td>Username: </td>
				<td><input name="username" id="username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><input type="submit" id="login" name="login" value="Login"></td>
			</tr>

			<tr></tr>
			<tr>
				<td></td>
				<td><a href="home.jsp">Home</a></td>
			</tr>
		</table>
	</form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>
</div>
</div>
<div class="footer">
  <p>Footer</p>
</div>
</body>
</html>