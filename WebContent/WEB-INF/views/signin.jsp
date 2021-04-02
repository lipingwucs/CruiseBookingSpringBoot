<!-- 
COMP303 Assignment02 
author:Liping Wu 
StudentId: 300958061 
Date: 03/05/2020 
 -->

 <%@ include file = "header.jsp" %>
 <div class="container">
<h1 align="center">Sign In</h1>  

<c:if test = "${not empty errorMsg}">
<h3 class="alert alert-danger" role="alert" ><c:out value="${errorMsg}"/></h3>
</c:if>
<div class="text-center">
<h4>Do not have an account? <a href="./signup">Sign Up here</a><br><br></h4>
</div>

<form:form class="form-horizontal" action="signin" method="post" modelAttribute="passenger">

  <div class="form-group">
    <label class="control-label col-sm-2" for="userName">User name:</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" path="userName"  
      placeholder="Enter user name" required="true"/>
    </div>
    
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="password">Password:</label>
    <div class="col-sm-10">
      <form:input type="password" class="form-control" path="password" placeholder="Enter your password"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="reset" class="btn btn-warning">Cancel</button>
      <button type="submit" class="btn btn-success">Sign In</button>     
    </div>  
  </div>
</form:form>

</div>

 <%@ include file = "footer.jsp" %>
	
