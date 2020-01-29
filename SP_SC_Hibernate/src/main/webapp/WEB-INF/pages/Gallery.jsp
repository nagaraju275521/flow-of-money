<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product Form</title>
</head>
<body>
    
      <center>
       <div style="margin: 100px;">
    
    <form method="post" action="./saveGallery?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
    	<input type="file" name="file" value="" />
    	<input type="submit" value="submit" />
    </form>
    </div>
    </center>
</body>
</html>