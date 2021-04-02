<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->
<%@ include file="header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<h3 align='center'>Cruise Details</h3>
	<br>
	<div class="container_contact">		
		<div align="right">
			<a class="btn btn-success" href="booking_add_step1?cruiseId=${cruise.cruiseId}">Book this Cruise</a> <br>
			<br>
		</div>
		<table class="table table-striped">		
			<tr>
				<td align='right'>Cruise Name:</td>
				<td align='right'>${cruise.cruiseName}</td>
			</tr>
			<tr>
				<td align='right'>Ship Name</td>
				<td align='right'>${cruise.shipName}</td>
			</tr>
			<tr>
				<td align='right'>Destination</td>
				<td align='right'>${cruise.destination}</td>
			</tr>
			<tr>
				<td align='right'>Start Date</td>
				<td align='right'><fmt:formatDate value="${cruise.startDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td align='right'>End Date</td>
				<td align='right'><fmt:formatDate value="${cruise.endDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td align='right'>Base Price</td>
				<td align='right'>CAD <fmt:formatNumber type="currency" value="${cruise.basePrice}" /></td>
			</tr>
		</table>
		<div align="right">
		<a class="btn btn-info" href="cruise_list" >Back to Cruise List</a> 	
		<a class="btn btn-success" href="booking_add_step1?cruiseId=${cruise.cruiseId}" >Book this Cruise</a> <br>
		<br>
	</div>
	</div>
	
</div>


<%@ include file="footer.jsp"%>
