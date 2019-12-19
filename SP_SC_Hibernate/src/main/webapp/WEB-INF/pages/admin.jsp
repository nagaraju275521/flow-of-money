<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Admin Profile Page | Beingjavaguys.com</title>
</head>
<body>
	
		<br /> <br /> <br />
		<h1>Admin profile page !!!ggg</h1>
		<c:url var="logoutUrl" value="j_spring_security_logout" />
		
		<form action="j_spring_security_logout" method="post">
			<input type="submit" value="Log out" />
			
			 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<p>${_csrf.parameterName}</p><br/>
		<p>${_csrf.token}</p>
	
	<a href="<c:url value="j_spring_security_logout" />" > Logout</a>

<div>
        <sec:authorize access="hasRole('ROLE_USER')">
            <label><a href="#">Edit this page</a> | This part is visible only to ADMIN</label>
        </sec:authorize>
    </div>
 
    <br/>
    <div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <label><a href="#">Start backup</a> | This part is visible only to one who is both ADMIN & DBA</label>
        </sec:authorize>
    </div>
</body>
</html>