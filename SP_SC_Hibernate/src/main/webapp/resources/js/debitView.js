var creditNameValid = "selectEE";

var date = new Date();
var hour = date.getHours();
var min = date.getMinutes();
var sec = date.getSeconds();
//alert(hour+"--"+min+"--"+sec);

//sessionTimeOUTT('../sessionRedir');



function hidef() {
		$("#exist").hide();
		madeAjaxCall();
		ajaxCallForSourceList();
		$("#inputS").hide();
		
		 document.getElementById("selectname").name = "Debit_Name";
         document.getElementById("Debitname").name = "fffff";
         document.getElementById("Debitname").value = "fffff";
         document.getElementById("selectname").value = "none";
         
	}
	$(function() {	
	$("#new").click(function () {
		$("#inputS").show();
		$("#selectS").hide();
		$("#new").hide();
		$("#exist").show();
		document.getElementById("Debitname").name = "Debit_Name";
        document.getElementById("selectname").name = "xcxcxcxc";
        $("#Debitname").val("");
        creditNameValid = "inputEE";
	});
	
	$("#exist").click(function (){
		$("#new").show();
		$("#inputS").hide();
		$("#selectS").show();
		$("#exist").hide();
		document.getElementById("selectname").name = "Debit_Name";
        document.getElementById("Debitname").name = "fffff";
        //$("#selectname").val("");
        $("#Debitname").val("xxx");
        creditNameValid = "selectEE";
	});
	});	

function madeAjaxCall() {
		$.ajax({
					url : "../debiterNamesList",
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
	
function ajaxCallForSourceList() {

		$.ajax({
					url : "../holderMoneyList",
					type : "GET",
					cache : false,
					dataType : "json",

					success : function(response) {
						// you could use the result.values dictionary here
						var obj = response;

						$.each(obj, function(key, name) { //populate child options
							$(".sourceList").append(
									"<option value=\""+name+"\">" + name+ "</option>");
						});

					},
					error : function(response, status, er) {
						alert("error: " + response + " status: " + status
								+ " er:" + er);
					}
				});

	}

$(document).ready(function(){
	
	
	
	    $('#Debit_Source').on('change', function() {
		    
		  	  var value = $(this).val();
		  	  var amount = $("#Debit_Amount").val();
		  	  
		  	  if(amount == "" || amount < 1){
		  		$().toastmessage('showErrorToast', "Amount should be more than 1 or equall.");
		  	  }else if(value === "select"){
		  		  	$().toastmessage('showErrorToast', "please select source.");
		  	  }else{
					$.ajax({
				        type: "GET",
				        url: "../amountAvailabilityForHolder",
				        cache : false,
						dataType : "json",
				        data: { source: value, amount: amount }, // parameters
							success : function(response) {
								// you could use the result.values dictionary here
								 
								obj = response;
								formSubCon145 = obj;
								if(formSubCon145 === "Success"){
									
									$().toastmessage('showSuccessToast', "available");
								}else{
									$().toastmessage('showErrorToast', "not available");
								}
							},
							error : function(response, status, er) {
								showAjaxErrorToast();
							}
				    
				   });
		    }
	    });
	   
	  
	$('#datetimepicker').datetimepicker({
		    format: 'MM/dd/yyyy hh:mm:ss',
		    language: 'en'
	});
		
		
	$("#formDebit").submit(function(e){
		 var numberReg = /^[0-9.]{0,13}$/;
      	 var characterReg = /^[a-zA-Z0-9., ]{0,150}$/;
      	 var characterRegInp = /^[a-zA-Z0-9_ ]{0,150}$/;
      	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
      	if($("#Debit_Date").val() === ""){
      		$().toastmessage('showErrorToast', "Please enter Date");
   	    	e.preventDefault(e);
   		}else if(creditNameValid == "inputEE"){
      	
   			if($("#Debitname").val() === ""){
   				$().toastmessage('showErrorToast', "Please enter debiter name");
   				e.preventDefault(e);
   			}else if(!characterRegInp.test($("#Debitname").val())){
   	    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z_}");
   		        e.preventDefault(e);
   			}else if($("#Debit_Amount").val() === "" || $("#Debit_Amount").val() === null){
   	   			$().toastmessage('showErrorToast', "Please enter amount");
   	   	    	e.preventDefault(e);
   	   		}else if(!numberReg.test($("#Debit_Amount").val())) {
   	    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
   	    	    e.preventDefault(e);
   	    	}else if($("#Debit_Amount").val() > 9000000000) {
   	    		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
   	    	    e.preventDefault(e);
   	    	}else if($("#Debit_Amount").val() < 1) {
   	    		$().toastmessage('showErrorToast', "Amount should be greter than or equall 1");
   	    	    e.preventDefault(e);
   	    	}else if($("#Debit_Source").val() === "select") {
   	    		$().toastmessage('showErrorToast', "Please select source");
   	    	    e.preventDefault(e);
   	    	}else if(!characterReg.test($("#Debit_Description").val())){
   	    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
   		        e.preventDefault(e);
   			}else{
   	    		e.preventDefault(e);
   	    		var Debit_Date = $("#Debit_Date").val();
   	        	var inputname = $("#Debitname").val();
   	        	var Debit_Amount = $("#Debit_Amount").val();
   	        	var Debit_Source = $("#Debit_Source").val();
   	        	var Debit_Description = $("#Debit_Description").val();
   	        	
   	        	$.ajax({
   	                contentType: "application/json; charset=utf-8",
   	                dataType: "json",
   	                type: $("#formDebit").attr("method"),
   	                url:  $("#formDebit").attr("action"),
   	                data:{Debit_Date: Debit_Date, Debit_name: inputname, Debit_Amount: Debit_Amount, Debit_Source: Debit_Source, Debit_Description: Debit_Description}, 
   	                success : function(response){		//new entry   	                	
   	                	if(response === "none"){
   	                		$().toastmessage('showErrorToast', "Save Unsuccessfull");
   	                	}else if(response === "ErrorAmountLess"){
   	                		$().toastmessage('showErrorToast', "Save Unsuccessfull due to less amount");
   	                	}else if(response === "SuccessCreditDeductNewRecordSave"){
   	                		showPopUpmessage("Successfully create new person and deduct from crediter and save record.", "../openDebitList");
   	                	}else if(response === "SuccessDeductHolderNewRecordSave"){
   	                		showPopUpmessage("Successfully create new person,  deduct from holder and Save record.", "../openDebitList");
   	                	}
   	                },
   	                error: function (re) {
   	                	$().toastmessage('showErrorToast', "Error in Ajax form");
   	                }
   	            });
   			}
   		}else if(creditNameValid == "selectEE"){
   			if($("#selectname").val() === null){
   				$().toastmessage('showErrorToast', "Please select debiter name");
   				e.preventDefault(e);
   			}else if($("#selectname").val() === "select" || $("#selectname").val() === ""){
   				$().toastmessage('showErrorToast', "Please select debiter name");
   				e.preventDefault(e);
   			}else if($("#Debit_Amount").val() === "" || $("#Debit_Amount").val() === null){
   	   			$().toastmessage('showErrorToast', "Please enter amount");
   	   	    	e.preventDefault(e);
   	   		}else if(!numberReg.test($("#Debit_Amount").val())) {
   	    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
   	    	    e.preventDefault(e);
   	    	}else if($("#Debit_Amount").val() > 9000000000) {
   	    		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
   	    	    e.preventDefault(e);
   	    	}else if($("#Debit_Amount").val() < 1) {
   	    		$().toastmessage('showErrorToast', "Amount should be greter than or equall 1");
   	    	    e.preventDefault(e);
   	    	}else if($("#Debit_Source").val() === "select") {
   	    		$().toastmessage('showErrorToast', "Please select source");
   	    	    e.preventDefault(e);
   	    	}else if(!characterReg.test($("#Debit_Description").val())){
   	    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
   		        e.preventDefault(e);
   			}else{
   				e.preventDefault(e);
   	    		var debit_Date = $("#Debit_Date").val();
   	        	var selectname = $("#selectname").val();
   	        	var Debit_Amount = $("#Debit_Amount").val();
   	        	var Debit_Source = $("#Debit_Source").val();
   	        	var Debit_Description = $("#Debit_Description").val();
   	        	$.ajax({
   	                contentType: "application/json; charset=utf-8",
   	                dataType: "json",
   	                type: $("#formDebit").attr("method"),
   	                url:  $("#formDebit").attr("action"),
   	                data:{Debit_Date: debit_Date, Debit_name: selectname, Debit_Amount: Debit_Amount, Debit_Source: Debit_Source, Debit_Description: Debit_Description}, 
   	                success : function(response){
   	                	if(response === "none"){
   	                		$().toastmessage('showErrorToast', "Save Unsuccessfull");
   	                	}else if(response === "ErrorAmountLess"){
   	                		$().toastmessage('showErrorToast', "Save Unsuccessfull due to less amount");
   	                	}else if(response === "SuccessCreditDeductUpdateRecordSave"){   	                		
   	                		showPopUpmessage("Successfully update and deduct from crediter and save record.", "../openDebitList");
   	                	}else if(response === "SuccessDeductHolderUpdateRecordSave"){   	                		
   	                		showPopUpmessage("Successfully update and deduct from holder and save record.", "../openDebitList");
   	                	}
   	                },
   	                error: function (re) {
   	                	$().toastmessage('showErrorToast', "Error in Ajax form");
   	                }
   	            });
   			}
   			
   		}  	
    });	
});