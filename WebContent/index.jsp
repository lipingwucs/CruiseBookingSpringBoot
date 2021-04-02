<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style type="text/css">
	<%@include file="../../bootstrap/css/bootstrap.css" %>
	<%@include file="../../bootstrap/css/bootstrap-theme.css" %>
	<%@include file="../../bootstrap/css/styles.css" %>
</style>
</head>
<body>

<div class="header" style="margin:0px;padding:0px;">
  <h1 align="center">Cruise Booking enterprise Application</h1> 
</div>
<div class="nav" >
   <nav class="navbar navbar-expand-lg navbar-dark default-color" align="center">
  
    <a href="home" class="btn btn-primary">     Home </a>    
	<a href="cruise_list" class="btn btn-info">      View Cruises </a>	
	<a href="aboutus" class="btn btn-info">      About Us </a>	
	
	<a href="signin" class="btn btn-warning">       Sign In</a> 
	<a href="signup" class="btn btn-warning">       Sign Up</a> 
	
   </nav>
</div>
<hr/>

<div class="container">
<h2>Sign In</h2>
	<form action="signin" method="post">
	<table class="table table-striped">
			<tr>
				<td>User Name:</td>
				<td><input name="userName" type="text" required></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="password" type="password" required></td>
			</tr>			
		</table>
		<!-- end of ADD table -->		
		<button type="submit" class="btn btn-primary">Sign In</button>
	</form>
	
	 <div class="text-center">Do not have an account? <a href="signup">Sign Up here</a></div>
</div>

<div class="footer">
 <p align="center">Copyright © 2020Winter COMP303 Liping Wu 300958061</p>
</div>
<br>

  
</body>
</html>