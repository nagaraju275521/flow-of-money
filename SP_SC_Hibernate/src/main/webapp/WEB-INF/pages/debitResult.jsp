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
  <link rel="stylesheet" href="<c:url value="/resources/css/pageStyle.css" />" />
  
  <link rel="stylesheet" href="<c:url value="/resources/css/demo-page.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/hover.css" />" />
  
  <link href="./resources/css/paginationForExpense.min.css" rel="Stylesheet" type="text/css" />
  <link rel="stylesheet" href="<c:url value="/resources/css/smoothness-jquery-ui.css" />" /> 
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrapPagination.css" />" />
  	<link rel="stylesheet" href="<c:url value="/resources/css/BootstrapPagination.min.css" />" />
  	<link href="./resources/css/showLoading.css" rel="Stylesheet" type="text/css" />
  	<link href="./resources/css/bootstrap-combined.min.css" rel="Stylesheet" type="text/css" />
	<link href="./resources/css/bootstrap-datetimepicker.min.css" rel="Stylesheet" type="text/css" />
  	
  <script src="<c:url value="/resources/js/jquery-1.12.0.min.js" />"></script>
  <script type="text/javascript" src="./resources/js/jquery.showLoading.min.js"></script>
  <!-- <script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"></script> -->
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
  
  <!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>  
<script type="text/javascript" src="./resources/js/jquery.weekpicker.js" ></script> -->
<!--  <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/> -->  
   
<script type="text/javascript">

sessionTimeOUT('./sessionRedir');

$(document).ready(function(){
  	$("div").click(function(){
		$(".dataTables_paginate>span>a").addClass("hvr-pulse");
		$("#example_paginate>a").addClass("hvr-pulse");
	});
});

 var debit_Name = "";
 var Source_Name = "";
 var De_Amount = 0.0;

 
    function Checkboxchek() {
      var count = 0;
      for ( var i = 0; i < adminDC.DebitId.length; i++) {
        if (adminDC.DebitId[i].checked) {
          count++;
        }
      }
      if (count == 0) {
        $().toastmessage('showErrorToast', "please select checkbox");
        //document.msgchbox.checkbox.focus();
        e.preventDefault();
        return false;
      }else if(count > 1){
    	  $().toastmessage('showErrorToast', "please select one checkbox");
          e.preventDefault();
          return false; 
      } else {
        //alert(count + ": rows selected for delete");
        return true;
      }
    }
    
  
	
function openDebitPage(){
		window.open("./debit", "_self");
}
function openDebitClearPage(){
	Checkboxchek();
	$(".DebitEntryPage").hide();
	$(".ClearPage").show();
	//window.open("./openDebitClear", "_self");
	loadholderList()	
	  
	   $('input:checkbox[class=cheBoxForClear]:checked').each(function () {
		    $this = $(this);
		    debit_Name = $this.parent().siblings('td').eq(2).text();
		    De_Amount = $this.parent().siblings('td').eq(3).text();
		});
$("#Debit_ClearName").attr("value",debit_Name);
$("#Debit_ClearAmount").attr("value",De_Amount);
}
function ClearBack(){
	$(".DebitEntryPage").show();
	$(".ClearPage").hide();
	$(".child_selection").empty();
}

function loadholderList(){
	$.ajax({
				url : "./loadHolderNames",
				type : "GET",
				cache : false,
				dataType : "json",
				data:{banktype: "bank"},
				success : function(response) {
					// you could use the result.values dictionary here
					var obj = response;

					$.each(obj, function(key, name1) { //populate child options
						$(".child_selection").append(
								"<option value=\""+name1+"\">" + name1
										+ "</option>");
					});

				},
				error : function(response, status, er) {
					alert("error: " + response + " status: " + status
							+ " er:" + er);
				}
			});
}
  
function hidef(){
	  $(".ClearPage").hide();
	  $(function(){
		sendTo(1/2/2016);
	    complate();
	  
	  });
	}


	function sendTo(date1) {
		$.ajax({
					type : "GET",
					url : "./debitList",
					cache : false,
					dataType : "json",
					data : {
						date1 : date1
					}, // parameters
					success : function(response) {
						// you could use the result.values dictionary here
						$.each(response, function(key, value) { //populate child options
											var eachrow = "<tr>"
													+ "<td><input type='checkbox' id='DebitId' class='cheBoxForClear' value='"+value.id+"'/> </td>"
													+ "<td>" + value.id
													+ "</td>" + "<td>"
													+ value.debit_Date
													+ "</td>"
													+ "<td class='more'>"
													+ value.debit_Name
													+ "</td>" + "<td>"
													+ value.debit_Amount
													+ "</td>" + "</tr>";

											$("#debitdata").append(eachrow);
										});
					},
					error : function(response, status, er) {
						alert("error: " + response + " status: " + status + " er:" + er);
					}

				});
	}

	function complate() {
		$(document).ajaxComplete(function() {

			pagingToAll();
			ReadMore();
			colresize();

		});
	}

	function pagingToAll() {
		$('#example')
				.DataTable({
							"pagingType": "full_numbers",
							retrieve : true,
							"footerCallback" : function(row, data, start, end,
									display) {
								var api = this.api(), data;

								// Remove the formatting to get integer data for summation
								var intVal = function(i) {
									return typeof i === 'string' ? i.replace(
											/[\$,]/g, '') * 1
											: typeof i === 'number' ? i : 0;
								};

								// Total over all pages
								total = api.column(4).data().reduce(
										function(a, b) {
											return intVal(a) + intVal(b);
										}, 0);

								// Total over this page
								pageTotal = api.column(4, {
									page : 'current'
								}).data().reduce(function(a, b) {
									return intVal(a) + intVal(b);
								}, 0);

								var totalF = total.toFixed(2);
								var pageTotalF = pageTotal.toFixed(2);
								// Update footer
								$(api.column(4).footer()).html(
										+pageTotalF + ' ( ' + totalF + ' )');
							}
						});
		
		$(".dataTables_paginate>span>a").addClass("hvr-pulse");
    	$("#example_paginate>a").addClass("hvr-pulse");

	}

	function colresize() {
		$("#example").colResizable({
			liveDrag : true,
			gripInnerHtml : "<div class='grip'></div>",
			draggingClass : "dragging",
			resizeMode : 'fix' // fix, flex, overflow
		});
	}

	$(document).ready(function() {
		
		$('#datetimepickerDR').datetimepicker({
	    	format: 'MM/dd/yyyy hh:mm:ss',
	    	language: 'en'
		});
		
			$("#formDebitClear").submit(function(e) {
						var numberReg = /^[0-9.]{0,13}$/;
						var characterReg = /^[a-zA-Z0-9., -]{0,150}$/;
						var characterRegInp = /^[a-zA-Z0-9]{0,100}$/;
						var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
						if ($("#ClearDebitDate").val() === "") {
						$().toastmessage('showErrorToast',"Please enter Date");
							e.preventDefault(e);
							} else if ($("#Debit_ClearName").val() === "") {
								$().toastmessage('showErrorToast',"Please enter debiter name");
									e.preventDefault(e);
							} else if ($("#Debit_ClearName").val() === "select") {
								$().toastmessage('showErrorToast',"Please select Credit Clear Name");
								e.preventDefault(e);
							} else if ($("#Debit_ClearAmount").val() === ""	|| $("#Debit_ClearAmount").val() === null) {
								$().toastmessage('showErrorToast',"Please enter amount");
								e.preventDefault(e);
							} else if (!numberReg.test($("#Debit_ClearAmount").val())) {
								$().toastmessage('showErrorToast',"Amount should be in Numeric  and length below 13");
								e.preventDefault(e);
							} else if ($("#Debit_ClearAmount").val() > 9000000000) {
								$().toastmessage('showErrorToast',"Amount should be less than 9 Billian Rs");
								e.preventDefault(e);
							} else if ($("#Debit_ClearAmount").val() < 1) {
								$().toastmessage('showErrorToast',"Amount should be greter than or equall 1");
								e.preventDefault(e);
							} else if ($("#Debit_ClearSource").val() === "select") {
								$().toastmessage('showErrorToast',"Please select source");
								e.preventDefault(e);
							} else if (!characterReg.test($("#Debit_ClearDes").val())) {
								$().toastmessage('showErrorToast',"Description length should be below 100, use {0-9a-zA-Z,. -}");
								e.preventDefault(e);
							} else {
								e.preventDefault(e);
								var ClearDebitDate = $("#ClearDebitDate").val();
								var inputname = $("#Debit_ClearName").val();
								var Debit_ClearAmount = $("#Debit_ClearAmount").val();
								var Debit_ClearSource = $("#Debit_ClearSource").val();
								var Debit_ClearDes = $("#Debit_ClearDes").val();
								$.ajax({	
									contentType : "application/json; charset=utf-8",
									dataType : "json",
									type : $("#formDebitClear").attr("method"),
									url : $("#formDebitClear").attr("action"),
									data : {ClearDebitDate : ClearDebitDate,
											Debit_ClearName : inputname,
											Debit_ClearAmount : Debit_ClearAmount,
											Debit_ClearSource : Debit_ClearSource,
											Debit_ClearDes : Debit_ClearDes
											},
							success : function(	response) {
									if (response === "Success") {
										showPopUpmessage("Successfully Debit updated and money add to Holder.",	"./openDebitList");
									} else {
										$().toastmessage('showErrorToast',"Error : "+response);
																}
									},
							error : function(re) {
										$().toastmessage('showErrorToast',"Error in Ajax form :"+ re);
									}
									});
							}

					});
			});


</script>

</head>
<body onload="return hidef();">
<center>
<div style="width: 60%;height:570px;border: 0px solid red;margin-top: 30px;" class="DebitEntryPage">

    <table style="width: auto;border: 0px solid red;">
      <tr>
        <td>
          <div class="headTableFloat">
            <strong>Debtor Details</strong>
              <div><button type="submit"   class="GrayBtn GrayBtn-primary">Delete</button></div>
              <div><button class="GrayBtn GrayBtn-primary" onclick="return openDebitPage();">Add</button></div>
              <div><button class="GrayBtn GrayBtn-primary" onclick="return openDebitClearPage();">Clear</button></div>
              <div><button class="GrayBtn GrayBtn-primary" onclick="return SelectMailDynamic('Direct', 'Debit');">Send email</button></div>
              <div><button class="GrayBtn GrayBtn-primary" onclick="return beforeConfirm('Debit', '2/2/2016', '2/2/2016');">Create Excel</button></div>
          </div>
        </td>
      </tr>
      <tr>
        <td>
        <form action="./hjfhdfjh" method="post" name="adminDC" id="debitListForm">
            <table border="1" id="example" class="display table table-striped table-bordered"  cellspacing="0" width="100%">
              <thead>
              <tr>
                <th style="border-right-style: hidden;"></th>
                <th style="border-right-style: hidden;">Id</th>
                <th style="border-right-style: hidden;">Date</th>
                <th style="border-right-style: hidden;">Name</th>
                <th style="border-right-style: hidden;">Amount</th>
                </tr>
              </thead>
              <tfoot>
    			<tr><th></th> <th></th> <th></th> <th>total: </th> <th></th></tr>
    			</tfoot>
              <tbody id="debitdata">

              </tbody>
            </table>
            </form>
        </td>
      </tr>
    </table>
  </div>
<div class="ClearPage" style="margin-top: 60px;">
		<h2 style="text-align: center;">Debit Clearence</h2>	
	<form action="./saveDebitClearDynamic" method="get" id="formDebitClear">
		<table align="center" class="debitClearForm">
			<tr>
				<th>Date</th>
				<td>
					<div id="datetimepickerDR" class="input-append date">
						<input type="text" name="ClearDebitDate" id="ClearDebitDate" /> 
						<span class="add-on"> 
						<i	data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>
						</span>
					</div>				
				</td>
			</tr>
			<tr>
				<th>Debtor Name</th>
				<td>
				<input type="text" name="Debit_ClearName" id="Debit_ClearName" value="" readonly/>		
				</td>
			</tr>
			<tr>
				<th>Amount</th>
				<td><input type="text" name="Debit_ClearAmount" id="Debit_ClearAmount" /></td>
			</tr>
			<tr>
				<th>Source</th>
				<td><select name="Debit_ClearSource" id="Debit_ClearSource" class="child_selection">
							<option value="select" selected>select</option>
				</select></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><textarea name="Debit_ClearDes" id="Debit_ClearDes"></textarea></td>
			</tr>
			<tr>
				<th></th>
				<td>
					<button type="submit" class="BlueBtn BlueBtn-primary">Save</button>&nbsp;|
					<button type="button" class="BlueBtn BlueBtn-primary" onclick="ClearBack();">Cancel</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</center>	
	<div id="appendPoUpMail"></div>	
</body>
</html>