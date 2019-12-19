
var creditNameValid = "selectEE";
function hidef() {
		$("#exist").hide();
		madeAjaxCall();
		$("#inputS").hide();
		
		 document.getElementById("selectname").name = "Credit_Name";
         document.getElementById("inputname").name = "fffff";
         document.getElementById("inputname").value = "fffff";
         document.getElementById("selectname").value = "none";
         
	}
	$(function() {	
	$("#new").click(function () {
		$("#inputS").show();
		$("#selectS").hide();
		$("#new").hide();
		$("#exist").show();
		document.getElementById("inputname").name = "Credit_Name";
        document.getElementById("selectname").name = "xcxcxcxc";
        $("#inputname").val("");
        creditNameValid = "inputEE";
	});
	
	$("#exist").click(function (){
		$("#new").show();
		$("#inputS").hide();
		$("#selectS").show();
		$("#exist").hide();
		document.getElementById("selectname").name = "Credit_Name";
        document.getElementById("inputname").name = "fffff";
        //$("#selectname").val("");
        $("#inputname").val("xxx");
        creditNameValid = "selectEE";
	});
	});	

	function madeAjaxCall() {
		$.ajax({
					url : "../crediterNamesList",
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
$(document).ready(function(){	
	
	$('#datetimepickerCred').datetimepicker({
	    format: 'MM/dd/yyyy hh:mm:ss',
	    //pickTime: false,
	    language: 'en'
	});
	
	$("#formCredit").submit(function(e){
		 var numberReg = /^[0-9.]{0,13}$/;
      	 var characterReg = /^[a-zA-Z0-9., ]{0,150}$/;
      	var characterRegInp = /^[a-zA-Z0-9 ]{0,150}$/;
      	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
      	if($("#Credit_Date").val() === ""){
      		$().toastmessage('showErrorToast', "Please enter Date");
   	    	e.preventDefault(e);
   		}else if(creditNameValid == "inputEE"){
      	
   			if($("#inputname").val() === ""){
   				$().toastmessage('showErrorToast', "Please enter crediter name");
   				e.preventDefault(e);
   			}else if(!characterRegInp.test($("#inputname").val())){
   	    		$().toastmessage('showErrorToast', "Crediter name length should be below 100, use {0-9a-zA-Z}");
   		        e.preventDefault(e);
   			}else if($("#Credit_Amount").val() === "" || $("#Credit_Amount").val() === null){
   	   			$().toastmessage('showErrorToast', "Please enter amount");
   	   	    	e.preventDefault(e);
   	   		}else if(!numberReg.test($("#Credit_Amount").val())) {
   	    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_Amount").val() > 9000000000) {
   	    		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_Amount").val() < 1) {
   	    		$().toastmessage('showErrorToast', "Amount should be greter than or equall 1");
   	    	    e.preventDefault(e);
   	    	}else if(!characterReg.test($("#Credit_Description").val())){
   	    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
   		        e.preventDefault(e);
   			}else{
   	    		e.preventDefault(e);
   	    		var Credit_Date = $("#Credit_Date").val();
   	        	var inputname = $("#inputname").val();
   	        	var Credit_Amount = $("#Credit_Amount").val();
   	        	var Credit_Description = $("#Credit_Description").val();
   	        	
   	        	$.ajax({
   	                contentType: "application/json; charset=utf-8",
   	                dataType: "json",
   	                type: $("#formCredit").attr("method"),
   	                url:  $("#formCredit").attr("action"),
   	                data:{Credit_Date: Credit_Date, creditname: inputname, Credit_Amount: Credit_Amount, Credit_Description: Credit_Description}, 
   	                success : function(response){
   	                	if(response === "SaveSuccessAsNew"){
   	                		showPopUpmessage("Successfully create new crediter, add amount and save record.", "../openCreditList");
   	                	}else if(response === "Error"){
   	                		$().toastmessage('showErrorToast', "Save Unsuccessfull");
   	                	}else if(response === "SaveSuccessAsExist"){
   	                		showPopUpmessage("Successfully add amount to exist crediter and save record.", "../openCreditList");
   	                	}
   	                },
   	                error: function (re) {
   	                	$().toastmessage('showErrorToast', "Error in Ajax form");
   	                }
   	            });
   			}
   		}else if(creditNameValid == "selectEE"){
   			if($("#selectname").val() === null){
   				$().toastmessage('showErrorToast', "Please select crediter name");
   				e.preventDefault(e);
   			}else if($("#selectname").val() === "select" || $("#selectname").val() === ""){
   				$().toastmessage('showErrorToast', "Please select crediter name");
   				e.preventDefault(e);
   			}else if($("#Credit_Amount").val() === "" || $("#Credit_Amount").val() === null){
   	   			$().toastmessage('showErrorToast', "Please enter amount");
   	   	    	e.preventDefault(e);
   	   		}else if(!numberReg.test($("#Credit_Amount").val())) {
   	    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 11");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_Amount").val() > 9000000000) {
   	    		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_Amount").val() < 1) {
   	    		$().toastmessage('showErrorToast', "Amount should be greter than or equall 1");
   	    	    e.preventDefault(e);
   	    	}else if(!characterReg.test($("#Credit_Description").val())){
   	    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
   		        e.preventDefault(e);
   			}else{
   				e.preventDefault(e);
   	    		var Credit_Date = $("#Credit_Date").val();
   	        	var selectname = $("#selectname").val();
   	        	var Credit_Amount = $("#Credit_Amount").val();
   	        	var Credit_Description = $("#Credit_Description").val();
   	        	
   	        	$.ajax({
   	                contentType: "application/json; charset=utf-8",
   	                dataType: "json",
   	                type: $("#formCredit").attr("method"),
   	                url:  $("#formCredit").attr("action"),
   	                data:{Credit_Date: Credit_Date, creditname: selectname, Credit_Amount: Credit_Amount, Credit_Description: Credit_Description}, 
   	                success : function(response){
   	                	if(response === "SaveSuccessAsExist"){
   	                		showPopUpmessage("Successfully add amount to exist crediter and save record.", "../openCreditList");
   	                	}else if(response === "Error"){
   	                		$().toastmessage('showErrorToast', "Save Unsuccessfull");
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