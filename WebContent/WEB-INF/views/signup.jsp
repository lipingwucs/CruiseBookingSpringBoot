<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

 <%@ include file = "header.jsp" %>
 <div class="container">
<h1 align="center">Sign Up</h1>  

<c:if test = "${not empty errorMsg}">
<h3 class="alert alert-danger pre_line" role="alert" ><c:out value="${errorMsg}"/></h3>
</c:if>

<div class="text-center">
<h4>Already have an account? <a href="./signin">Sign in here</a> <br><br></h4>
</div>

<form:form class="form-horizontal" action="signup" method="post" modelAttribute="passenger">
  <div class="form-group">
    <label class="control-label col-sm-2" for="userName">User name:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="userName" placeholder="Enter a new userName"/>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="password">Password:</label>
    <div class="col-sm-10">
      <form:input type="password" class="form-control" path="password" placeholder="Enter password"/>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="password2">Verify Password:</label>
    <div class="col-sm-10">
      <!-- password2 is not an attribute of Passenger, can't use form tag here -->
      <input type="password" class="form-control" name="password2" placeholder="verify your password"/>
    
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="firstName">First Name:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="firstName" />
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="lastName">Last Name:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="lastName" />
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="email">Email:</label>
    <div class="col-sm-10">
      <form:input type="email" class="form-control" path="email" />
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="telephone">Telephone:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="telephone" />
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="address">Address:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="address" />
    </div>
  </div>
  
       <div class="form-group">
    <label class="control-label col-sm-2" for="city">City:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="city" />
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="postalCode">Postal Code:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="postalCode" />
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="country">Country:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="country" />
    </div>
  </div>  
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="reset" class="btn btn-warning">Clear</button>
      <button type="submit" class="btn btn-success">Sign Up</button>
      
    </div>
  </div>
</form:form>


</div>


 <%@ include file = "footer.jsp" %>
	
