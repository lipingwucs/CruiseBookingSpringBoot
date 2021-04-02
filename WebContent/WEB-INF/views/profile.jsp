<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->
<%@ include file="header.jsp"%>
<div class="container">
	<h1 align="center">My Profile</h1>


	<c:if test="${not empty errorMsg}">
		<h3 class="alert alert-danger" role="alert">
			<c:out value="${errorMsg}" />
		</h3>
	</c:if>

	<form:form class="form-horizontal" action="profile" method="post"
		modelAttribute="passenger">
		<form:hidden path="passengerId" />
		<div class="form-group">
			<form:label class="control-label col-sm-2" path="userName">User name:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="userName" readonly="true" />
			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="firstName">First Name:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="firstName" />

			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="lastName">Last Name:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="lastName" />
			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="email">Email:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="email" type="email" />
			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="telephone">Telephone:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="telephone" />
			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="address">Address:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="address" />
			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="city">City:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="city" />
			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="postalCode">Postal Code:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="postalCode" />

			</div>
		</div>

		<div class="form-group">
			<form:label class="control-label col-sm-2" path="country">Country:</form:label>
			<div class="col-sm-10">
				<form:input class="form-control" path="country" />
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-warning">Update My
					Profile</button>

			</div>
		</div>
	</form:form>

</div>


<%@ include file="footer.jsp"%>

