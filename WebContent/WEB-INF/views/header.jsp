<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${not empty Title ? $Title : ""} - Cruise Booking enterprise Application </title>
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
	
<c:choose>
	<c:when test = "${not empty sessionScope.currentUser}">	
		<a href="booking_list" class="btn btn-info">       My Booking </a>
		<a href="profile" class="btn btn-info">       My Profile</a> 
		<a href="logout"  class="btn btn-warning"> Logout</a>
	</c:when>
	<c:when test = "${empty sessionScope.currentUser}">	
		<a href="signin" class="btn btn-success">       Sign In</a> 
		<a href="signup" class="btn btn-success">       Sign Up</a> 
	</c:when>
</c:choose>


<label>
	<c:if test="${not empty sessionScope.currentUser}">
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; hello ${sessionScope.currentUser} !</div>
		</c:if>
</label>

   </nav>
</div>
<hr/>
<div ></div>

<c:if test = "${not empty tempMsg}">
<h3 align="center" style="color: green; background-color: lightyellow" class="pre_line"><c:out value="${sessionScope.tempMsg}"/></h3>
</c:if>
<c:remove var="tempMsg" scope="session" />
