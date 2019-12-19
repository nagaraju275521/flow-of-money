
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
 <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" /> 
<link href="./resources/css/boots-combinedExpens.min.css" rel="Stylesheet" type="text/css" />

<link rel="stylesheet" href="<c:url value="/resources/css/demo-page.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/hover.css" />" />
  
  <script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
  <script src="<c:url value="/resources/js/paging.js" />"></script>
  <!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> -->
  <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
  
  <link href="./resources/css/jquery.toastmessage.css" rel="Stylesheet" type="text/css" />
  <script type="text/javascript" src="./resources/js/jquery.toastmessage-min.js"></script>  
  <script type="text/javascript" src="./resources/js/commonFunctions.js"></script>
  
  <script type="text/javascript" src="./resources/js/jquery.showLoading.min.js"></script>
   <link href="./resources/css/showLoading.css" rel="Stylesheet" type="text/css" />
  
  <script src="<c:url value="./resources/js/dataTables.bootstrap.min.js" />"></script>
  <!-- <script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script> -->
  <script src="<c:url value="./resources/js/jquery.dataTable.min.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrapPagination.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/BootstrapPagination.min.css" />" />
  
  <script src="<c:url value="./resources/js/showMoreLink.js" />"></script>  
  <script src="<c:url value="./resources/js/colResizable-1.6.js" />"></script>
  
   <script type="text/javascript" src="./resources/js/bootstrap-3.3.6.min.js"></script>
  <script type="text/javascript" src="./resources/js/bootstrap-popup.min.js"></script>
  <link href="./resources/css/paginationForExpense.min.css" rel="Stylesheet" type="text/css" />
  
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

function openHolderPage(){
		window.open("./bank", "_self");
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
           url: "./HolderList",
           cache : false,
   		dataType : "json",
           data: { date1: date1}, // parameters
   			success : function(response) {
   				// you could use the result.values dictionary here
   				$.each(response, function(key, value) { //populate child options
   					var eachrow = "<tr>"
   						+ "<td><input type='checkbox' /> </td>"
   		                 + "<td>" + value.holder_Id+"</td>"
   		                 + "<td>" + value.holder_Date + "</td>"
   		                 + "<td>" + value.holder_Type + "</td>"
   		                 + "<td class='more'>" + value.holder_Name + "</td>"
   		              	 + "<td>" + value.holder_Amount + "</td>"
   		           		 + "<td>" + value.holder_Source + "</td>"
   		                 + "</tr>";
   		              
   					$("#holderList").append(eachrow);

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
		$('#example').DataTable({
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
    
               // Total over this page
               pageTotal = api
                   .column( 5, { page: 'current'} )
                   .data()
                   .reduce( function (a, b) {
                       return intVal(a) + intVal(b);
                   }, 0 );
    
             
               var totalF = total.toFixed(2);
               var pageTotalF = pageTotal.toFixed(2);
            // Update footer
               $( api.column( 5 ).footer() ).html(
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
  <div style="width: 810px;height:570px;border: 0px solid red;margin-top: 30px;">

    <table style="width: auto;border: 0px solid red;">
      <tr>
        <td>
          <div class="headTableFloat">
            <strong>Holder Details</strong>
              	<div><button type="submit" class="GrayBtn GrayBtn-primary">Delete</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return openHolderPage();">Add</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return SelectMailDynamic('Direct', 'Holder');">Send email</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return beforeConfirm('Holder', '2/2/2016', '2/2/2016');">Excel</button></div>
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <form action="deleteBank" method="post" name="admin" id="myForm">
            <table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0" width="100%">
              <thead>
              <tr>
                <th></th>
                <th>Bank Id</th>
                <th>Date</th>
                <th>Type</th>
                <th>Name</th>
                <th>Amount</th>
                <th>Source</th>
              </tr>
              </thead>
              <tfoot>
    			<tr><th> </th> <th></th> <th></th> <th></th> <th>total:</th> <th></th>	<th></th></tr>
    			</tfoot>
              <tbody id="holderList">              
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