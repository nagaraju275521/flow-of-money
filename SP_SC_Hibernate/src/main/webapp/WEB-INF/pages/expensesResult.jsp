<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Drugs location</title>



  <link rel="stylesheet" href="<c:url value="/resources/css/tableStyles.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/demo-page.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/hover.css" />" />
 <!-- <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/> -->
 <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" /> 
  <link href="./resources/css/showLoading.css" rel="Stylesheet" type="text/css" />
 <script src="<c:url value="/resources/js/jquery-1.12.0.min.js" />"></script>
  
  <!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> -->
 
  <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
  <script type="text/javascript" src="./resources/js/commonFunctions.js"></script>
  <script src="<c:url value="./resources/js/showMoreLink.js" />"></script>  
  <script type="text/javascript" src="./resources/js/jquery.showLoading.min.js"></script>
  
   
  <script src="<c:url value="./resources/js/dataTables.bootstrap.min.js" />"></script>
  <script src="<c:url value="./resources/js/jquery.dataTable.min.js" />"></script>
  <script src="<c:url value="./resources/js/expensesViewResult.js" />"></script>
 <!-- 
   <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
   -->
  <link href="./resources/css/boots-combinedExpens.min.css" rel="Stylesheet" type="text/css" />
<link href="./resources/css/bootstrap-datetimepicker.min.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="./resources/js/Bootstrap-datepicker.min.js"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/paginationForExpense.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/paginationForExpense.min.css" />" />
  
  <script src="<c:url value="./resources/js/colResizable-1.6.js" />"></script>
 
  <link href="./resources/css/jquery.toastmessage.css" rel="Stylesheet" type="text/css" />
  <script type="text/javascript" src="./resources/js/jquery.toastmessage-min.js"></script>
  <!-- dialog box -->
  <!-- <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
  <script type="text/javascript" src="./resources/js/bootstrap-3.3.6.min.js"></script>
  <script type="text/javascript" src="./resources/js/bootstrap-popup.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.sessionTimeout.js"></script>
  
  
   
</head>
<body onload="return bodyLoading();"  style="margin-top: 20px;" >
<center>

  <div style="width: 98%;height:570px;border: 0px solid red;margin-top: 0px;">

    <table style="width:100%;border: 0px solid red;">
      <tr>
        <td>
          <div class="headTableFloat">
            
             <strong>Expenses Details: </strong>
             
             <div class="selec SMLicons">               	
               	<button id="TinyTab" class="hvr-pulse">T</button>
               	<button id="smallTab" class="hvr-pulse">S</button>
               	<button id="MediumTab" class="hvr-pulse">M</button>
               	<button id="LargeTab" class="hvr-pulse">L</button>
               	<button id="largeRe" class="hvr-pulse">Go</button>
              </div>
             
            
             
              <div class="selec">
              <select name="gggg" id="mySelect">
              	<option value="null">Select</option>
                <option value="allData">All</option>
                <option value="date">Date</option>
                <option value="BeeDate">Between Date</option>
                <option value="UniteName">Unite Name</option>
               
              </select>
               
              </div>

                <div id="DATE_"  class="selec">
                     <div id="datetimepicker" class="input-append date">
						<input type="text"  name="sss"  id="datavalue"  /> 
						<span class="add-on"> 
								<i	data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>
						</span>
						<button  id="button1" style="height: 25px;width: 30px;border: 0px;background-color: transparent;">
                        <img src="./resources/images/arrow.png"></button>
					</div>            
                                      
                    
                </div>
                
                <div id="showBetween" class="selec">
                
                <div id="datetimepicker2" class="input-append date">						 
						<input type="text" name="toDate" id="toDate"   />
						<span class="add-on"> 
								<i	data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>
						</span>	
						<button  id="twoDates" style="height: 25px;width: 30px;background-color: transparent;border:0px;">
						<img src="./resources/images/arrow.png"></button>					
					</div> 
					
                	<div id="datetimepicker1" class="input-append date">
						<input type="text" name="fromDate" id="fromDate" />						
						<span class="add-on"> 
								<i	data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>
						</span>						
					</div>              	
                
                        
                    
                </div>
                <div id="Unitename" class="selec">                   
                     <input type="text" value="" name="" id="tags" />
                     <button  id="twoDates" style="height: 25px;width: 30px;background-color: transparent;border:0px;">
						<img src="./resources/images/arrow.png"></button>                   
                </div>
             
              	<div><button type="submit"  onclick="return Checkboxchek();" class="GrayBtn GrayBtn-primary">Delete</button></div>
            	<div><p class="GrayBtn GrayBtn-primary" onclick="return openexpenses();">Add</p></div>
            	<div><p class="GrayBtn GrayBtn-primary" onclick="return SelectMailDynamic('Direct', 'Expenses');">Send email</p></div>
            	<div><p class="GrayBtn GrayBtn-primary" onclick="return commonPOPUpTwoDates('Expenses');">Excel</p></div>
              
          </div>
        </td>
      </tr>
      
      <tr>
        <td>
        	<form action="./deleteExpenses" method="get" name="admin" id="myForm">
        		<div id="appendToLarge"> </div> 
        		<div id="appendToSmall"> </div>
        		<div id="appendToDates"></div>
        	</form>
        </td>
      </tr>
    </table>
  </div>
</center>
	<div id="appendPoUp"></div>
	<div id="appendPoUpMail"></div>	
</body>
</html>