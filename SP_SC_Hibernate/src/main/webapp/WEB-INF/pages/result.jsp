<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Insert title here</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/resources/css/pageStyle.css" />" />

  <script type="text/javascript">

    (function($,W,D)
    {
      var JQUERY4U = {};

      JQUERY4U.UTIL =
      {
        setupFormValidation: function()
        {
          //form validation rules
          $("#register-form").validate({
            errorElement: "div",
            errorPlacement : function(error, element) {
              error.insertAfter(element).append().css({"color" : "red","margin-top" : "10px"});
            },
            rules: {
              Location_ID: "required",
              Name: "required",
              ACTIVE:	"required",
            },
            messages: {
              Location_ID: "Please enter  location id",
              Name: "Please enter location name",
              ACTIVE:	"Please select location name",
            },
            submitHandler: function(form) {
              if (confirm('Are you sure you want to submit this data ?')) {
                form.submit();
              }
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
<script type="text/javascript">
  function sendbackTwo(){
    window.open("locationList","_self");
  }
</script>
</head>
<body>

<center>
  <div style="width: 710px;height: 400px;border: 0px solid red;margin-top: -100px;">
  <c:forEach items="${LocationList}" var="list" varStatus="status">
    <c:set var="Name" value="${list.name}" />
    <c:set var="Location" value="${list.location_ID }"></c:set>
    <c:set var="Active" value="${list.active }"></c:set>
  </c:forEach>

  <%
    Long  loca = (Long) pageContext.getAttribute("Location");
    String name = (String) pageContext.getAttribute("Name");
    String active = (String) pageContext.getAttribute("Active");
 %>
  <form action="editLocation" method="post" name="myForm" onsubmit="return validate()" id="register-form" novalidate="novalidate">
    <h2>Location</h2>
    <table border="0">
      <tr>
        <th>location Id</th>
        <td>
          <input type="text" name="Location_ID" value="<%=loca%>" readonly>
          <div></div>
        </td>
      </tr>
      <tr>
        <th>Name</th>
        <td>
          <input type="text" name="Name" value="<%=name%>" />
          <div></div>
        </td>
      </tr>
      <tr>
        <th>Active</th>
        <td>
         <select name="Active">
           <option value="1">Active</option>
           <option value="0">Pause</option>
         </select>
        </td>
      </tr>
      <tr>
        <td></td>
        <td><button type="submit">Save</button>&nbsp;
          <button type="button" onclick="return sendbackTwo();">Cancel</button></td>

      </tr>
    </table>
  </form>
    </div>
</center>

</body>
</html>