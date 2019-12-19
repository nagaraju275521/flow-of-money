<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>AccessDenied page</title>
    <script type="text/javascript">
   // window.top.location.href = "./logout?AccessDenied=AccessDeniedError";
    </script>
</head>
<body>
    Dear <strong>${user}</strong>, You are not authorized to access this page.
    
   <p> ${session_invalid}</p>
   
   <c:if test="${user ne 'anonymousUser'}">
   <p>My salary is: <c:out value="${user}"/></p>
   
  <script> window.top.location.href = "./logout?AccessDenied=AccessDeniedError";</script>
</c:if>
   
   <c:if test="${session_invalid eq 'Session_Invalid_Error'}">
   
   <p>My salary is: <c:out value="${session_invalid}"/><p>
   <script>window.top.location.href = "./logout?AccessDenied=sessionDeniedError";</script>
   
</c:if>
    
    <c:if test="${concurency_invalid eq 'Concurency_Invalid_Error'}">
   <p>My salary is: <c:out value="${concurency_invalid}"/><p>
</c:if>

    <a href="<c:url value="/logout?AccessDenied=AccessDeniedError" />" target="_top">Logout</a>
</body>
</html>