<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

<%@ include file="header.jsp"%>

<div class="container">
	<h3 align='center'>Booking this cruise - Choose stateroom type</h3>
	<br>
	<c:if test="${not empty errorMsg}">
		<h3 class="alert alert-danger pre_line" role="alert">
			<c:out value="${errorMsg}" />
		</h3>
	</c:if>
	<div class="container_contact">
		<form:form class="form-horizontal" method="post"
			modelAttribute="booking">
			<table class="table table-striped">
				<tr>
					<td align='right'>Cruise Name:</td>
					<td align='right'>${booking.cruise.cruiseName}</td>
				</tr>
				<tr>
					<td align='right'>Ship Name</td>
					<td align='right'>${booking.cruise.shipName}</td>
				</tr>
				<tr>
					<td align='right'>Destination</td>
					<td align='right'>${booking.cruise.destination}</td>
				</tr>
				<tr>
					<td align='right'>Start Date</td>
					<td align='right'><fmt:formatDate value="${booking.cruise.startDate}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td align='right'>End Date</td>
					<td align='right'><fmt:formatDate value="${booking.cruise.endDate}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td align='right'>Base Price</td>
					<td align='right'>CAD <fmt:formatNumber type="currency"
							value="${booking.cruise.basePrice}" /></td>
				</tr>
			</table>

			<table class="table" bgcolor="lightblue">
				<tr>
					<td align='right'>How many Guests?</td>
					<td><form:input path="totalGuests" type="number" required="true" /></td>
				</tr>

				<tr>
					<td align='right'>Stateroom Type:</td>
					<td><form:select path="stateroomType" required="true">
							<form:option value="" label="--- Select ---" />
							<form:options items="${stateRoomList}" />
						</form:select></td>
				</tr>
			</table>
			<div>
				<a class="btn btn-info" href="cruise_list">Back to Cruise List</a> <input
					type="submit" class="btn btn-success" value="Next - Payment">
			</div>
		</form:form>
	</div>
</div>

<%@ include file="footer.jsp"%>
