<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="todo" %>    
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" type="text/css" href="css/mystyle.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"></head>
<body>
<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6 col-md-6 d-none d-md-block image-container">
						<img src="image/bg.jpg" width="750px" align="center">
			</div>

			<div class="col-lg-6 col-md-6 form-container">
				<div class="col-lg-8 col-md-12 col-sm-9 col-xs-12 form-box text-center">
					<div class="logo mb-3">
						<img src="image/logo.png" width="150px">
					</div>
					<div class="heading mb-4">
						<h4>Create an account</h4>
					</div>
					<todo:form action="doSignup.app" method="post" commandName="user">
						<div class="form-input">
							<span><i class="fa fa-user"></i></span>
							<todo:input path="userFullName" placeholder="Full Name" />
						</div><font color="red" ><todo:errors path="userFullName" ></todo:errors></font>
                	
						<div class="form-input">
							<span><i class="fa fa-envelope"></i></span>
							<todo:input path="userEmail" type="email" placeholder="Email Address" />
						</div>
                    <font color="red" ><todo:errors path="userEmail" ></todo:errors></font>
				
						<div class="form-input">
							<span><i class="fa fa-lock"></i></span>
							<todo:input path="userPassword" type="password" placeholder="Password" />
						</div>
						<font color="red" ><todo:errors path="userPassword" ></todo:errors></font>
						
	
						<div class="text-left mb-3">
							<button type="submit" class="btn">Register</button>
						</div>
					
						<div style="color: #777">Already have an account
							<a href="userLogin.app" class="login-link">Login here</a>
						</div>
					</todo:form>

					<c:if test="condition"></c:if>
					<c:choose>
    					<c:when test="${UserExist!=null}">
							<div class="alert alert-danger" role="alert">${UserExist}</div><br />
   					 </c:when>    
			   		 <c:otherwise>
  					     <br />
   					 </c:otherwise>
					 </c:choose>                                       
				</div>
			</div>
		</div>
	</div>
</body>
</html>