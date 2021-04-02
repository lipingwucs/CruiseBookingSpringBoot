<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

<%@ include file="header.jsp"%>

<div class="container">
	<h3 align='center'>Cruise Booking Delete</h3>
	<br>
	<h3 class="alert alert-danger" role="alert">${confirmMsg }</h3>
	<form class="form-horizontal" method="post">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="reservationId"
					value="${booking.reservationId}"> <a class="btn btn-info"
					href="booking_list">Back to My Booking List</a> <input
					type="submit" class="btn btn-success"
					value="confirm to cancel this booking">
			</div>
		</div>
	</form>
</div>
<%@ include file="footer.jsp"%>
