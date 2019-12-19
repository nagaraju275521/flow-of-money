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
<link rel="stylesheet" href="<c:url value="/resources/css/pageStyle.css" />" />

<link rel="stylesheet" href="<c:url value="/resources/css/demo-page.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/hover.css" />" />

  <script src="<c:url value="./resources/js/jquery-1.11.3.min.js" />"></script>
  <!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> -->
  <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>

  <script type="text/javascript" src="./resources/js/jquery.weekpicker.js" ></script>
  <!-- <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/> -->
  <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" />
  <link href="./resources/css/boots-combinedExpens.min.css" rel="Stylesheet" type="text/css" />
  <script type="text/javascript" src="./resources/js/jquery.showLoading.min.js"></script>
   <link href="./resources/css/showLoading.css" rel="Stylesheet" type="text/css" />
  
   <script src="<c:url value="./resources/js/dataTables.bootstrap.min.js" />"></script>
  <!-- <script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script> -->
  <script src="<c:url value="./resources/js/jquery.dataTable.min.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapPagination.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/BootstrapPagination.min.css" />" />
   
  <script src="<c:url value="./resources/js/serverSideSecurity.js" />"></script>
  <script src="<c:url value="./resources/js/showMoreLink.js" />"></script>  
  <script src="<c:url value="./resources/js/colResizable-1.6.js" />"></script>
    
  <link href="./resources/css/jquery.toastmessage.css" rel="Stylesheet" type="text/css" />
  <script type="text/javascript" src="./resources/js/jquery.toastmessage-min.js"></script>
  <script type="text/javascript" src="./resources/js/commonFunctions.js"></script>
  
  
   <script type="text/javascript" src="./resources/js/bootstrap-3.3.6.min.js"></script>
  <script type="text/javascript" src="./resources/js/bootstrap-popup.min.js"></script>
  <link href="./resources/css/paginationForExpense.min.css" rel="Stylesheet" type="text/css" />
   
  <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.sessionTimeout.js"></script>
   
  <script>
  
  sessionTimeOUT('./sessionRedir');
  
  	$(document).ready(function(){
	  	$("div").click(function(){
			$(".dataTables_paginate>span>a").addClass("hvr-pulse");
			$("#example_paginate>a").addClass("hvr-pulse");
		});
	});
  
   </script>
   
  <script type="text/javascript">
  
  	var remaingAmount = 0;
  	var lentAmount = 0;
  	var debitname = "";
	var debitamount = 0;
	var sourceN = "";
   	var IdC = 0;	
	var amountA = 0;

  
    function Checkboxchek() {
      var count = 0;
      for ( var i = 0; i < admin.IdCC.length; i++) {
        if (admin.IdCC[i].checked) {
          count++;
        }
      }
      if (count == 0) {
    	$().toastmessage('showErrorToast', "please select checkbox");
        e.preventDefault();
        return false;
      }else if (count > 1) {
    	  $().toastmessage('showErrorToast', "please select one checkbox");         
          e.preventDefault();
          return false;
        } else {
        //alert(count + ": rows selected");
        return true;
      }
    }
    
    function CheckboxchekData() {
        var count = 0;
        for ( var i = 0; i < adminData.Id123.length; i++) {
          if (adminData.Id123[i].checked) {
            count++;
          }
        }
        if (count == 0) {
          $().toastmessage('showErrorToast', "please select checkbox");
          //document.msgchbox.checkbox.focus();
          e.preventDefault();
          return false;
        }else if (count > 1) {
            $().toastmessage('showErrorToast', "please select one checkbox");
            e.preventDefault();
            return false;
          } else {
          //alert(count + ": rows selected");
          return true;
        }
      }
    
function openCreditPage(){
		window.open("./credit", "_self");
}
//function openClearPage(){
	//window.open("./creditClear", "_self");
//}
function clearDynamic(){
	window.open("./creditClearDynamic", "_self");
}

function clearBack(){
	$("#creditlist").show();
	$("#creditClearPart").hide();
	$("#creditClearProceed").hide();
	
	$("#placeLeft").empty();
	$("#placehere").empty();
}

function clearBackAgain(){
	$("#creditlist").hide();
	$("#creditClearPart").show();
	$("#creditClearProceed").hide();
	
	$("#compare").empty();
}

  
function hidef(){
	  
	  $(function(){
		sendTo(1/2/2016);
	    complate();
	  
	  });
	  
	  	$("#creditlist").show();
		$("#creditClearPart").hide();
		$("#creditClearProceed").hide();
	}
    
    function sendTo(date1){
    	
   	 $.ajax({
           type: "GET",
           url: "./creditList",
           cache : false,
   		dataType : "json",
           data: { date1: date1}, // parameters
   			success : function(response) {
   				// you could use the result.values dictionary here
   				$.each(response, function(key, value) { //populate child options
   					
   					var eachrow = "<tr>"
   						+ "<td><input type='checkbox' id='Id123' class='cheBoxForClear' value='"+value.id+"'/> </td>"
   		                 + "<td>" + value.id+"</td>"
   		                 + "<td>" + value.credit_Date + "</td>"
   		                 + "<td class='more'>" + value.credit_Name + "</td>"
   		                 + "<td>" + value.credit_Amount + "</td>"
   		              	 + "<td>" + value.credit_C_Amount + "</td>"
   		                 + "</tr>";
   		              
   					$("#creditdata").append(eachrow);

   				});
			},
   			error : function(response, status, er) {
   				$().toastmessage('showErrorToast', "error: " + response + " status: " + status+ " er:" + er);
   			}
       
      });
   	
   	
   }
   
   function complate(){
   	$(document).ajaxComplete(function () {
   		
   		pagingToAll();
   		ReadMore();
   		colresize();
   	   
   	});
   }

   function  pagingToAll(){
		$('#example').DataTable( {
			"pagingType": "full_numbers",
       		retrieve: true,
           "footerCallback": function ( row, data, start, end, display ) {
               var api = this.api(), data;
    
               // Remove the formatting to get integer data for summation
               var intVal = function ( i ) {
                   return typeof i === 'string' ?
                       i.replace(/[\$,]/g, '')*1 :
                       typeof i === 'number' ?
                           i : 0;
               };
    
               // Total over all pages
               total = api
                   .column( 5 )
                   .data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
    
            // Total over all pages
               totalT = api.column( 4 ).data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
            
               // Total over this page
               pageTotal = api.column( 5, { page: 'current'} ).data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
    
               // Total over this page
               pageTotalT = api.column( 4, { page: 'current'} ).data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
             
               var totalF = total.toFixed(2);
               var pageTotalF = pageTotal.toFixed(2);
               
               var totalF2 = totalT.toFixed(2);
               var pageTotalF2 = pageTotalT.toFixed(2);
            // Update footer
               $( api.column( 5 ).footer() ).html(+pageTotalF +' ( '+ totalF +' total)');
            
               $( api.column( 4 ).footer() ).html(+pageTotalF2 +' ( '+ totalF2 +' total)');
           }
       });
		
		$(".dataTables_paginate>span>a").addClass("hvr-pulse");
    	$("#example_paginate>a").addClass("hvr-pulse");
	}
   	
function openClearPage(){
	
	serverSideSecurity();
	
	CheckboxchekData();
	
	$('input:checkbox[class=cheBoxForClear]:checked').each(function () {
        $this = $(this);
        IdC = $this.parent().siblings('td').eq(0).text();
        sourceN = $this.parent().siblings('td').eq(2).text();
        amountA = $this.parent().siblings('td').eq(4).text();
    });
	
	var eachrow12 = "<tr>"	
			+ "<td style='height:40px;font-size:20px;'>"+IdC+"</td>"            
            + "<td class='more' style='height:40px;font-size:20px;'>" + sourceN + "</td>"
         	 + "<td style='height:40px;font-size:20px;'><textarea id='lentAmount' style='height:30px;' readonly>"+amountA+"</textarea></td>"
            + "</tr>";
         
		$("#placeLeft").append(eachrow12);
	
	$("#creditlist").hide();
	$("#creditClearPart").show();
	$("#creditClearProceed").hide();
	
	
	$.ajax({
        type: "GET",
        url: "./creditClearFirst",
        cache : false,
		dataType : "json",
        data: { Id: IdC, sourceCC: sourceN, amountCC: amountA}, // no need of this parameters delet it
			success : function(response) {
				//alert(response);
				$.each(response, function(key, value) { //populate child options
   					
   					var eachrow = "<tr>"  						
   						+ "<td><input type='checkbox' name='IdCC' class='cheBoxForProceed' value='"+value.id+"'/></td>"  		                 
   		                 + "<td class='more'>" + value.debit_Name + "</td>"
   		              	 + "<td>" + value.debit_Amount + "</td>"
   		                 + "</tr>";
   		              
   					$("#placehere").append(eachrow);

   				});
			},
			error : function(response, status, er) {
				$().toastmessage('showErrorToast', "error: " + response + " status: " + status+ " er:" + er);
			}
    
   });
	
}  

function clearCompare(){
	serverSideSecurity();
	Checkboxchek();
	lentAmount = $("#lentAmount").val();
	remaingAmount = amountA-lentAmount;
	//alert(lentAmount+"--->--"+amountA);
	var d = new Date();
    var strDate = (d.getMonth()+1)+"/"+ d.getDate()+"/"+d.getFullYear();
	document.getElementById("curr_date").value = strDate;
		
	$('input:checkbox[class=cheBoxForProceed]:checked').each(function () {
        $this = $(this);
        debitname = $this.parent().siblings('td').eq(0).text();
        debitamount = $this.parent().siblings('td').eq(1).text();
        
    });
	
	//alert(debitname+"--"+debitamount);
	var diff = debitamount-lentAmount;
	var difference = diff.toFixed(2);
	$("#creditlist").hide();
	$("#creditClearPart").hide();
	$("#creditClearProceed").show();
	
	var eachrow456 = "<tr>"  						
			+ "<td style='height:40px;font-size:16px;'>"+sourceN+"</td>" 
			+ "<td style='height:40px;font-size:15px;'>"+lentAmount+"</td>" 
			+ "<td style='height:40px;font-size:15px;'>"+remaingAmount+"</td>"
			+ "<td style='height:40px;font-size:16px;'>"+debitname+"</td>" 
            + "<td style='height:40px;font-size:18px;'>" + debitamount + "</td>"
         	+ "<td style='height:40px;font-size:16px;'>" + difference + "</td>"
            + "</tr>";
         
		$("#compare").append(eachrow456);
}

function clearProcced(){
	$.bs.popup.confirm({
        title: 'Title',
        info: 'Do you want confirm?'
    }, function(dialogE) {
        //alert('You are confirmed.');
        dialogE.modal('hide');
        var Curr_date = $("#curr_date").val();
        var Des_Cle = $("#Des_cle").val();
        $.ajax({
            type: "GET",
            url: "./clearLentAmount",
            cache : false,
    		dataType : "json",
            data: {sourceCC: sourceN, amountCC: lentAmount, debitName: debitname, debitAmount: debitamount, curr_date: Curr_date, des_Cle: Des_Cle}, // parameters
    			success : function(response) {
    				if(response === "NoNeedAction"){
    				$().toastmessage('showErrorToast', "No Need Action for this");
    				}else if(response === "actionNotPerform"){
    					$().toastmessage('showErrorToast', "Sorry action Not Perform");
    				}else{
    					$("#creditlist").show();
    					$("#creditClearPart").hide();
    					$("#creditClearProceed").hide();
    					$("#creditdata").empty();
    					$("#placeLeft").empty();
    					$("#placehere").empty();
    					$("#compare").empty();
    					sendTo(1/2/2016);
    				    complate();
    				    if(response === "CreditMore"){
    						showPopUpmessage123("Successfully update Crediter and Debiter. But Credit more.");
    				    }else if(response === "DebitMore"){
    				    	showPopUpmessage123("Successfully update Crediter and Debiter. But Debit more.");
    				    }else if(response === "EquallMore"){
    				    	showPopUpmessage123("Successfully update Crediter and Debiter. Now amount not available.");
    				    }else{
    				    	$().toastmessage('showErrorToast', "Error");
    				    }
    				}
    				
    			},
    			error : function(response, status, er) {
    				$().toastmessage('showToast', {
    		            text     : 'error: ' + response + ' status: ' + status+ ' er:' + er,
    		            sticky   : true,
    		            position : 'top-right',
    		            type     : 'error',
    		            closeText: '',
    		            close    : function () {
    		                console.log("toast is closed ...");
    		            }
    		        });
    			}
        
       });
    });
	
	
	
}
  </script>

</head>
<body onload="return hidef();">


<center>
<div style="width: 60%;height:570px;border: 0px solid red;margin-top: 30px;" id="creditlist">

    <table style="width: auto;border: 0px solid red;">
      <tr>
        <td>
          <div class="headTableFloat">
           <strong>Creditors Details</strong>           		
              	<div><button type="submit"  onclick="return Checkboxchek();" class="GrayBtn GrayBtn-primary">Delete</button></div>
   				<div><button class="GrayBtn GrayBtn-primary" onclick="return openCreditPage();">Add</button></div>
   				<div><button class="GrayBtn GrayBtn-primary" onclick="return openClearPage();">Clear</button></div>
   				<div><button class="GrayBtn GrayBtn-primary" onclick="return clearDynamic();">Dynamically</button></div>
   				<div><button class="GrayBtn GrayBtn-primary" onclick="return SelectMailDynamic('Direct', 'Credit');">Send email</button></div>
   				<div><button class="GrayBtn GrayBtn-primary" onclick="return beforeConfirm('Credit', '2/2/2016', '2/2/2016');">Excel</button></div>
          </div>
        </td>
      </tr>
      <tr>
        <td>

          <form action="deleteExpenses" method="post" name="adminData" id="myForm">
            <table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0" width="100%">
              <thead>
              <tr>
                <th style="border-right-style: hidden;"></th>
                <th style="border-right-style: hidden;">Id</th>
                <th style="border-right-style: hidden;">Date</th>
                <th style="border-right-style: hidden;">Name</th>
                <th style="border-right-style: hidden;">Available Amount</th>
                <th style="border-right-style: hidden;">Credit Amount</th>
                </tr>
              </thead>
              	<tfoot>
    				<tr><th> </th> <th></th> <th></th> <th>total:</th> <th></th> <th></th>	</tr>
    			</tfoot>
              <tbody id="creditdata">

              </tbody>
            </table>
          </form>
        </td>
      </tr>
    </table>
  </div>
  
<div style="width: 97%;height:570px;border: 0px solid red;margin-top: 60px;" id="creditClearPart">
<p>Choose your caluclates</p>
	<div style="width: 40%;">
		<table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0" >
		<thead>
		<tr>
		<th>Id</th>
		<th>Current person</th>
		<th>amount</th>
		</tr>
		</thead>
		<tbody id="placeLeft">

		</tbody>
		</table>
	</div>
	<div style="width: 40%;">
	<form action="#" method="get" name="admin">
	<table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0">
	<thead>
	<tr>
	
	<th></th>
	<th>choose one</th>
	<th>amount</th>
	</tr>
	</thead>
	<tbody id="placehere">

	</tbody>
	</table>
	</form>
	</div>

	<button type="button" onclick="return clearCompare();" class="BlueBtn BlueBtn-primary">Compare</button>
	<button type="button" onclick="return clearDynamic();" class="BlueBtn BlueBtn-primary">Dynamically Clearence</button>
	<button type="button" onclick="return clearBack();" class="BlueBtn BlueBtn-primary">Cancel</button>
</div>

<div style="width: 60%;height:570px;border: 0px solid red;margin-top: 100px;" id="creditClearProceed">
<table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0" style="margin-bottom: 0px;width: 100%;">
		<thead>
		<tr>
			<th style="width: 40%;">Date</th><th style="width: 60%;">Description</th>	
			</tr></thead>
			<tbody>
			<tr>
			<td style="width: 40%;"><input type="text" name="curr_date" id="curr_date" class="week-picker" value="" style="width: 100%;height: 30px;font-size: 19px;color: green;" /></td>
			<td style="width: 60%;"><textarea name="Des_cle" id="Des_cle" style="width: 100%;height: 30px;background-color: #ffeece;color: green;"></textarea></td>
		</tr></tbody>
	</table>
	<table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0">
	<thead>	
	<tr>	
	<th>credit name</th>
	<th> amount</th>
	<th>Remaing</th>
	<th>debit name</th>
	<th> amount</th>
	<th>difference</th>
	</tr>	
	</thead>
	<tbody id="compare">
	</tbody>
	</table>
	<button type="button" onclick="return clearProcced();" class="BlueBtn BlueBtn-primary">Proceed</button>
	<button type="button" onclick="return clearBackAgain();" class="BlueBtn BlueBtn-primary">Cancel</button>
	</div>
</center>
	<div id="appendPoUpMail"></div>
</body>
</html>