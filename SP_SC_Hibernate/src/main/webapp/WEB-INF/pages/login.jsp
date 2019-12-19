<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>FlowOfMoney Login Form</title>
<head> 
  <link href="./resources/css/styleForLogin.css" rel="Stylesheet" type="text/css" />
 	
  <script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
  
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

<script type="text/javascript">

(function($,W,D)
{
    var JQUERY4U = {};

    JQUERY4U.UTIL =
    {
        setupFormValidation: function()
        {
            //form validation rules
            $(".login").validate({
                rules: {
                    
                    username: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    agree: "required"
                },
                messages: {
                    password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 5 characters long"
                    },
                    username: "Please enter a valid email address",
                    agree: "Please accept our policy"
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        }
    }

    //when the dom has loaded setup form validation rules
    $(D).ready(function($) {
        JQUERY4U.UTIL.setupFormValidation();
    });

})(jQuery, window, document);
</script>
<style type="text/css">
label{
color:white;
}
</style>
</head>
<body>
 <div class="headerView">
	<div class="first">
		<strong>VK Technologies Limited.</strong>
	</div>
	
</div>


<div class="formError">
<c:if test="${message eq 'logoutsuccess'}">
<p>Successfully logout.</p>
</c:if>

<c:if test="${message eq 'accessDeniedError'}">
	<p>Dear <strong>user</strong>, You are not authorized to access this page.</p>
</c:if>

<c:if test="${message eq 'accessDeniedErrorSession'}">
	<p>Dear <strong>user</strong>, You are session timeout.</p>
</c:if>

<c:if test="${message eq 'sessionDeniedError'}">
	<p>Session invalid please login again.</p>
</c:if>

</div>
<div class="formContaier">
  <form:form method="post" action="j_spring_security_check" modelAttribute="users" class="login" novalidate="novalidate">
    <h1>Flow Of Money</h1>
    <form:input  path="username" class="login-input" id="username" />
    <form:password  path="password" class="login-input"  id="password" />
    <input type="submit" value="Login" class="login-submit">
    <p class="login-help"><a href="./Register" target="_top">Register?</a> | <a href="#">Forgot password?</a></p>    
  </form:form>
</div>
  <section class="about">    
    <p class="about-author">
      &copy; 2016 <a href="http://thibaut.me" target="_blank">Vk Technologies </a> -
      <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br>
      Original PSD by <a href="http://dribbble.com/shots/808325-Facebook-Login-Freebie" target="_blank">Alex Montague</a>
    </p>
  </section>
</body>
</html>
