<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FlowOfMoney</title>



<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

 <link rel="stylesheet" href="<c:url value="/resources/css/expensesStyle.css" />" />
<script src="<c:url value="/resources/js/jquery.weekpicker.js" />"></script>

 <link rel="stylesheet" href="<c:url value="/resources/css/jquery.toastmessage.css" />" />
	<script src="<c:url value="/resources/js/jquery.toastmessage-min.js" />"></script>
       
    

<script type="text/javascript">
alert("hgfhfgjhgj");
$(document).ready(function() {
    function showSuccessToast() {
        $().toastmessage('showSuccessToast', "Available sufficient money");
    }
    function showStickySuccessToast() {
        $().toastmessage('showToast', {
            text     : 'Success Dialog which is sticky',
            sticky   : true,
            position : 'top-right',
            type     : 'success',
            closeText: '',
            close    : function () {
                console.log("toast is closed ...");
            }
        });

    }
    function showStickySelectToast() {
        $().toastmessage('showToast', {
            text     : 'Please select Source',
            sticky   : false,
            position : 'top-right',
            type     : 'error',
            closeText: '',
            close    : function () {
                console.log("toast is closed ...");
            }
        });

    }
    function showAjaxErrorToast() {
        $().toastmessage('showToast', {
            text     : 'No object recived from controller or ajax error.',
            sticky   : false,
            position : 'top-right',
            type     : 'error',
            closeText: '',
            close    : function () {
                console.log("toast is closed ...");
            }
        });

    }

    function showNoticeToast() {
        $().toastmessage('showNoticeToast', "Notice  Dialog which is fading away ...");
    }
    function showStickyNoticeToast() {
        $().toastmessage('showToast', {
             text     : 'Notice Dialog which is sticky',
             sticky   : true,
             position : 'top-right',
             type     : 'notice',
             closeText: '',
             close    : function () {console.log("toast is closed ...");}
        });
    }
    function showWarningToast() {
        $().toastmessage('showWarningToast', "Warning Dialog which is fading away ...");
    }
    function showStickyWarningToast() {
        $().toastmessage('showToast', {
            text     : 'Warning Dialog which is sticky',
            sticky   : true,
            position : 'top-right',
            type     : 'warning',
            closeText: '',
            close    : function () {
                console.log("toast is closed ...");
            }
        });
    }
    function showErrorToast() {
        $().toastmessage('showErrorToast', "There is not available sufficient money");
    }
    function showStickyErrorToast() {
        $().toastmessage('showToast', {
            text     : 'There is not available sufficient money',
            sticky   : true,
            position : 'top-right',
            type     : 'error',
            closeText: '',
            close    : function () {
                console.log("toast is closed ...");
            }
        });
    }

	function hidef() {

		madeAjaxCall();
	}
	
	function madeAjaxCall() {

		$.ajax({
					url : "./holderMoneyList",
					type : "GET",
					cache : false,
					dataType : "json",

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

	function sendbackTwo() {
		window.open("../expensesList", "_self");
	}
});
	$(function() {

		$('.week-picker').weekpicker();
	});

	

	$(function() {
		var availableTags = [ "Ticket", "Travelling", "Food", "Tea", "Movie", "Internet", "Mobile", "Vehicle", "Electronics",
			"Etc", "Arnaments", "Clothes", "Books", "Wood", "Jym"];
		
		var unitNameTags = ["Fruit", "Tea", "Non-veg", "Ticket", "Vegitables", "Rice", "Shorts", "Ice", "CoolDrinks",
		                    "Biryani", "Shampo", "Train", "Bus", "taxi", "Auto", "Mobile"];
		
		var unit_name = ["Banana", "Apple", "Mirchi", "Black Tea", "Lemon", "Rice", "Orange", "Green Tea", "Chai", "Milk",
		                "Palakura", "sari", "Ice cream", "Chicken", "Mutton", "booti", "goat head", "Dall", "H&D", "Meera",
		                "Onion", "MenThem", "Eggs", "mmts", "passenger", "Metro", "Express", "Recharge", "Repaire", "Purchase"];
		
		$("#unitTypeAuto").autocomplete({
			source : availableTags
		});
		
		$("#unitNameTagAuto").autocomplete({
			source : unitNameTags
		});
		
		$("#unitNameAuto").autocomplete({
			source : unit_name
		});
	});




	$(document).ready(function() {

				//let's create arrays
				var cash = [ {
					display : "Pocket",
					value : "pocket"
				}, {
					display : "money",
					value : "money"
				}, {
					display : "White chocolate",
					value : "white-chocolate"
				}, {
					display : "Gianduja chocolate",
					value : "gianduja-chocolate"
				} ];

				var debitcard = [ {
					display : "Syndicate Bank",
					value : "SyndicateBank"
				}, {
					display : "SBI",
					value : "SBI"
				}, {
					display : "SBH",
					value : "SBH"
				}, {
					display : "ICICI",
					value : "ICICI"
				} ];

				var creditcard = [ {
					display : "visa",
					value : "visa"
				}, {
					display : "SBI",
					value : "SBI"
				}, {
					display : "ICICI",
					value : "ICICI"
				}, {
					display : "HDFC",
					value : "HDFC"
				} ];

				var check = [

				{
					display : "Check",
					value : "check"
				} ];

				//If parent option is changed
				$("#parent_selection").change(function() {
					var parent = $(this).val(); //get option value from parent

					switch (parent) { //using switch compare selected option and populate child
					case 'cash':
						list(cash);
						break;
					case 'debitcard':
						list(debitcard);
						break;
					case 'creditcard':
						list(creditcard);
						break;
					case 'check':
						list(check);
						break;
					default: //default child option is blank
						$("#child_selection").html('');
						break;
					}
				});

				//function to populate child select box
				function list(array_list) {
					$("#child_selection").html(""); //reset child options
					$(array_list).each(
							function(i) { //populate child options
								$("#child_selection").append(
										"<option value=\""+array_list[i].value+"\">"
												+ array_list[i].display
												+ "</option>");
							});
				}

			});



$(document).ready(function(){
    var formSubCon1 = "";
    var checking ="";
    var selectValue;
    $('#selsource').on('change', function() {
    selectValue = $(this).val();
  	  var value = $(this).val();
  	  var amount = $("#Amount").val();
  	  if(amount == "" || amount == 0){
  		$().toastmessage('showErrorToast', "Please enter amount");
  	  }else if(value === "select"){
			showStickySelectToast();
		}else{
			$.ajax({
		        type: "GET",
		        url: "../amountAvailability",
		        cache : false,
				dataType : "json",
		        data: { source: value, amount: amount }, // parameters
					success : function(response) {
						// you could use the result.values dictionary here
						 
						obj = response;
						formSubCon1 = obj;
						//alert("222 "+formSubCon1);
						if(formSubCon1 === "Success"){
							showSuccessToast();
						}else{
							showErrorToast();
						}
					},
					error : function(response, status, er) {
						showAjaxErrorToast();
					}
		    
		   });
    }
	});
    
    
    $("form").submit(function(e){
    	 var numberReg = /^[0-9.]{0,11}$/;
    	 var characterReg = /^[a-zA-Z0-9., ]{0,100}$/;
    	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
    	if($("#Date").val() === ""){
    	    $().toastmessage('showErrorToast', "Please enter date");
    	    e.preventDefault(e);
    	}else if(!characterReg.test($("#unitTypeAuto").val())){
    		$().toastmessage('showErrorToast', "Unit type length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if($("#unitNameAuto").val() === ""){
    		$().toastmessage('showErrorToast', "Please enter unit name");
	        e.preventDefault(e);
		}else if(!characterReg.test($("#unitNameAuto").val())){
    		$().toastmessage('showErrorToast', "Unit name length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if(!characterReg.test($("#unitNameTagAuto").val())){
    		$().toastmessage('showErrorToast', "Tag length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if(!numberReg.test($("#Unit_price").val())) {
    		$().toastmessage('showErrorToast', "unit price should be in Numeric and length below 11 including Decimal");
    	    e.preventDefault(e);
    	}else if(!characterReg.test($("#Quantity").val())){
    		$().toastmessage('showErrorToast', "Quantity should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if($("#Amount").val() === "" || $("#Amount").val() == 0){
    		$().toastmessage('showErrorToast', "Please enter amount");
	        e.preventDefault(e);
		}else if(!numberReg.test($("#Amount").val())) {
    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 11");
    	    e.preventDefault(e);
    	}else if($("#Amount").val() > 9999999) {
    		$().toastmessage('showErrorToast', "Amount should be less than 9999999");
    	    e.preventDefault(e);
    	}else if($("#selsource").val() === "select"){
			$().toastmessage('showErrorToast', "Please select source");
    	    e.preventDefault(e);
    	}else if(!characterReg.test($("#Sold_company").val())){
    		$().toastmessage('showErrorToast', "Company length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if(!characterReg.test($("#Area").val())){
    		$().toastmessage('showErrorToast', "Area length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if(!characterReg.test($("#Whome_to").val())){
    		$().toastmessage('showErrorToast', "Whome-to length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if(!characterReg.test($("#Description").val())){
    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if(formSubCon1 === "Error"){
      		 	showErrorToast();
        		e.preventDefault(e);
    	}else{
    			showSuccessToast();
    	}
    });
    formSubCon1 = "";
    checking = "";
});
</script>
</head>
<body onload="return hidef();">
	<div style="text-align: center; margin-top: 20px;">
		<Strong>Expenses</Strong>
<div style="text-align: center; margin-top: 20px;">
		<form action="../addExpenses" method="post">
			<table style="padding-left:45px;width: 50%; margin: 0px auto;" class="">
				<tr><th>Date*</th><th>Unit Type</th></tr>
							<tr>
								<td><input type="text" name="Date" id="Date" class="week-picker" /></td>
								<td><input type="text" name="Unit_Type" id="unitTypeAuto" /></td>
							</tr>
							<tr><th>Unit name*</th><th>Tag</th></tr>	
							<tr>
								<td><input type="text" name="Unit_Name" id="unitNameAuto" /></td>
								<td><input type="text" name="Unit_Tag" id="unitNameTagAuto" /></td>
							</tr>
							<tr><th>Unit Price</th><th>Quantity</th></tr>
							<tr>
								<td><input type="text" name="Unit_price" id="Unit_price" /></td>
								<td><input type="text" name="Quantity" id="Quantity" /></td>
							</tr>
							<tr><th>Amount*</th><th>Source*</th></tr>
							<tr>
								<td><input type="text" name="Amount" id="Amount" /></td>
								<td><select name="Source_Name" class="child_selection" id="selsource">
										<option value="select">Select</option>
									</select></td>
								
							</tr>
							<tr><th>Sold Company</th><th>Area</th></tr>
							<tr>
							<td><input type="text" name="Sold_company" id="Sold_company"  /></td>
							<td><input type="text" name="Area" id="Area"  /></td>
							</tr>
								<input type="hidden" name="User" value="null" />
							<tr><th>Whoom</th><th>Description</th></tr>
							<tr>
								<td><input type="text" name="Whome_to" id="Whome_to" value="me"/></td>
								<td><textarea name="Description" id="Description"></textarea></td>
							</tr>
						</table>
		
		<table style="width: 100%;">
						<tr>
								
								<td style="text-align: center;"><button type="submit">Save</button>&nbsp;|&nbsp;
									<button type="button" onclick="return sendbackTwo();">Cancel</button>
								</td>
							</tr>
						</table>
		</form>
	</div></div>
</body>
</html>