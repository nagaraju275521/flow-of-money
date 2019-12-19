<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Dairy</title>
		<link href="./resources/css/pageStyle.css" rel="Stylesheet" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/resources/css/tableStyles.css" />" />
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
		<link rel="stylesheet" href="<c:url value="/resources/css/boostrapText.min.css" />" />		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="<c:url value="/resources/css/editor.css" />" />
		<link href="./resources/css/jquery.toastmessage.css" rel="Stylesheet" type="text/css" />
		<link href="./resources/css/boots-combinedExpens.min.css" rel="Stylesheet" type="text/css" />
		<link href="./resources/css/bootstrap-datetimepicker.min.css" rel="Stylesheet" type="text/css" />
		 <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" />
		 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="<c:url value="/resources/js/editor.js" />"></script>
		<script type="text/javascript" src="./resources/js/Bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="./resources/js/commonFunctions.js"></script>
		<script type="text/javascript" src="./resources/js/bootstrap-popup.min.js"></script>		
		<script type="text/javascript" src="./resources/js/jquery.toastmessage-min.js"></script> 
		<script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
  		<script type="text/javascript" src="./resources/js/jquery.sessionTimeout.js"></script>
		<script>
		
		sessionTimeOUT('./sessionRedir');
		
			$(document).ready(function() {
				$("#txtEditor").Editor();
			});
			

			$(document).ready(function() {
				$('#datetimepicker').datetimepicker({
				    format: 'MM/dd/yyyy hh:mm:ss',
				    language: 'en'
				});
			
			
			
	$("#formDairy").submit(function(e){
		//alert("--------------"+$('.Editor-editor').text().length);
		var Des = $('.Editor-editor').text();
		//var characterReg = new RegExp("^[a-zA-Z0-9~`!@#$%^&*()_+-={}|:;<>,.?\"/' \\[\\]]+$"); success no slash
		var characterReg = new RegExp("^[a-zA-Z0-9~`!@#$%^&*()_+-={}|:;<>,.?\"/' \\[\\]]+$");
     	var characterRegInp = /^[a-zA-Z0-9_ .,<>]{0,200}$/;
			if($("#DairyDate").val() === ""){
   				$().toastmessage('showErrorToast', "Please enter date");
   				e.preventDefault(e);
   			}else if($('.Editor-editor').text() === ""){
   	    		$().toastmessage('showErrorToast', "Description not empty");
   		        e.preventDefault(e);
   			}else if($('.Editor-editor').text().length > 5000){
   	    		$().toastmessage('showErrorToast', "Description length should be below 5000");
   		        e.preventDefault(e);
   			}else if(!Des.match(characterReg)){
   	    		$().toastmessage('showErrorToast', "Description box support only keyboard symbols.");
   		        e.preventDefault(e);
   			}else{
   				e.preventDefault(e);

   				$.bs.popup.confirm({
   					title : 'Confirm Message',
   					info : 'Do you want confirm to save dairy ?'
   				}, function(dialogE) {
   						dialogE.modal('hide');				
   						   				
   	    	
   	    		var Dairy_Date1 = $("#DairyDate").val();
   	        	var Dairy_Des1 = $('.Editor-editor').text(); 
   	        	//alert(Dairy_Date1+"--"+Dairy_Des1);
   	        	$.ajax({
   	                contentType: "application/json; charset=utf-8",
   	                dataType: "json",
   	                type: $("#formDairy").attr("method"),
   	                url:  $("#formDairy").attr("action"),
   	                data:{Dairy_Date: Dairy_Date1, Dairy_Des: Dairy_Des1}, 
   	                success : function(response){		            	
   	                	if(response === "Success"){
   	                		showPopUpmessage("Successfully save dairy.", "./openDairyResult");
   	                	}else{
   	                		$().toastmessage('showErrorToast', "message :"+response);
   	                	}
   	                },
   	                error: function (re) {
   	                	$().toastmessage('showErrorToast', "Error in Ajax form");
   	                }
   	            	});   	        	
   				});   	        	
   			}			
		});
	});
			
		</script>
		
	
		
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<h2 class="demo-text">Your Dairy</h2>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 nopadding">
						<form action="./saveDairy" method="get" id="formDairy">
							<div id="datetimepicker" class="input-append date">
										<input type="text" name="DairyDate" id="DairyDate" class="dairyDate" /> 
										<span class="add-on"> 
											<i	data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>
										</span>
							</div>
							<textarea name="DairyDes" id="txtEditor" class="textArea"></textarea> 							
							<div class="buttonFlow">								
								<button type="button" class="BlueBtn BlueBtn-primary" onclick="window.history.back();">Cancel</button>&nbsp;|&nbsp;
								<button type="submit" class="BlueBtn BlueBtn-primary">Save</button>
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
<div id='render-to-me'></div>
    
		
		<div class="container-fluid footer">
			<p class="textFooter">&copy; Vk Technologies <script>document.write(new Date().getFullYear())</script>. All rights reserved.</p>
		</div>
		
	</body>
</html>
