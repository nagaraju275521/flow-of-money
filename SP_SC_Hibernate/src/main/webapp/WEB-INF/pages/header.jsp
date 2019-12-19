<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
 
    <style>
        .header{
            width: 100%;
            height: 60px;
            background-color: beige;
            margin-top: 0px;
            border-bottom: 2px solid gray;
        }
    </style>
   


</head>
<body>



<div class="header">
<u>Header</u>
    <a href="expenses" target="Body">Expenses</a>| <a href="expensesList" target="Body">Expenses list</a> |
    <a href="locationList" target="Body">Location list</a>  | <a href="location" target="Body">Location</a> |
    <a href="jsonExpenses" target="Body">Json</a> |  <a href="pocket" target="Body">Pocket</a> |  <a href="source" target="Body">Source</a>
    | <a href="pocketList" target="Body">Pocket List</a> | <a href="sourceList" target="Body">Source List</a>
    | <a href="pocketList" target="Body">create credits/list</a> | <a href="./LogOutUser" target="_top">Logout</a>
    | <a href="bankList" target="Body">create bank/list</a> |<a href="holderList" target="Body"> holderList</a> |
</div>

</body>
</html>

