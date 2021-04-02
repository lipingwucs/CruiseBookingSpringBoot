<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

  <%@ include file = "header.jsp" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div class="container">
	<h3  align='center'>  Cruise Booking Details</h3>
		<br>
		<div class="container-contact">	
		<table class="table table-striped" >		
		<tr><td align='right'>Name:</td> <td align='left'>${fullName}</td></tr>
		<tr><td align='right'>Address:</td> <td align='left'> ${address}</td></tr>
		<tr><td align='right'>Cruise Name: </td> <td align='left'> <a href="cruise_detail?id=${cruise.cruiseId}">${cruise.cruiseName}</a></td></tr>
		<tr><td align='right'>Ship Name: </td> <td align='left'> ${cruise.shipName}</td></tr>
		<tr><td align='right'>Destination:  </td> <td align='left'> ${cruise.destination}</td></tr>
		<tr><td align='right'>Start Date:  </td> <td align='left'>  <fmt:formatDate value="${cruise.startDate}" pattern="yyyy-MM-dd"/></td></tr>
		<tr><td align='right'>End Date: </td> <td align='left'>  <fmt:formatDate value="${cruise.endDate}" pattern="yyyy-MM-dd"/></td></tr>
		<tr><td align='right'>Stateroom Type: </td> <td align='left'> ${stateroomType}</td></tr>
		<tr><td align='right'>Total Guests: </td> <td align='left'> ${totalGuests}</td></tr>
		<tr><td align='right'>Stateroom Type: </td> <td align='left'> ${stateroomType}</td></tr>
		<tr><td align='right'>Base Price:  </td> <td align='left'> CAD <fmt:formatNumber type="currency" value="${cruise.basePrice}" /></td></tr>
		</table>
		</div>
	
		<div class="container_invoice">
		<br>
		<br>
			<h3 align='center'>Invoice Details</h3>
			<h4 align='right' > ${stateroomType} Stateroom   </h4>			
			<table class="table table-striped" >		
			<tr><td align='right'>Unit Price:</td> <td align='right'>CAD <fmt:formatNumber type="currency" value="${cruise.basePrice}" /></td></tr>
			<tr><td align='right'>No of Guests</td> <td align='right'>${totalGuests}</td></tr>			
			<tr><td align='right'>price (total)</td> <td align='right'>CAD <fmt:formatNumber type="currency" value="${booking.price}" /></td></tr>		
	     	<tr><td align='right'>Taxes and Fees(total)</td> <td align='right'>CAD <fmt:formatNumber type="currency" value="${totalTax}" /> </td></tr>
			<tr><td align='right'>Cruise Trip Total Cost</td> <td align='right'>CAD <fmt:formatNumber type="currency" value="${totalCost}" /></td></tr>			
			</table>
		</div>
		<br>
		<h2 class="italic"  align='center'>  ${thanksMsg}  </h2>	
		<br>
		<br>
		
	</div>


  <%@ include file = "footer.jsp" %>
