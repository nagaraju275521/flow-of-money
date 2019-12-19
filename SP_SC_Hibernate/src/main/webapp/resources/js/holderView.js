 function hidef(){
      madeAjaxCall();
      //alert("calling");
    }

var  formSubCon145 ="";
var  ErrorNewHolder = "";
$(document).ready(function() {
	
	$('#datetimepicker').datetimepicker({
	    format: 'MM/dd/yyyy hh:mm:ss',
	    language: 'en'
	});
	
    $("#myButton").click(function() {
        $("#formPopUp").submit();
    });
    
    $('#Holder_Type123').on('change', function() {
   	   $("#Holder_Name123").val("");
   	   if($(this).val() === "select"){
   		$().toastmessage('showErrorToast', "Please select holder type");
   	   }
   		   
    });	
    
    $("#Holder_Name123").keyup(function(){
 	  $("span").html($("#Holder_Name123").val());
      var holder_type = $("#Holder_Type123").val();
      var holder_name = $("#Holder_Name123").val();
      if(holder_type === "select"){
    	  $().toastmessage('showErrorToast', "Please select holder type");
      }else{
       $.ajax({
	        type: "GET",
	        url: "../availabilityHolderName",
	        cache : false,
			dataType : "json",
	        data: { holdertype: holder_type, holdername:holder_name}, // parameters
				success : function(response) {
					// you could use the result.values dictionary here
					 
					obj = response;
					var nnn = ""; // JSON.stringify();
					$.each(obj, function (key, value) { //populate child options
						nnn = value;
			          });
					
					var xxx = holder_name.toLowerCase();
					if(xxx.toLowerCase() == nnn.toLowerCase()){
							ErrorNewHolder = "ErrorHolder";
							$("span").css({"color":"red"});
					}else{
						ErrorNewHolder = "";
						$("span").css({"color":"green"});
					}

			   },
				error : function(response, status, er) {
					showAjaxErrorToast();
				}
	    
	   });
    }
    });
    
    

      
      $('#Holder_Source').on('change', function() {
    	    
    	  	  var value = $(this).val();
    	  	  var amount = $("#Holder_Amount").val();
    	  	  
    	  	  if(amount == "" || amount == 0){
    	  		$().toastmessage('showErrorToast', "Amount should be more than 0.");
    	  	  }else if(value === "select"){
    	  		  	$().toastmessage('showErrorToast', "please select source.");
    	  	  }else if(value === $("#child_selection").val()){
  	  		  	$().toastmessage('showErrorToast', "Never transfer money between same holder");
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
      $('#parent_selection').on('change', function() {
  	    
	  	  var value = $(this).val();
	  	 	//alert(value);  	  
				$.ajax({
			        type: "GET",
			        url: "../loadHolderNames",
			        cache : false,
					dataType : "json",
			        data: { banktype: value}, // parameters
						success : function(response) {
							// you could use the result.values dictionary here
							 
							obj = response;
							$("#child_selection").html("");
							$.each(obj, function (key, value) { //populate child options
								$("#child_selection").append("<option value=\""+value+"\">"+value+"</option>");
					          });
						},
						error : function(response, status, er) {
							showAjaxErrorToast();
						}
			    
			   });
	    
		});  	  
      $("#form123").submit(function(e){
    	 
     	 var numberReg = /^[0-9.]{0,13}$/;
     	 var characterReg = /^[a-zA-Z0-9., ]{0,100}$/;
     	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
     	if($("#Holder_Date").val() === ""){
     	    $().toastmessage('showErrorToast', "Please enter date");
     	    e.preventDefault(e);
     	}else if($("#parent_selection").val() === "select"){
			$().toastmessage('showErrorToast', "Please select holder type");
    	    e.preventDefault(e);
    	}else if($("#child_selection").val() === "null" || $("#child_selection").val() === "none"){
			$().toastmessage('showErrorToast', "Please select holder name");
    	    e.preventDefault(e);
    	}else if($("#Holder_Amount").val() === ""){
    		$().toastmessage('showErrorToast', "Please enter amount");
	        e.preventDefault(e);
		}else if($("#Holder_Amount").val() == 0){
    		$().toastmessage('showErrorToast', "Amount should be greter than 0");
	        e.preventDefault(e);
		}else if(!numberReg.test($("#Holder_Amount").val())) {
    		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
    	    e.preventDefault(e);
    	}else if($("#Holder_Amount").val() > 9000000000) {
    		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
    	    e.preventDefault(e);
    	}else if(!characterReg.test($("#Holder_Description").val())){
    		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
	        e.preventDefault(e);
		}else if($("#Holder_Source").val() === "select") {
    		$().toastmessage('showErrorToast', "Please select source");
    	    e.preventDefault(e);
    	}else if($("#Holder_Source").val() === $("#child_selection").val()) {
    		$().toastmessage('showErrorToast', "Never transfer money between same holder");
    	    e.preventDefault(e);
    	}else if(formSubCon145 === "Error" ){
    		$().toastmessage('showErrorToast', "Amount not available");
    		e.preventDefault(e);
    	}else{
    		e.preventDefault(e);
    		var Holder_Date = $("#Holder_Date").val();
        	var parent_selection = $("#parent_selection").val();
        	var child_selection = $("#child_selection").val();
        	var Holder_Amount = $("#Holder_Amount").val();        	
        	var Description = $("#Holder_Description").val();
        	var Holder_Source = $("#Holder_Source").val();
        	$.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: $("#form123").attr("method"),
                url:  $("#form123").attr("action"),
                data: {Holder_Date: Holder_Date, parent_selection: parent_selection, child_selection: child_selection, Holder_Amount: Holder_Amount, 
                	Description: Description, Holder_Source: Holder_Source },
                	
                success : function(response){
                	if(response === "SuccessCreditToHolder"){
                		showPopUpmessage("Successfully deduct from Credit and add to Holder, save record.", "../openHolderList");
                	}else if(response === "BankToBankPocket"){
                		showPopUpmessage("Successfully complete transaction between banks and Save record.", "../openHolderList");
                	}else if(response === "SuccessHolderNewEntry"){
                		showPopUpmessage("Successfully create new Holder and Save record.", "../openHolderList");
                	}else if(response === "SuccessSourceToHolder"){
                		showPopUpmessage("Successfully amount deduct from source,amount add to Holder and Save record.", "../openHolderList");
                	}else if(response === "Error"){
                		$().toastmessage('showErrorToast', "Save Unsuccessfull");
                	}else if(response === "ErrorAmountLess"){
                		$().toastmessage('showErrorToast', "Save Unsuccessfull. Because there is no amount");
                	}
                },
                error: function (re) {
                	$().toastmessage('showErrorToast', "Error in Ajax form");
                }
            });
        	
    		

    	}   
      });
     
      $("#formPopUp").submit(function(e){
    
      	 var numberReg = /^[0-9.]{0,11}$/;
      	 var characterReg = /^[a-zA-Z0-9., ]{0,100}$/;
      	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
      	if($("#Holder_Date123").val() === ""){
      	    $().toastmessage('showErrorToast', "Please enter date");
      	    e.preventDefault(e);
      	}else if($("#Holder_Type123").val() === "select"){
 			$().toastmessage('showErrorToast', "Please select holder type");
     	    e.preventDefault(e);
     	}else if($("#Holder_Name123").val() === ""){
 			$().toastmessage('showErrorToast', "Please enter holder name");
     	    e.preventDefault(e);
     	}else if(ErrorNewHolder === "ErrorHolder"){
 			$().toastmessage('showErrorToast', "Please choose another name");
     	    e.preventDefault(e);
     	}else{
     		e.preventDefault(e);
    		var Holder_Date = $("#Holder_Date123").val();
        	var parent_selection = $("#Holder_Type123").val();
        	var child_selection = $("#Holder_Name123").val();
        	var Holder_Amount = $("#Holder_Amount123").val();        	
        	var Description = $("#Holder_Description123").val();
        	var Holder_Source = $("#Holder_Source123").val();
        	$.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: $("#formPopUp").attr("method"),
                url:  $("#formPopUp").attr("action"),
                data: {Holder_Date: Holder_Date, parent_selection: parent_selection, child_selection: child_selection, Holder_Amount: Holder_Amount, 
                	Description: Description, Holder_Source: Holder_Source },
                	
                success : function(response){
                	if(response === "SuccessHolderNewEntry"){
                		showPopUpmessage("Successfully create new Holder and Save record.", "../openHolderList");
                	}else if(response === "Error"){
                		$().toastmessage('showErrorToast', "Save Unsuccessfull");
                	}else if(response === "ErrorAmountLess"){
                		$().toastmessage('showErrorToast', "Save Unsuccessfull. Because there is no amount");
                	}
                },
                error: function (re) {
                	$().toastmessage('showErrorToast', "Error in Ajax form"+re);
                }
            });
        	
    		


     	}   
       });
      
    });

    function madeAjaxCall(){

      $.ajax({
        url: "../holderlistOnly",
        type: "GET",
        cache: false,
        dataType: "json",

        success: function (response) {
          // you could use the result.values dictionary here
          var obj = response;


          $.each(obj, function (key,value) { //populate child options
            $(".source_selection").append("<option value=\""+value+"\">"+value+"</option>");
          });

        },
        error:function(response,status,er) {
          alert("error: "+response+" status: "+status+" er:"+er);
        }
      });


    }
    ErrorNewHolder = "";