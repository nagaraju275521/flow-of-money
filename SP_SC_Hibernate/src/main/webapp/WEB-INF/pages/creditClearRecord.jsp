<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Flow of money</title>

  <link rel="stylesheet" href="<c:url value="/resources/css/tableStyles.css" />" /> 
  <link href="./resources/css/paginationForExpense.min.css" rel="Stylesheet" type="text/css" />
  <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" /> 
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrapPagination.css" />" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/BootstrapPagination.min.css" />" />
  	<link href="./resources/css/showLoading.css" rel="Stylesheet" type="text/css" />
  	<link href="./resources/css/bootstrap-combined.min.css" rel="Stylesheet" type="text/css" />
	<link href="./resources/css/bootstrap-datetimepicker.min.css" rel="Stylesheet" type="text/css" />
	
	<link rel="stylesheet" href="<c:url value="/resources/css/demo-page.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/hover.css" />" />
  	
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
  
  	$(document).ready(function(){
	  	$("div").click(function(){
			$(".dataTables_paginate>span>a").addClass("hvr-pulse");
			$("#example_paginate>a").addClass("hvr-pulse");
		});
	});
  
   function ReadMore(){
		 
	   var showChar = 30;
		var ellipsestext = "...";
		var moretext = "&#10143;";
		var lesstext = "&#8672;";
		$(".more").each(function() {
			var content = $(this).html();
			
			if(content.length > showChar) {

				var c = content.substr(0, showChar);
				var h = content.substr(showChar-1, content.length - showChar);

				var html = c + '<span class="moreelipses">'+ellipsestext+'</span>&nbsp;<span class="morecontent"><span>' + h + 
				'</span>&nbsp;&nbsp;<a href="" style="border: 0px solid red;padding:0px;color:#949494;" class="morelink">'+moretext+'</a></span>';

				$(this).html(html);
			}

		});
		
		$(".morelink").click(function(){
			if($(this).hasClass("less")) {
				$(this).removeClass("less");
				$(this).html(moretext);
			} else {
				$(this).addClass("less");
				$(this).html(lesstext);
			}
			$(this).parent().prev().toggle();
			$(this).prev().toggle();
			return false;
		});
   }
   
   function newID(){
	   //alert("----------");
	   
	   $("span").removeClass("morecontent");
	   //$("td:last>a").removeClass("morelink");
	   //ReadMore();
	   
   }
   
   
    function Checkboxchek() {
      var count = 0;
      for ( var i = 0; i < admin.location_ID.length; i++) {
        if (admin.location_ID[i].checked) {
          count++;
        }
      }
      if (count == 0) {
        alert("please select checkbox");
        //document.msgchbox.checkbox.focus();
        return false;
      } else {
        alert(count + ": rows selected for delete");
        return true;
      }
    }

function openCreditPage(){
		window.open("./openCreditList", "_self");
}
  
function hidef(){
	  
	  $(function(){
		sendTo(1/2/2016);
	    complate();
	  
	  });
	}
    
    function sendTo(date1){
    	
   	 $.ajax({
           type: "GET",
           url: "./ClearRecordForCredit",
           cache : false,
   		dataType : "json",
           data: { date1: date1}, // parameters
   			success : function(response) {
   				// you could use the result.values dictionary here
   				$.each(response, function(key, value) { //populate child options
   					
   					var eachrow = "<tr>"
   						+ "<td><input type='checkbox' /> </td>"
   		                 + "<td>" + value.CreditClear_Id+"</td>"
   		                 + "<td>" + value.CreditClear_Date + "</td>"
   		                 + "<td class='more'>" + value.CreditClear_name + "</td>"
   		                 + "<td>" + value.CreditClear_Amount + "</td>"
   		              	 + "<td>" + value.CreditClear_balance + "</td>"
   		           		 + "<td class='more'>" + value.CreditClear_Source + "</td>"
   		           		+ "<td>" + value.CreditClear_SourceBal + "</td>"
   		         		+ "<td class='more'>" + value.CreditClear_Des + "</td>"
   		                 + "</tr>";
   		              
   					$("#CClearList").append(eachrow);

   				});
			},
   			error : function(response, status, er) {
   				alert("error: " + response + " status: " + status
   						+ " er:" + er);
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
                   .column( 4 )
                   .data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
    
               // Total over this page
               pageTotal = api
                   .column( 4, { page: 'current'} )
                   .data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
    
             
               var totalF = total.toFixed(2);
               var pageTotalF = pageTotal.toFixed(2);
            // Update footer
               $( api.column( 4 ).footer() ).html(
                +pageTotalF +' ( '+ totalF +' total)'
               );
           }
       });

		$(".dataTables_paginate>span>a").addClass("hvr-pulse");
    	$("#example_paginate>a").addClass("hvr-pulse");
	}


  </script>
 
</head>
<body onload="return hidef();">

<center>
  <div style="width: 990px;height:570px;border: 0px solid red;margin-top: 30px;">

    <table style="width: auto;border: 0px solid red;">
      <tr>
        <td>
          <div class="headTableFloat">
            <strong>Creditor Clear Record</strong>
              	<div><button type="submit" class="GrayBtn GrayBtn-primary">Delete</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return openCreditPage();">Add</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return SelectMailDynamic('Direct', 'CreditClearRecord');">Send email</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return commonPOPUpTwoDates('CreditClearRecord');">Excel</button></div>
          </div>
        </td>
      </tr>
      <tr>
        <td>

          <form action="deleteBank" method="post" name="admin" id="myForm">
            <table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0" width="100%">
              <thead>
              <tr>
                <th style="border-right-style: hidden;"></th>
                <th style="border-right-style: hidden;">Id</th>
                <th style="border-right-style: hidden;">Date</th>
                <th style="border-right-style: hidden;">Creditor name</th>
                <th style="border-right-style: hidden;">Amount</th>
                <th style="border-right-style: hidden;">Balance</th>
                <th style="border-right-style: hidden;">Source name</th>
                <th style="border-right-style: hidden;">Source Bal</th>
                <th style="border-right-style: hidden;">Description</th>
              </tr>
              </thead>
              <tfoot>
    			<tr><th> </th> <th></th> <th></th> <th>total:</th> <th></th> <th></th> <th></th> <th></th>	<th></th></tr>
    			</tfoot>
              <tbody id="CClearList">
              
              </tbody>
            </table>
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