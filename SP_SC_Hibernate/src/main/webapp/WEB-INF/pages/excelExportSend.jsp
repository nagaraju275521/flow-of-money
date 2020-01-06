<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Debit result</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/tableStyles.css" />" /> 
  <link href="./resources/css/paginationForExpense.min.css" rel="Stylesheet" type="text/css" />
  <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" /> 
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrapPagination.css" />" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/BootstrapPagination.min.css" />" />
  	<link href="./resources/css/showLoading.css" rel="Stylesheet" type="text/css" />
  	<link href="./resources/css/bootstrap-combined.min.css" rel="Stylesheet" type="text/css" />
	<link href="./resources/css/bootstrap-datetimepicker.min.css" rel="Stylesheet" type="text/css" />
  	
  <script src="<c:url value="/resources/js/jquery-1.12.0.min.js" />"></script>
  <script type="text/javascript" src="./resources/js/jquery.showLoading.min.js"></script>
 <script src="<c:url value="./resources/js/dataTables.bootstrap.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTable.min.js" />"></script>  
  <script src="<c:url value="/resources/js/showMoreLink.js" />"></script>  
  <script src="<c:url value="/resources/js/colResizable-1.6.js" />"></script>  
  <link href="./resources/css/jquery.toastmessage.css" rel="Stylesheet" type="text/css" />
  <script type="text/javascript" src="./resources/js/jquery.toastmessage-min.js"></script>
  <script type="text/javascript" src="./resources/js/commonFunctions.js"></script>  
  <script type="text/javascript" src="./resources/js/bootstrap-3.3.6.min.js"></script>
  <script type="text/javascript" src="./resources/js/bootstrap-popup.min.js"></script>
  <script type="text/javascript" src="./resources/js/Bootstrap-datepicker.min.js"></script>
  
  <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.sessionTimeout.js"></script>
  
<script type="text/javascript">

sessionTimeOUT('./sessionRedir');

var tableOp = "none";
var mailOption = "none";
var eventname = "none";


var currentDate = new Date()
var day = currentDate.getDate()
var month = currentDate.getMonth() + 1
var year = currentDate.getFullYear()
var todayDate = month + "/" + day + "/" + year;

function Checkboxchek() {
    var count = 0;
    for ( var i = 0; i < admin.getFile.length; i++) {
      if (admin.getFile[i].checked) {
        count++;
      }
    }
    if (count == 0) {
      //alert("please select checkbox");
      $().toastmessage('showErrorToast', "please select checkbox");
      e.preventDefault();
      return false;
    }else if (count > 1) {
       // alert("please select one checkbox");
        $().toastmessage('showErrorToast', "please select one checkbox");
        e.preventDefault();
        return false;
      } else {
      //alert(count + ": rows selected");
      $().toastmessage('showErrorToast', count+" : table selected");
      return true;
    }
  }
  
function SelectMail(){

	$.each($("input[name='selectmail']:checked"), function(){            

        //alert("SelectMail :"+$(this).val());
        mailOption = $(this).val();
                       
    });
}  

function SelectTable(){
	
	Checkboxchek();
	
	if($("input[name='selectmail']").prop('checked') == true){
		//alert("--checked---"+mailOption);
	}else{
		mailOption = "none";
		//alert("--unchecked---"+mailOption);
	}
	
	$.each($("input[name='getFile']:checked"), function(){ 
		eventname = $(this).val();
	});
	
	if(mailOption == "none"){		
		if(eventname === "Debit" || eventname === "Credit" || eventname == "Holder" || eventname == "Source"){
			$.each($("input[name='getFile']:checked"), function(){            
		        tableOp = $(this).val();
		           	//alert(tableOp);
		           	beforeConfirm(tableOp, '2/2/2016', '2/2/2016');
			 });
		}else{
			$.each($("input[name='getFile']:checked"), function(){            
		        tableOp = $(this).val();
		        //alert(tableOp);
		        commonPOPUpTwoDates(tableOp);	        
			 });
		}
		
				
	}else{
		if(eventname === "Debit" || eventname === "Credit" || eventname == "Holder" || eventname == "Source"){
			$.each($("input[name='getFile']:checked"), function(){            
				tableOp = $(this).val();
				SelectMailDynamic(tableOp, tableOp);   
		     });
		}else{
			$.each($("input[name='getFile']:checked"), function(){            
				tableOp = $(this).val();
				SelectMailDynamic(tableOp, tableOp);        
		     });
		}
			
	}
}





</script>
</head>
<body onload="return hidef();" style="background-color: #ffc080;">
	<h2 style="text-align: center;color: black;">Choose Export Table(Excel.xlsx)</h2>


<div style="width: 75%;margin-left: auto;margin-top: 50px;">
<div style="border-bottom: 1px solid black;width:25%;">
<input type="checkbox" value="SendMail" name="selectmail" onclick="return SelectMail();" style="margin-left: 1px;" />&nbsp;&nbsp;&nbsp;Select mail
</div>
<div>	 
<form action="#" name="admin" method="get">	
	<table>

	<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile"  value="Debit" onclick="return SelectTable();" /></td><td style="width: 300px;">Debit</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="Expenses" onclick="return SelectTable();" /></td><td style="width: 300px;">Expenses</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox"  name="getFile" value="Credit" onclick="return SelectTable();" /></td><td style="width: 300px;">credit</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox"  name="getFile" value="Holder" onclick="return SelectTable();" /></td><td style="width: 300px;">Holder</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="Source" onclick="return SelectTable();" /></td><td style="width: 300px;">Source</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="DebitRecord" onclick="return SelectTable();" /></td><td style="width: 300px;">Debit Record</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="CreditRecord" onclick="return SelectTable();" /></td><td style="width: 300px;">Credit Record</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="HolderRecord" onclick="return SelectTable();" /></td><td style="width: 300px;">Holder Record</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="SourceRecord" onclick="return SelectTable();" /></td><td style="width: 300px;">Source Record</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="CreditClearRecord" onclick="return SelectTable();" /></td><td style="width: 300px;">Credit Clear Record</td>
</tr>
<tr>
	<td style="width: 25px;"> <input type="checkbox" name="getFile" value="DebitClearRecord" onclick="return SelectTable();" /></td><td style="width: 300px;">Debit Clear Record</td>
</tr>

</table>
</form>	
</div>	
</div>
	
<div id="appendPoUp"></div>
	<div id="appendPoUpMail"></div>	
	

	
</body></html>