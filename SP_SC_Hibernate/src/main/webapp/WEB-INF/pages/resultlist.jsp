<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 10/15/2015
  Time: 1:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
<div align="center">
  <h1>Contact List</h1>
  <table border="1">
    <th>No</th>
    <th>Username</th>
    <th>Email</th>

    <c:forEach var="user" items="${userList}" varStatus="status">
      <tr>

        <td>${user.first_Name}</td>
        <td>${user.password}</td>
        <td>${user.email}</td>

      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
