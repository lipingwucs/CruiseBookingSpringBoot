<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

<%@ include file="header.jsp"%>
<div class="container">
	<h1>Booking List</h1>
<c:if test = "${not empty errorMsg}">
<h3 class="alert alert-danger pre_line"  role="alert" ><c:out value="${errorMsg}"/></h3>
</c:if>

	<!-- form for list start-->
	<form>
		<!-- search box part-->
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<input type="search" id="searchtext" class="form-control"
					align="left" name="searchtext" value="${searchtext} ">
			</div>
			<div>
				<button type="search" class="btn btn-info">Search</button>
				<br>
			</div>
		</div>
		<!-- search box part-->	

		<table class="table table-striped">
			<tr>
				<td>Booking Id</td>
				<td>Cruise Name</td>
				<td>Total Guests</td>
				<td>Total Price</td>
				<td>Moidfy</td>
				<td>Cancel</td>
				<td>DETAILS</td>
			</tr>
			<c:forEach var="booking" items="${bookingList}">
				<tr>
					<td>${booking.reservationId}</td>
					<td><a href="cruise_detail?id=${booking.cruise.cruiseId}">${booking.cruise.cruiseName}</a></td>
					<td>${booking.totalGuests}</td>
					<td>CAD <fmt:formatNumber type="currency" value="${booking.price}" /></td>
					<td><a href="booking_update?id=${booking.reservationId}"
						class="btn btn-warning">Modify</a></td>
					<td><a href="booking_delete?id=${booking.reservationId}"
						class="btn btn-danger">Cancel</a></td>
					<td><a href="booking_detail?id=${booking.reservationId}"
						class="btn btn-info">DETAILS</a></td>
			</c:forEach>
		</table>
	</form>
</div>


<%@ include file="footer.jsp"%>

