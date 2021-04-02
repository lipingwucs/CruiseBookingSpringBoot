<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

<%@ include file="header.jsp"%>

<div class="container">
	<h3 align='center'>User Homepage</h3>
	<br>
	
	<ul>
		<li><a href="cruise_list"> View Cruises </a></li>
		<c:if test="${not empty sessionScope.currentUser}">
			<li><a href="booking_list"> View My Booking List </a></li>
			<li><a href="profile"> View/Edit My Profile </a></li>
			<li><a href="logout"> Logout</a></li>
		</c:if>
		<c:if test="${empty sessionScope.currentUser}">
			<li><a href="signin"> Sign In </a></li>
			<li><a href="signup"> Sign Up </a></li>
		</c:if>

	</ul>

	<br>

</div>

<%@ include file="footer.jsp"%>

