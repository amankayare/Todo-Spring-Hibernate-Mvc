<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spr" %> 
<%@page import="java.util.Date"%>
 <%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Log-In</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <!-- Bootstrap CSS -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <script
      src="https://kit.fontawesome.com/0ff6456f25.js"
      crossorigin="anonymous"
    ></script>
    <style>
      body {
        background-image: url("background.jpg");
        background-repeat: no-repeat;
        background-size: cover;
      }

      .img {
        width: 50vh;
        height: 50vh;
        margin-left: 16%;
        margin-top: 40%;
      }

      #icn {
        font-size: 25px;
        margin: 19px;
        color: white;
        cursor: pointer;
      }

      .mobileShow {
        display: inline;
      }

      /* Smartphone Portrait and Landscape */
      @media only screen and (min-device-width: 320px) and (max-device-width: 580px) {
        .mobileShow {
          display: inline;
        }
      }

      .mobileHide {
        display: inline;
      }

      /* Smartphone Portrait and Landscape */
      @media only screen and (min-device-width: 320px) and (max-device-width: 580px) {
        .mobileHide {
          display: none;
        }
      }
    </style>
  </head>
<body>
	<nav
      class="navbar navbar-expand fixed-top text-light navbar-dark bg-dark justify-content-between"
    >
      <div class="nav navbar-nav">
        <!--<a class="nav-item nav-link active" href="#">
          <i class="fa fa-home" aria-hidden="true"></i> Home</a
        >-->
      </div>
      <div class="nav navbar-nav" style="font-family: cursive; font-size: 25px">
        <b> Grocery Management System </b>
      </div>
     	
      <div class="nav navbar-nav">
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row" style="height: 100vh">
        <!--NAV-BAR-->

        <!--MIDDLE-COLOUM-->
        <div class="col-3 bg-inf1o mobileHide">
          
        </div>
        <div
          class="col-md-6 align-items-center justify-content-center mobileShow"
        >
				<div
            class="row d-flex align-items-center justify-content-center"
            style="height: 20vh"
          ></div>
          <div class="row shadow ml-0 mr-5">
            <div
              class="col-4 text-dark d-flex align-items-center justify-content-center"
              style="
                height: 50vh;
                font-family: cursive;
                background-color: #00cba9;
              "
            >
              Forgot Password
            </div>
            <div
              class="col-8 d-flex align-items-center justify-content-center"
              style="height: 50vh; background: whitesmoke"
            >
              <form action="forgotPassword.app" method="post">
                <div class="ml-5 form-group display-3">
                  <i class="fas fa-user-friends"></i>
                </div>
                <div class="form-group">
              
              	<div>${msg}</div>
                
                  <input class="form-control" placeholder="Enter Email" name="email"/>
                </div>
                <div class="form-group">
                  <input
                    type="submit"
                    class="rounded-pill form-control btn btn-info"
                    name="" value="Send Password"
                    placeholder="User-name"
                  />
                </div>
              </form>
            </div>
          </div>
        </div>

      </div>
    </div>
    <footer class="page-footer font-small bg-dark text-light fixed-bottom">
      <div class="footer-copyright text-center">Created by : Grocery Management System</div>
      <!-- Copyright -->
      <div class="footer-copyright text-center">
       © 2020 Copyright:
        <a href="https://mdbootstrap.com/"> CDAC</a>
      </div>
      <!-- Copyright -->
    </footer>
</body>
</html>