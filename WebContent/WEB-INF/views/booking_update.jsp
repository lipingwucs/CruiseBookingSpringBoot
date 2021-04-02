<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

  <%@ include file = "header.jsp" %>

	<div class="container">
	<h3 align='center'>  Update your booking </h3>
		<br>
<c:if test = "${not empty errorMsg}">
<h3 class="alert alert-danger pre_line"  role="alert" ><c:out value="${errorMsg}"/></h3>
</c:if>
<form:form class="form-horizontal" action="booking_update" method="post" modelAttribute="booking">
			<table class="table table-striped">
				<tr>
					<td scope="row">Cruise Name:</td>
					<td><a href="cruise_detail?id=${cruise.cruiseId}">${cruise.cruiseName}</a></td>
				</tr>
				<tr>
					<td scope="row">Total Guests:</td>
					<td><form:input type="number" path="totalGuests" value="${booking.totalGuests}"/></td>
				</tr>
				<tr>
					<td scope="row">Stateroom Type</td>
					<td><form:select path="stateroomType" required="true">
							<form:option value="" label="--- Select ---" />
							<form:options items="${stateRoomList}" />
						</form:select></td>
				</tr>						
				
			</table>
	<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="hidden" name="cruiseId" value="${cruise.cruiseId}">
    <input type="hidden" name="reservationId" value="${booking.reservationId}">
	<a class="btn btn-info" href="cruise_list">Back to Cruise List</a>
	<input type="submit" class="btn btn-warning" value="Update my booking">
     
    </div>  
  </div>
</form:form>



  <%@ include file = "footer.jsp" %>
