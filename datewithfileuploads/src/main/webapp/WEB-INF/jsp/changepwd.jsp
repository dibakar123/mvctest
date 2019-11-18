
<%@page import="com.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/mycss.css">

<title>Welcome</title>
</head>
<body>
<div class="header">
  <h1>Header</h1>
  <p>Resize the browser window to see the responsive effect.</p>
</div>

<div class="topnav">
   <%@include file="usermenu.html" %>
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
    <center>Password Update</center>
	<form id="changepwd"  action="changepwd.do"	method="post">
		<table align="center">
			<tr>
				<td>Old Password: </td>
				<td><input type="password" name="opassword" id="opassword" /></td>
			</tr>
			<tr>
				<td>New Password:</td>
				<td><input type="password" name="npassword" id="npassword" /></td>
			</tr>
			<tr>
				<td>Re-type Password:</td>
				<td><input type="password" name="rpassword" id="rpassword" /></td>
			</tr>			
			<tr>
				<td></td>
				<td align="left"><input type="submit" id="login" name="Update_Password" value="Update Password"></td>
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