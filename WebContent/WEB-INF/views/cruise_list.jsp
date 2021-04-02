<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->
<%@ include file="header.jsp"%>
<div class="container">
	<h1>Cruise List</h1>
<c:if test = "${not empty errorMsg}">
<h3 class="alert alert-danger pre_line"  role="alert" ><c:out value="${errorMsg}"/></h3>
</c:if>

	<form>
		<!-- search box part-->
		<div class="row">
			<div class="col-lg-4 col-lg-offset-4">
				<input type="search" id="searchtext" class="form-control"
					align="left" name="searchtext" value="${searchtext}">
			</div>
			<div>
				<button type="search" class="btn btn-info">Search</button> &nbsp;&nbsp;&nbsp;&nbsp;   
		<c:if test="${sessionScope.currentUser == 'admin'}">
			<a href="cruise_add" class="btn btn-success">Add A New Cruise</a>
		</c:if>
				<br>
			</div>
		</div>
		<!-- search box part-->
		<form>

			<table class="table table-striped table-hover">
				<tr>
					<th>Cruise Id</th>
					<th>Cruise Name</th>
					<th>Ship Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Destination</th>
					<th>Base Price</th>
				<c:if test="${sessionScope.currentUser.equals('admin')}">
					<th>UPDATE</th>
					<th>DELETE</th>
				</c:if>
					<th>DETAILS</th>
				</tr>

				<c:forEach var="cruise" items="${cruiseList}">
					<tr>
						<td>${cruise.cruiseId}</td>
						<td>${cruise.cruiseName}</td>
						<td>${cruise.shipName}</td>
						<td><fmt:formatDate value="${cruise.startDate}" type="date"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${cruise.endDate}" type="date"
								pattern="yyyy-MM-dd" /></td>
						<td>${cruise.destination}</td>
						<td>CAD <fmt:formatNumber type="currency" value="${cruise.basePrice}" /> </td>
					<c:if test="${sessionScope.currentUser == 'admin'}">
						<td><a href="cruise_update?id=${cruise.cruiseId}"
							class="btn btn-warning">Update</a></td>
						<td><a href="cruise_delete?id=${cruise.cruiseId}"
							class="btn btn-danger">Delete</a></td>
					</c:if>
						<td><a href="cruise_detail?id=${cruise.cruiseId}"
							class="btn btn-info">Details</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<div align = "center">
		<c:if test="${sessionScope.currentUser == 'admin'}">
			<a href="cruise_add" class="btn btn-success">Add A New Cruise</a>
		</c:if>
			<br><br>
		</div>
</div>
<%@ include file="footer.jsp"%>

