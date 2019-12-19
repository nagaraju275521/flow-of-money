$(document).ready(function(){
	 

		$('#datetimepickerCCD').datetimepicker({
			format: 'MM/dd/yyyy hh:mm:ss',
		    pickTime: true,
		    language: 'en'
		    
		});
	
	
	
    $('#Credit_ClearSource').on('change', function() {
	    
	  	  var value = $(this).val();
	  	  var amount = $("#Credit_ClearAmount").val();
	  	  
	  	  if(amount == "" || amount < 1){
	  		$().toastmessage('showErrorToast', "Amount should be more than 1 or equall.");
	  	  }else if(value === "select"){
	  		  	$().toastmessage('showErrorToast', "please select source.");
	  	  }else if(value === $("#Credit_ClearName").val()){
	  		  	$().toastmessage('showErrorToast', "Credit Clear Name and source name should not be same.");
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
    
    $('#Credit_ClearName').on('change', function() {
	    
	  	  var value = $(this).val();
	  	  if(value === "select"){
	  		  	$().toastmessage('showErrorToast', "please select Credit Clear Name.");
	  	  }
		});
    
      
    });

function hidef() {
	madeAjaxCall();
	ajaxCallForSourceList();     
	}
	


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
	$("#formCreditClear").submit(function(e){
		 var numberReg = /^[0-9.]{0,13}$/;
      	 var characterReg = /^[a-zA-Z0-9., -]{0,150}$/;
      	 var characterRegInp = /^[a-zA-Z0-9]{0,100}$/;
      	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
      		if($("#Credit_ClearDate").val() === ""){
      		$().toastmessage('showErrorToast', "Please enter Date");
   	    	e.preventDefault(e);
      		}else if($("#Credit_ClearName").val() === ""){
   				$().toastmessage('showErrorToast', "Please enter debiter name");
   				e.preventDefault(e);
   			}else if($("#Credit_ClearName").val() === "select") {
   	    		$().toastmessage('showErrorToast', "Please select Credit Clear Name");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_ClearAmount").val() === "" || $("#Credit_ClearAmount").val() === null){
   	   			$().toastmessage('showErrorToast', "Please enter amount");
   	   	    	e.preventDefault(e);
   	   		}else if(!numberReg.test($("#Credit_ClearAmount").val())) {
   	    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_ClearAmount").val() > 9000000000) {
   	    		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_ClearAmount").val() < 1) {
   	    		$().toastmessage('showErrorToast', "Amount should be greter than or equall 1");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_ClearSource").val() === "select") {
   	    		$().toastmessage('showErrorToast', "Please select source");
   	    	    e.preventDefault(e);
   	    	}else if($("#Credit_ClearSource").val() === $("#Credit_ClearName").val()) {
   	    		$().toastmessage('showErrorToast', "Please verify.... Credit Clear Name and source name should not be same");
   	    	    e.preventDefault(e);
   	    	}else if(!characterReg.test($("#Credit_ClearDes").val())){
   	    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,. -}");
   		        e.preventDefault(e);
   			}else{
   	    		e.preventDefault(e);
   	    		var Credit_ClearDate = $("#Credit_ClearDate").val();
   	        	var inputname = $("#Credit_ClearName").val();
   	        	var Credit_ClearAmount = $("#Credit_ClearAmount").val();
   	        	var Credit_ClearSource = $("#Credit_ClearSource").val();
   	        	var Credit_ClearDes = $("#Credit_ClearDes").val();
   	        	
   	        	$.ajax({
   	                contentType: "application/json; charset=utf-8",
   	                dataType: "json",
   	                type: $("#formCreditClear").attr("method"),
   	                url:  $("#formCreditClear").attr("action"),
   	                data:{Credit_ClearDate: Credit_ClearDate, CreditClear_name: inputname, Credit_ClearAmount: Credit_ClearAmount, Credit_ClearSource: Credit_ClearSource, Credit_ClearDes: Credit_ClearDes}, 
   	                success : function(response){
   	                	if(response === "SuccessCredit"){
   	                		showPopUpmessage("Successfully clear Credit and amount updated", "../openCreditList");
   	                	}else if(response === "SuccessHolder"){
   	                		showPopUpmessage("Successfully clear Credit and Holder amount updated", "../openCreditList");
   	                	}else{
   	                		$().toastmessage('showErrorToast', "Error: "+response);
   	                	}
   	                },
   	                error: function (re) {
   	                	$().toastmessage('showErrorToast', "Error in Ajax form :"+re);
   	                }
   	            });
   			}
   			
    });	
});	