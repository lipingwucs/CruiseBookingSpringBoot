<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

<%@ include file="header.jsp"%>

<div class="container">
	<h3 align='center'>Booking this cruise - Payment</h3>
	<br>
	<c:if test="${not empty errorMsg}">
		<h3 class="alert alert-danger pre_line" role="alert">
			<c:out value="${errorMsg}" />
		</h3>
	</c:if>
	<form:form class="form-horizontal" action="booking_add_step2"
		method="post" modelAttribute="payment">
		<table class="table table-striped">
			<tr>
				<input type="hidden" name="cruiseId" value="${booking.cruise.cruiseId}" />
			</tr>
			<tr>
				<td scope="row">Cruise Name:</td>
				<td><a href="cruise_detail?id=${booking.cruise.cruiseId}">${booking.cruise.cruiseName}</a></td>
			</tr>
			<tr>
				<td scope="row">Destination:</td>
				<td>${booking.cruise.destination}</td>
			</tr>
			<tr>
				<td scope="row">Start Date:</td>
				<td><fmt:formatDate value="${booking.cruise.startDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td scope="row">End Date:</td>
				<td><fmt:formatDate value="${booking.cruise.endDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td scope="row">Total Guests:</td>
				<td>${booking.totalGuests}</td>
			</tr>
			<tr>
				<td scope="row">Stateroom Type</td>
				<td>${booking.stateroomType}</td>
			</tr>
			<tr>
				<td scope="row">Total Price(before tax)</td>
				<td><fmt:formatNumber type="currency" value="${totalPrice}" /></td>
			</tr>
			<tr>
				<td scope="row">Taxes and Fees(total)</td>
				<td><fmt:formatNumber type="currency" value="${totalTax}" /></td>
			</tr>
			<tr>
				<td scope="row">Cruise Trip Total Cost</td>
				<td><fmt:formatNumber type="currency" value="${totalCost}" /></td>
			</tr>

		</table>
		<h4>
			<br> <br>Please fill your payment to finish the booking:
		</h4>

		<table class="table table-hover">
			<tr>
				<td scope="row">Card Type</td>
				<td><form:radiobutton path="paymentType" value="debit" />Debit 
				    <form:radiobutton path="paymentType" value="visa" />Visa
				    <form:radiobutton path="paymentType" value="master" />Master
				   	 </td>
			</tr>
			<tr>
				<td scope="row">Credit card number</td>
				<td><form:input  path="cardNumber" 	placeholder="Enter your credit number" required="true" /></td>
			</tr>
			<tr>
				<td scope="row">Name on Credit Card</td>
				<td><form:input path="ownerName" placeholder="Enter your name" required="true"/></td>
			</tr>
			<tr>
				<td scope="row">cvc:</td>
				<td><form:input type="number" path="cvc" required="true"/></td>
			</tr>
			<tr>
				<td scope="row">expire date</td>
				<td><form:select path="expireMonth" required="true">
						<form:option value="" label="--- Month ---" />
						<form:options items="${expireMonthList}" />
					</form:select>
					<form:select path="expireYear" required="true">
						<form:option value="" label="--- Year ---" />
						<form:options items="${expireYearList}" />
					</form:select>
				</td>
			</tr>

			<tr>
				<td scope="row">Billing address:</td>
				<td><form:input path="billingAddress" required="true" /></td>
			</tr>
		</table>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="cruiseId" value="${booking.cruise.cruiseId}">
				<input type="hidden" name="totalGuests" value="${booking.totalGuests}">
				<input type="hidden" name="stateroomType" value="${booking.stateroomType}">
				<a class="btn btn-info" href="cruise_list">Back to Cruise List</a> <input
					type="submit" class="btn btn-success" value="Booking Confirm">

			</div>
		</div>
	</form:form>
</div>
<div class="container_invoice"></div>

<%@ include file="footer.jsp"%>
