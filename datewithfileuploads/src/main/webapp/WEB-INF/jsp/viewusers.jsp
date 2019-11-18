
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

<h3>User Details</h3>
<hr size="4" color="gray"/>


 <c:forEach items="${userlist}" var="user">
      <div>
            <b>User Name:<c:out value="${user.username}"/></b><br>
            <c:out value="${user.firstname}"/> &nbsp;<c:out value="${user.lastname}"/><br>  
            Email:<c:out value="${user.email}"/><br>  
            Contact No:<c:out value="${user.phone}"/> <br>
            <img src="${pageContext.request.contextPath}/download/image.do?fileName=${user.image}" height="100" width="100"><br>
            <a target="_self" href="${pageContext.request.contextPath}/download/pdf.do?fileName=${user.dob_fname}">Download Date Of Birth Document</a>
            <a target="_self" href="${pageContext.request.contextPath}/download/pdf.do?fileName=${user.address_fname}">Download Address Document</a>
            <a target="_self" href="${pageContext.request.contextPath}/download/image.do?fileName=${user.image}">Download Image</a>
            <hr>  
            
            
       </div>
    </c:forEach> 
</div>
</div>

<div class="footer">
  <p>Footer</p>
</div>


</body>
</html>