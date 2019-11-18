<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/mycss.css">

<title>Registration</title>
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
<%
String name=(String)request.getAttribute("username");%>
<div class="column side2">
    <center>Upload Files for User Registration</center>
	<form id="uploadfiles" modelAttribute="userRegForm" action="uploadfiles.do" method="post" enctype="multipart/form-data">

		<table align="center">
			<tr>
				<td><label >Username</label></td>
				<td><input type="text" name="username" id="username" value='<%=name%>' readonly="readonly"></td>
			</tr>
	
			<tr>
				<td><label >Address Proof</label></td>
				<td><input type="file" name="file" id="address_fname"></td>
			</tr>
			
			<tr>
				<td><label >Date of birth Proof</label></td>
				<td><input type="file" name="file" id="dob_fname"></td>
			</tr>
			<tr>
				<td></td>
				<td><button id="register" name="register">Upload</button></td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan="2">
				 <b>${status}</b>
				<a href="index.jsp">Home</a></td>
			</tr>
		</table>
	</form>
</div>
</div>
<div class="footer">
  <p>Footer</p>
</div>

</body>
</html>