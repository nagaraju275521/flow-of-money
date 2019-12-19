var validforError = "selectE";
var ajaxMessage = "";
function hidef(){
	
    madeAjaxCall();
    $(function(){
    	$("#showOld").hide();
        $("#input").hide();

        var x = document.getElementById("myText").name = "SourceName";
        var y = document.getElementById("texxt").name = "fffff";
        $("#texxt").val("fghhgh");

    });
}
function showSuccessToast() {
        $().toastmessage('showSuccessToast', "success");
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


        $(function() {
            
            $("#showNew").click(function () {
            	$("#showNew").hide();
            	$("#showOld").show();
            	$("#input").show();
                $("#select").hide();

                document.getElementById("texxt").name = "SourceName";
                document.getElementById("myText").name = "xcxcxcxc";
                validforError = "inputE";
                $("#texxt").val("");
            });
            $("#showOld").click(function () {
            	$("#showNew").show();
            	$("#showOld").hide();
            	$("#select").show();
            	$("#input").hide();
            	 document.getElementById("myText").name = "SourceName";
                 document.getElementById("texxt").name = "fffff";
                 $("#texxt").val("ddd");
                 validforError = "selectE";
            });
        });



      function madeAjaxCall(){

            $.ajax({
                url: "../sourceRe",
                type: "GET",
                cache: false,
                dataType: "json",

                success: function (response) {
                    // you could use the result.values dictionary here
                    var obj = response;

                    $(".child_selection").append("<option value='select' selected>select</option>");
                    $.each(obj, function (key,value) { //populate child options
                        $(".child_selection").append("<option value=\""+value+"\">"+value+"</option>");
                    });

                },
                error:function(response,status,er) {
                    alert("error: "+response+" status: "+status+" er:"+er);
                }
            });


        }
$(document).ready(function(){
	
	$('#datetimepickerSour').datetimepicker({
	    format: 'MM/dd/yyyy hh:mm:ss',
	    //pickTime: false,
	    language: 'en'
	});
	
	var select = "";
	 $('#myText').on('change', function() {
		select = $(this).val();
		
		if(select === "select"){
			$().toastmessage('showErrorToast', "Please select source");
		}
		
	 });
       $("form").submit(function(e){
    	 var numberReg = /^[0-9.]{0,13}$/;
       	 var characterReg = /^[a-zA-Z0-9., ]{0,150}$/;
       	var characterRegTiny = /^[a-zA-Z0-9., ]{0,100}$/;
       	 var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
       	if($("#SourceDate").val() === ""){
       		$().toastmessage('showErrorToast', "Please enter Date");
    	    e.preventDefault(e);
    	}else if(validforError == "selectE"){
       	
    		if($("#myText").val() === "select"){
    			$().toastmessage('showErrorToast', "Please select source");
    			e.preventDefault(e);
    		}else if($("#myText").val() === ""){
    			$().toastmessage('showErrorToast', "Please select source");
    			e.preventDefault(e);
    		}else if($("#SourceAmount").val() == ""){
        		$().toastmessage('showErrorToast', "Please enter amount");
        	    e.preventDefault(e);
        	}else if($("#SourceAmount").val() == 0){
        		$().toastmessage('showErrorToast', "Amount should be greter than 0");
        	    e.preventDefault(e);
        	}else if(!numberReg.test($("#SourceAmount").val())) {
        		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
        	    e.preventDefault(e);
        	}else if($("#SourceAmount").val() > 9000000000) {
        		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
        	    e.preventDefault(e);
        	}else if(!characterReg.test($("#SourceDescription").val())){
        		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
    	        e.preventDefault(e);
    		}else{
    			e.preventDefault(e);
        		var SourceDate = $("#SourceDate").val();
            	var SourceType = $("#SourceType").val();
            	var SourceName = $("#myText").val();
            	var Amount = $("#SourceAmount").val();
            	var Description = $("#SourceDescription").val();
            	
            	$.ajax({
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    type: $("form").attr("method"),
                    url:  $("form").attr("action"),
                    data: {SourceDate:SourceDate, SourceType:SourceType, SourceName:SourceName, Amount:Amount, Description:Description},
                    	
                    success : function(response){
                    	ajaxMessage = response;
                    	if(ajaxMessage === "SuccessSaveAsOld"){
                    		showPopUpmessage("Successfully amount add to exist source and Save record.", "../openSourceList");
                    	}else if(ajaxMessage === "Error"){
                    		$().toastmessage('showErrorToast', "Save Unsuccessfull");
                    	}else if(ajaxMessage === "SuccessAsSaveNew"){
                    		showPopUpmessage("Successfully create new source and add amount, Save record.", "../openSourceList");
                    	}
                    },
                    error: function (re) {
                    	$().toastmessage('showErrorToast', "Error in Ajax form");
                    }
                });

    		}
    	}else if(validforError == "inputE"){
    	
    		if($("#texxt").val() === ""){
    			$().toastmessage('showErrorToast', "Please enter source");
    			e.preventDefault(e);
    		}else if(!characterRegTiny.test($("#texxt").val())){
        		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
    	        e.preventDefault(e);
    		}else if($("#SourceAmount").val() == ""){
        		$().toastmessage('showErrorToast', "Please enter amount");
        	    e.preventDefault(e);
        	}else if($("#SourceAmount").val() == 0){
        		$().toastmessage('showErrorToast', "Amount should be greter than 0");
        	    e.preventDefault(e);
        	}else if(!numberReg.test($("#SourceAmount").val())) {
        		$().toastmessage('showErrorToast', "Amount should be in Numeric  and length below 13");
        	    e.preventDefault(e);
        	}else if($("#SourceAmount").val() > 9000000000) {
        		$().toastmessage('showErrorToast', "Amount should be less than 9 Billian Rs");
        	    e.preventDefault(e);
        	}else if(!characterReg.test($("#SourceDescription").val())){
        		$().toastmessage('showErrorToast', "Description length should be below 100, use {0-9a-zA-Z,.}");
    	        e.preventDefault(e);
    		}else{
        		e.preventDefault(e);
        		var SourceDate = $("#SourceDate").val();
            	var SourceType = $("#SourceType").val();
            	var SourceName = $("#texxt").val();
            	var Amount = $("#SourceAmount").val();
            	var Description = $("#SourceDescription").val();
            	
            	$.ajax({
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    type: $("form").attr("method"),
                    url:  $("form").attr("action"),
                    data: {SourceDate:SourceDate, SourceType:SourceType, SourceName:SourceName, Amount:Amount, Description:Description},
                    	
                    success : function(response){
                    	ajaxMessage = response;
                    	if(ajaxMessage === "SuccessSaveAsOld"){
                    		showPopUpmessage("Successfully amount add to exist source and Save record.", "../openSourceList");
                    	}else if(ajaxMessage === "Error"){
                    		$().toastmessage('showErrorToast', "Save Unsuccessfull");
                    	}else if(ajaxMessage === "SuccessAsSaveNew"){
                    		showPopUpmessage("Successfully create new source and add amount, Save record.", "../openSourceList");
                    	}
                    },
                    error: function (re) {
                    	$().toastmessage('showErrorToast', "Error in Ajax form");
                    }
                });
            	
    		}
    	}else{

    	}
     });
       select = "";
   	ajaxMessage = "";
});
