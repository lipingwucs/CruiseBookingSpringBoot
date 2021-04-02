<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->
  <%@ include file = "header.jsp" %>

<div class="container">
	<h2>Please Update the Cruise here</h2>
<c:if test = "${not empty errorMsg}">
<h3 class="alert alert-danger pre_line"  role="alert" ><c:out value="${errorMsg}"/></h3>
</c:if>

<form:form class="well form-horizontal"  action="" method="post" id="cruise_form" modelAttribute="cruise">
<form:hidden path="cruiseId" />
		<table class="table table-striped">
		
			<tr>
				<td>Cruise Name:</td>
				<td><form:select path="cruiseName" class="form-control" items="${cruiseNameList}" />
				 </td>
			</tr>
			
			<tr>
				<td>Ship Name:</td>
				<td><form:select path="shipName" class="form-control" >
						<form:option value="" label="--- Select ---" />
						<form:options items="${shipNameList}" />
					</form:select>
					</td>
			</tr>
			
			
			<tr>
				<td>Start Date:</td>
				<td><form:input path="startDate" type="Date"  /> 
				 </td>						
			</tr>
			<tr>
				<td>End Date:</td>
				<td><form:input path="endDate" type="date"  /> </td>						
			</tr>
			
			<tr>
				<td>Destination:</td>
				<td><form:select path="destination" class="form-control" items="${destinationList}">
					</form:select></td>
			</tr>
				<tr>
				<td>Base Price:</td>
				<td><form:input path="basePrice" type="number" /></td>
			</tr>
			
			
		</table>
		
		<div align = "center">
			<button type="submit" class="btn btn-success">Update the Cruise</button>
			<br><br>
		</div>
				
	</form:form>
</div>


  <%@ include file = "footer.jsp" %>
