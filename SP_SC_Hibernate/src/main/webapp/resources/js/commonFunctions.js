var tableOPT = "none";
var tableOPTNmail = "none";
var mailOptioin = "none";


function sessionTimeOUT(redirectURL){
$(document).ready(function(){
	
	$.sessionTimeout({
		redirUrl: redirectURL,
		logoutUrl: redirectURL,
	    warnAfter: 240000,
	    redirAfter: 300000,
		appendTime: true
	});
	
	
	
});
}



function sessionExp(){
	$.ajax({	
	    type: "GET",
	    url: "./displyUserOnHeader",
	    cache : false,
		dataType : "json",
	    data: { dle_Id: "23"}, // parameters
		success : function(response) {
			//alert(response);
			//document.getElementById("user_Mail").value =response;
			
		},
		error : function(response, status, er) {
			//alert("error: " + response + " status: " + status+ " er:" + er);	
			window.open("./expenses", "_top");
			//location.reload();
		}
	});
}



function colresize(){
	//alert("colresize 123");
$("#example").colResizable({
	liveDrag:true, 
    gripInnerHtml:"<div class='grip'></div>", 
    draggingClass:"dragging", 
    resizeMode:'fix'		// fix, flex, overflow
});
}

function colresizeLarge(){
	alert("colresizeLarge");
	$("#exampleLarge").colResizable({
		liveDrag:true, 
	    gripInnerHtml:"<div class='grip'></div>", 
	    draggingClass:"dragging", 
	    resizeMode:'fix'		// fix, flex, overflow
	});
}

function colresizeSmall(){
	alert("colresizeSmall");
	$("#exampleSmall").colResizable({
		liveDrag:true, 
	    gripInnerHtml:"<div class='grip'></div>", 
	    draggingClass:"dragging", 
	    resizeMode:'fix'		// fix, flex, overflow
	});
}

function hiding(){
	alert("hiding 123");
    var $chk = $("#grpChkBox input:checkbox");
    var $tbl = $("#example");
    $chk.prop('checked', true);
    $chk.click(function () {
        var colToHide = $tbl.find("." + $(this).attr("name"));
        $(colToHide).toggle();        
      });
}

function showPopUpmessage(mes, urlString){
	$.bs.popup.custom({
     		title: 'Success Message',
     		dom: '<div class="popupmesg"><div></div><p>'+mes+'</p></div>',
     		width: '400px'
 		}, function(dialogE) {                         	
         	dialogE.modal('hide');
         	SuccessBack(urlString);          
 	});
}

function SuccessBack(urlString){
	window.open(urlString,"_self");
}

function showPopUpmessage123(mes){
	$.bs.popup.custom({
     		title: 'Success Message',
     		dom: '<div class="popupmesg"><div></div><p>'+mes+'</p></div>',
     		width: '400px'
 		}, function(dialogE) {                         	
         	dialogE.modal('hide');          
 	});
}

function loadd(){
	   $(function () {	   	
	       var $body = $('body');    
	       $body.showLoading();
	   });
}

function hideLoad(){
	$(function () {
     var $body = $('body');
    
		$body.hideLoading();
		
 });
}

function downLoadSendMail(user_Mail, Pass_word, Subject, Message, tableOption, mailOption, fdate, tdate, Direct){
	loadd();
	  $.ajax({
			contentType: "application/json; charset=utf-8",
			dataType : "json",
		    type: "get",
		    url: "./sendUserDataToMail",
		  	cache: false,
		    data: {user_Mail: user_Mail, Pass_word: Pass_word, Subject: Subject, Message: Message, tableOption: tableOption, mailOption: mailOption, Fdate: fdate, Tdate: tdate, direct: Direct},
				success : function(response) {
					if(response === "Success"){
						$().toastmessage('showSuccessToast', "Successfully sent your Excel file.");
					}else if(response === "fileNotCreate"){
						$().toastmessage('showErrorToast', "Sorry to say Excel file not create or not select.");
					}else{
						$().toastmessage('showToast', {
				            text     : 'sorry to say !!'+response,
				            sticky   : true,
				            position : 'top-right',
				            type     : 'error',
				            closeText: '',
				            close    : function () {
				                console.log("toast is closed ...");
				            }
				        });
					}
					hideLoad();
					},
				error : function(response, status, er) {
					$().toastmessage('showErrorToast', "error: " + response + " status: " + status + " er:" + er);
				}

			});
}

function userName(){
	$.ajax({	
	    type: "GET",
	    url: "./displyUserOnHeader",
	    cache : false,
		dataType : "json",
	    data: { dle_Id: "23"}, // parameters
		success : function(response) {
			//alert(response);
			document.getElementById("user_Mail").value =response;
		},
		error : function(response, status, er) {
			alert("error: " + response + " status: " + status+ " er:" + er);		
		}
	});
}

function commonPOPUpTwoDates(tableOption) {
	sessionExp();
	tableOPT = tableOption;
	$.bs.popup.confirm({
		title : 'Confirm Message',
		info : 'Do you want confirm to generate '+ tableOption + ' Excel file(xlsx) table ?'
	}, function(dialogE) {
			dialogE.modal('hide');				
			openPopGenExccel(tableOption);	
			$("#appendDatesPop").html(tableOption + " Excel file generate");	
	});
}

function passDatesDynamic(){
	//alert(tableOPT);
  	var FDateE = $("#form_DateE").val();
  	var TDateE = $("#to_DateE").val(); 
  	
  	if(FDateE === ""){
  		$().toastmessage('showErrorToast', "Please enter from date.");
  		return false;
  	}else if(TDateE === ""){
  		$().toastmessage('showErrorToast', "Please enter to date.");
  		return false;
  	}else{  	
  		$('#closingPop').trigger('click');  	
  	  	generateExcelFile(tableOPT, FDateE, TDateE);
  	}  	
  }

function beforeConfirm(tableOption, FDate, TDate){
	//alert(tableOption);
	$.bs.popup.confirm({
		title : 'Confirm Message',
		info : 'Do you want confirm to generate '+ tableOption + ' Excel file(xlsx) ?'
	}, function(dialogE) {
		dialogE.modal('hide');		
		generateExcelFile(tableOption, FDate, TDate);
	});
}

function generateExcelFile(tableOption, FDate, TDate){
	//alert(tableOption);
	loadd();
	$.ajax({
		contentType: "application/json; charset=utf-8",
		dataType : "json",
	    type: "get",
	    url: "./generateExcelFile",
	  	cache: false,
	    data: {tableOption: tableOption, FromDate: FDate, ToDate: TDate},
			success : function(response) {
				// you could use the result.values dictionary here
				//alert(response);
				
				if(response === "Success"){
					$().toastmessage('showSuccessToast', "Successfully generate selected table Excel file");
				}else{
					$().toastmessage('showErrorToast', "sorry to say !! "+response);
				}
				hideLoad();
			},
			error : function(response, status, er) {
				//alert("error: " + response + " status: " + status + " er:" + er);
				$().toastmessage('showErrorToast', "error: " + response + " status: " + status + " er:" + er);
			}

		});

}

function SelectMailDynamic(mailOptionIP, tableOPTION) {
	tableOPTNmail = tableOPTION;
	mailOptioin = mailOptionIP;
	$.bs.popup.confirm({
		title : 'Confirm Message',
		info : 'Do you want confirm to sending email '+ tableOPTION +' Excel file(xlsx) ?'
	}, function(dialogE) {
			dialogE.modal('hide');		
			popUpOpenForMail();				
			userName();
			$("#appendEvent").html(tableOPTION+" Excel file sending");
	});		
}


function mailSendingDirect() {
	var tableOpt_mail = tableOPTNmail;
	var Direct_mail = mailOptioin;
	user_MailE = $("#user_Mail").val();
	Pass_wordE = $("#Pass_word").val();
	SubjectE = $("#Subject").val();
	MessageE = $("#Message").val();
	var numberReg = /^[0-9.]{0,11}$/;
	var characterReg = /^[a-zA-Z0-9., ]{0,150}$/;
	var onlyNumerics = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
	var emailValid = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
	if ($("#user_Mail").val() === "") {
		$().toastmessage('showErrorToast', "Please enter user email");
		return false;
	}else if(!emailValid.test($("#user_Mail").val())){
		$().toastmessage('showErrorToast', "Please enter valid email");
	} else if (Pass_wordE === "") {
		$().toastmessage('showErrorToast', "Please enter Password");
		return false;
	} else if (Pass_wordE.length > 14) {
		$().toastmessage('showErrorToast', "Password length should be 14 or below.");
		return false;
	} else if (Pass_wordE.length < 8) {
		$().toastmessage('showErrorToast', "Password length should be 8 or above.");
		return false;
	}else if (SubjectE === "" || SubjectE.length > 10) {
		$().toastmessage('showErrorToast', "Subject length should be 100 or below.");
		return false;
	} else if (MessageE === "") {
		$().toastmessage('showErrorToast', "Please enter message");
		return false;
	} else if(!characterReg.test(MessageE)){
		$().toastmessage('showErrorToast', "Message length should be 150 or below. ");
	}else {
		$('.modal').modal('hide');
		downLoadSendMail(user_MailE, Pass_wordE, SubjectE, MessageE, tableOpt_mail, "SendMail", "2/2/2016", "2/2/2016", Direct_mail);
		destMailPop();
	}
}

function dest(){
	$(".PopUp7412").remove();
}

function destMailPop(){
	$(".PopUpMailDynamic").remove();
}

function ddate(){
	
	$(document).ready(function(){	 

		$('#datetimepickerCom').datetimepicker({
		    format: 'MM/dd/yyyy',
		    pickTime: false,
		    language: 'en'
		    
		}); 
		
		$('#datetimepickerComTO').datetimepicker({
		    format: 'MM/dd/yyyy',
		    pickTime: false,
		    language: 'en'
		    
		}); 
		
		
	});
	
}

function openPopGenExccel(tableOPTN){
	//alert("yyyyyyyyyyyy"+tableOPTN);
	var pop= '<p class="PopUp7412" data-toggle="modal" data-target="#PopUpModelHRecord123"  onclick="ddate()" ></p>'
		
		+'<div class="container">'
		+'<!-- Modal -->'
		+'<div class="modal fade" id="PopUpModelHRecord123" role="dialog" data-keyboard="false" data-backdrop="static">'
		+'<div class="modal-dialog">'
	    
		+'<!-- Modal content-->'
		+'<div class="modal-content">'
		+'<div class="modal-header">'
		+'<button type="button" class="close" data-dismiss="modal" onclick="return dest()"  style="width:20px;">&times;</button>'
		+'<h4 class="modal-title">excel Compose</h4>'
		+' </div>'
		+'<div class="modal-body">'
		+'<div id="appendDatesPop" class="mailHead"></div>'    
		+'<form action="#" method="get">'
		+'<table class="exportPopUp" >'
		+'<tr>'
		+'<th>From Date</th><td>'
		+'<div id="datetimepickerCom" class="input-append date">'
		+'<input type="text" name="form_DateE" id="form_DateE" />' 
		+'<span class="add-on">'
		+'<i data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>'
		+'</span>'
		+'</div>'		
		+'</td>'
		+'</tr>'
		+'<tr>'
		+'<th>To Date</th>'
		+'<td>'	
		+'<div id="datetimepickerComTO" class="input-append date">'
		+'<input type="text" name="to_DateE" id="to_DateE" />' 
		+'<span class="add-on">'
		+'<i data-time-icon="icon-time"  data-date-icon="icon-calendar"></i>'
		+'</span>'
		+'</div>'
		+'</td>'
		+'</tr>'      
		+'</table>'   
		+'</form>'
		+'</div>'
		+'<div class="modal-footer">'
		+'<button type="button"  data-dismiss="modal" class="btn btn-primary" data-toggle="modal" id="closingPop" onclick="return dest();">Close</button>'
		+'<button type="button"  class="btn btn-primary" data-toggle="modal" onclick="return passDatesDynamic()">Create</button>'
		+'</div>'
		+'</div>'      
		+'</div>'
		+'</div>'  
		+'</div>';
	
	$("#appendPoUp").append(pop);	
	$('.PopUp7412').trigger('click');
}



function popUpOpenForMail(){
	var popMail = '<p class="PopUpMailDynamic" data-toggle="modal" data-target="#ModalMailDynamic" ></p>'
		+'<div class="container">'
		+'<!-- Modal -->'
		+'<div class="modal fade" id="ModalMailDynamic" role="dialog" data-keyboard="false" data-backdrop="static">'
		+'<div class="modal-dialog">'
		+'<!-- Modal content-->'
		+'<div class="modal-content">'
		+'<div class="modal-header">'
		+'<button type="button" class="close" data-dismiss="modal" onclick="return destMailPop();" style="width: 20px;">&times;</button>'
		+'<h4 class="modal-title">Mail Compose</h4>'
		+'</div>'
		+'<div class="modal-body">'
		+'<div id="appendEvent" class="mailHead"></div>'
		+'<form id="exportPopUpForm" action="../downloadCredit" method="get">'
		+'<table class="exportPopUp">'
		+'<tr>'
		+'<th>mail</th>'
		+'<td><input type="email" name="user_Mail" id="user_Mail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"></td>'
		+'</tr>'
		+'<tr>'
		+'<th>Password</th>'
		+'<td><input type="password" name="Pass_word" id="Pass_word" />'
		+'</td>'
		+'</tr>'
		+'<tr>'
		+'<th>Subject</th>'
		+'<td><input type="text" name="Subject" id="Subject" /></td>'
		+'</tr>'
		+'<tr>'
		+'<th>Message</th>'
		+'<td><textarea rows="3" cols="20" name="Message" id="Message"></textarea></td>'
		+'</tr>'
		+'</table>'
		+'</form>'
		+'</div>'
		+'<div class="modal-footer">'
		+'<button type="button" data-dismiss="modal" class="btn btn-primary" onclick="return destMailPop();" data-toggle="modal">Close</button>'
		+'<button type="button" onclick="mailSendingDirect()" class="btn btn-primary" data-toggle="modal">Send</button>'
		+'</div>'
		+'</div>'
		+'</div>'
		+'</div>'
		+'</div>';
	
	$("#appendPoUpMail").append(popMail);	
	$('.PopUpMailDynamic').trigger('click');
}



