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
<title>Error</title>
<style type="text/css">
	<%@include file="../../bootstrap/css/bootstrap.css" %>
	<%@include file="../../bootstrap/css/bootstrap-theme.css" %>
	<%@include file="../../bootstrap/css/styles.css" %>
</style>
</head>
<body>
<%@ include file = "header.jsp" %>
<div class="container">

<h1>Error </h1>  
<h3 class="alert alert-danger" role="alert" >${errorMsg }</h3>


</div>
	
	  <%@ include file = "footer.jsp" %>

</body>
</html>
