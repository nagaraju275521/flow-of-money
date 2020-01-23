<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title></title>
    <style>
        body{
            width: 100%;
            margin: 0px;
        }
    </style>
    <link href="./resources/css/styleIE.css" rel="Stylesheet" type="text/css" />
<!-- 	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"/> --> <!--no need this check  -->
	<link rel="stylesheet" href="<c:url value="/resources/css/tableStyles.css" />" />
 	<link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" /> 
	
	<script type="text/javascript" src="./resources/js/jquery-1.12.0.min.js" ></script>
	 <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
	<script type="text/javascript" src="./resources/js/jquery.hoverintent.r7.js" ></script>
	<script type="text/javascript" src="./resources/js/jquery.sessionTimeout.js"></script>
	
	<script type="text/javascript" src="./resources/js/jquery.mnmenu.js" ></script>
	
	
  	<script>
  	$(document).ready(function() {
 
                $('#idmenu').mnmenu({responsiveMenuButtonLabel: "<i class='fa fa-bars fa-lg'></i>"});
                
                
            });
  	</script>
	
 	<script type="text/javascript">
		function formsubmit(){
			
			document.getElementById("formLogout").submit();

		}
	</script>
</head>
<body>
<iframe src="./HeaderAfter" name="Header" width="100%" height="80px" style="border: 0px;"></iframe>
<div id="menu_bar">

<ul id="idmenu">
	<li><a href="./homePage" target="Body">Home</a></li>
	<li><a href="./expensesList" target="Body">Expenses</a>
		<ul>
			<li><a href="./expensesList" target="Body">Expenses</a></li>
			<li><a href="./expenses" target="Body">Entry</a></li>
			<!-- <li><a href="./expensesMulty" target="Body">Multy Entries</a></li> -->
			<li><a href="#">send mail test</a>
				<ul>
					<li><a href="#">----------</a></li>
					<li><a href="#">----------</a></li>
					<li><a href="#">------------</a></li>						
				</ul>
			</li>						
		</ul>
	</li>
	<li><a href="./openHolderList" target="Body">Holders</a>
	<ul>
			<li><a href="./openHolderList" target="Body">Holder</a></li>
			<li><a href="./openSourceList" target="Body">Source</a></li>
			<li><a href="./openCreditList" target="Body">Credit</a></li>
			<li><a href="./openDebitList" target="Body">Debit</a></li>				
		</ul>
	</li>
	<li><a href="./openExportPage" target="Body">Export &amp; Import</a>
		<ul>
			<li><a href="./openExportPage" target="Body">Export Excel</a></li>
			<li><a href="#">Export xml</a></li>
			<li><a href="#">Import Excel</a></li>	
			<li><a href="#">Import xml</a></li>			
		</ul>
	</li>
	<li><a href="./holderList" target="Body">Records</a>
		<ul>
			<li><a href="./holderList" target="Body">Holder</a></li>
			<li><a href="./openSourceRecord" target="Body">Source</a></li>
			<li><a href="./openCreditRecord" target="Body">Credit</a></li>
			<li><a href="./openDebitRecords" target="Body">Debit</a></li>
			<li><a href="./openCreditClearRecord" target="Body">Credit Clear</a></li>
			<li><a href="./openDebitClearRecord" target="Body">Debit Clear</a></li>
			<li><a href="./allRecords" target="Body">All Records</a></li>
			<li><a href="./jqueryPlugins" target="Body">Jquery Plugins</a></li>				
		</ul>
	</li>
	<li><a href="./openDairyResult" target="Body">Dairy</a>
		<ul>
			<li><a href="./dairy" target="Body">Dairy</a>
			<li><a href="./openDairyResult" target="Body">Dairy Result</a></li>
			<li><a href="./galleryPage" target="Body">Gallery</a></li>
			<li><a href="./openStudentResult" target="Body">Student</a></li>
			<li><a href="./openEmployeeResult" target="Body">Employee</a></li>
			<li><a href="./exceone" target="Body">exceone</a></li>
			<li><a href="./excetwo" target="Body">excetwo</a></li>
		</ul>
	</li>	
	<li style="float: right;">
	<a href="<c:url value="/logout?AccessDenied=logedout" />" target="_top"><p id="imm"></p></a></li>		
</ul>


</div>


<iframe src="./homePage" name="Body" width="100%" style="height: 800px;" style="border: 0px;"></iframe>
<iframe src="./footer" name="Footer" width="100%" height="200px" style="border: 0px;"></iframe>



	
</body>
</html>