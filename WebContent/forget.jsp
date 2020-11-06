<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %> 


<!DOCTYPE html>
<html>
<head>
	<title>Forget password</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" type="text/css" href="css/mystyle.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6 col-md-6 d-none d-md-block image-container"></div>

			<div class="col-lg-6 col-md-6 form-container">
				<div class="col-lg-8 col-md-12 col-sm-9 col-xs-12 form-box">
					<div class="text-center logo mb-3">
						<img src="image/logo.png" width="150px">
					</div>
					<div class=" reset-form d-block">
						<form class="reset-password-form" action="forgotPassword.app" method="post">
							<h4 class="mb-3">Reset your password</h4>
							<p class="mb-3 text-white">Please enter your email address</p>
							<div class="form-input">
								<span><i class="fa fa-envelope"></i></span>
								<input type="email" name="email" placeholder="Email Address" required>
							</div>
							<div class="mb-3">
								<input class="btn btn-danger" type="submit" value="Send Password">
								
								<c:if test="condition"></c:if>
					<c:choose>
    					<c:when test="${msg!=null}">
							<div class="alert alert-danger" role="alert">${msg}</div><br/>
							<a href="userLogin.app" class="btn btn-primary">Login here</a>
							
   					 </c:when>    
			   		 <c:otherwise>
  					     <br />
   					 </c:otherwise>
					 </c:choose>
								
								
								
								
							</div>
						</form>
					</div>
			
				</div>
			</div>
		</div>
	</div>

	
</body>
</html>