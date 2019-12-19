
var urlDates = "null";
var tableCol = "null";

sessionTimeOUT('./sessionRedir');

function bodyLoading(){
  $(function(){
    $("#Unitename").hide();
    $("#DATE_").hide();
    $("#showBetween").hide();
    $("#appendToDates").empty();
	twoDatesTable("2/2/2016", "2/2/2016", "./allDataOfExpence");
	$("#LargeTab").css({"background-color" : "gray", "font-size" : "14px"});
   });
}

function openexpenses(){
	window.open("./expenses", "_self");
}


$(document).ready(function(){
  	$("div").click(function(){
		//alert("---");
		$(".dataTables_paginate>span>a").addClass("hvr-pulse");
		$("#exampleTDates_paginate>a").addClass("hvr-pulse");
	});
  	 
  
  });


$(document).ready(function(){
	
	
	
	
	$(".hideShowColumn").hide();
	$("#option").click(function(){
	   
	    $(".hideShowColumn").toggle();
	    $("#selectAll").hide();
	    $("#reset").show();
	});
	
	$("#reset").click(function(){
		$("#grpChkBox input:checkbox").prop('checked', false);
		$("#reset").hide();
		$("#selectAll").show();
		
			
	        var items = document.getElementsByClassName('chebox');
	        for (var i = 0; i < items.length; i++){
	            
	            $("."+items[i].name).hide();
	            
	        }
	        $("#example>tbody").hide();
	});
	
	$("#selectAll").click(function(){
		$("#grpChkBox input:checkbox").prop('checked', true);
		$("#selectAll").hide();
		$("#reset").show();
		
		var items = document.getElementsByClassName('chebox');
        for (var i = 0; i < items.length; i++){
            
            $("."+items[i].name).show();
        }
        $("#example>tbody").show();
	});

});



   var obj;




    $(function() {
      var availableTags = [
        "Ticket",
        "Fruit",
        "Green Tea",
        "Rice",
        "Non-veg",
        "Vegitables",
        "Clojure",
        "COBOL",
        "ColdFusion",
        "Erlang",
        "Fortran",
        "Groovy",
        "Haskell",
        "Java",
        "JavaScript",
        "Lisp",
        "Perl",
        "PHP",
        "Python",
        "Ruby",
        "Scala",
        "Scheme"
      ];
      
      $( "#unit_name" ).autocomplete({
        source: availableTags
      });
      
    });


    function Checkboxchek() {
      var count = 0;
      for ( var i = 0; i < admin.Id123.length; i++) {
        if (admin.Id123[i].checked) {
          count++;
        }
      }
      if (count == 0) {
    	//$().toastmessage('showErrorToast', "please select checkbox.");
    	  
    	  $().toastmessage('showToast', {
              text     : 'please select checkbox.',
              sticky   : true,
              position : 'top',
              type     : 'error',
              closeText: '',
              close    : function () {
                  console.log("toast is closed ...");
              }
          });
    	  
        e.preventDefault();
        return false;
      }else if (count >1) {
    	  
    	  $().toastmessage('showToast', {
              text     : 'please select One checkbox only.',
              sticky   : true,
              position : 'top',
              type     : 'error',
              closeText: '',
              close    : function () {
                  console.log("toast is closed ...");
              }
          });
          
          //$().toastmessage('showErrorToast', "please select one checkbox.");
          //document.msgchbox.checkbox.focus();
          e.preventDefault();
          return false;
        } else {
        	var dle_IdC = 0;
        	$('input:checkbox[class=cheBoxForDelete]:checked').each(function () {
                $this = $(this);
                dle_IdC = $this.parent().siblings('td').eq(0).text();    
            });
        	//$().toastmessage('showSuccessToast', count+"  rows selected for delete");   
        	
        	//$("#menu_bar").hide();
        	if (window.location.hash === '#menu_bar') {
        	    $('#menu_bar').hode();
        	}
        	
        	$.bs.popup.confirm({
                title: 'Confirm Message',
                info: 'Do you want confirm to delete this ' +dle_IdC
            }, function(dialogE) {
                //alert('You are confirmed.');
            	deleteExpense();
                dialogE.modal('hide');
            });
        	
        	return true;
      }
    }










$(document).ready(function(){
    
    $('#mySelect').on('change', function() {
  	  var value = $(this).val();
  	  if(value == "date"){
  	 
  	 	$("#DATE_").show();
  	 	$("#showBetween").hide();
  		$("#Unitename").hide();
  		
  	  }
  	if(value == "BeeDate"){
    	  
    	  $("#showBetween").show();
    	  $("#DATE_").hide();
    	  $("#Unitename").hide();
    	  }
  	if(value == "UniteName"){
    	  $("#Unitename").show();
    	  $("#DATE_").hide();
    	  $("#showBetween").hide();
    	  }
    if(value == "allData"){
    	$("#showBetween").hide();
  	  	$("#DATE_").hide();
  	  	$("#Unitename").hide();  
		$("#appendToDates").empty();
		twoDatesTable("2/2/2016", "2/2/2016", "./allDataOfExpence");
    }	  	  
  	});
    
   
    
    $("#button1").click(function(){
        var dateOne= $("#datavalue").val();
        
     	urlDates ="./dateByList";
    	$("#appendToDates").empty();
    	twoDatesTable(dateOne, "2/2/2016", urlDates);
    });
        
    $("#largeRe").click(function(){
    	$("#appendToDates").empty();		
		twoDatesTable("2/2/2016", "2/2/2016", "./allDataOfExpence");
    });

$("#TinyTab").click(function(){
    	tableCol = "tiny"; 
    	$("#TinyTab").css({"background-color" : "gray", "font-size" : "14px"});
    	$("#smallTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
    	$("#MediumTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
    	$("#LargeTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
    });
    
$("#smallTab").click(function(){
	tableCol = "small"; 
	$("#smallTab").css({"background-color" : "gray", "font-size" : "14px"});
	$("#TinyTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
	$("#MediumTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
	$("#LargeTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
    });

$("#MediumTab").click(function(){
	tableCol = "medium"; 
	$("#MediumTab").css({"background-color" : "gray", "font-size" : "14px"});
	$("#smallTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
	$("#TinyTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
	$("#LargeTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
    });
$("#LargeTab").click(function(){
	tableCol = "null";
	$("#LargeTab").css({"background-color" : "gray", "font-size" : "14px"});
	$("#smallTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
	$("#MediumTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
	$("#TinyTab").css({"background-color" : "#bab8b8", "font-size" : "16px"});
    });
    
    $("#twoDates").click(function(){
    	var fdate = $("#fromDate").val();
    	var tdate = $("#toDate").val();   
    	urlDates ="./betweenDates";
    	$("#appendToDates").empty();
    	twoDatesTable(fdate, tdate, urlDates);
    });
});



function deleteExpense(){
		
    var dle_Id;
    var dle_amount;
    var dle_source;
    
	$('input:checkbox[class=cheBoxForDelete]:checked').each(function () {
        $this = $(this);
        dle_Id = $this.parent().siblings('td').eq(0).text();
        dle_amount = $this.parent().siblings('td').eq(7).text();
        dle_source = $this.parent().siblings('td').eq(8).text();
              
    });
	
	 $.ajax({
		 type: "GET",
	     url: "./deleteExpenses",
	     cache : false,
	     dataType : "json",
	     data: { dle_Id: dle_Id, dle_amount: dle_amount, dle_source: dle_source}, // parameters
	     success : function(response) {				
			if(response === "SuccessDeductFrCredit"){				
				showPopUpmessage("Successfully  Deduct money from Credit And delete corresponding row from database.","./expensesList");
			}else if(response === "SuccessAddToHolder"){				
				showPopUpmessage("Money successfully add to your Holder And delete corresponding row from database.","./expensesList");
			}else{
				$().toastmessage('showErrorToast', "Error.");
			}
					
		},
		error : function(response, status, er) {
			alert("error: " + response + " status: " + status+ " er:" + er);
			$().toastmessage('showToast', {text  : "error: " + response + " status: " + status+ " er:" + er,
				sticky   : true,
				position : 'top-right',
				type     : 'error',
				closeText: '',
				close    : function () {
				console.log("toast is closed ...");
				}
			});
		}
	    
	   });	 
	}

$(document).ready(function(){
	$('#datetimepicker').datetimepicker({
	    format: 'MM/dd/yyyy hh:mm:ss',
	    //pickTime: false,
	    language: 'en'
	});
	
	$('#datetimepicker1').datetimepicker({
	    format: 'MM/dd/yyyy hh:mm:ss',
	    language: 'en'
	});
	
	$('#datetimepicker2').datetimepicker({
	    format: 'MM/dd/yyyy hh:mm:ss',
	    language: 'en'
	});
	
	});






function twoDatesTable(fdate, tdate, urlDates){
	//alert("LargeResultTable");
	$("#appendToDates").append('<table border="1" id="exampleTDates" class="display table table-striped table-bordered DatesColRez"  cellspacing="0" width="100%">'
			+'<thead>'
			+'<tr>'
			+'<th class="check_id"></th> <th class="id">Id</th> <th class="Date">Date</th> <th class="Type">Type</th> <th class="Name">Name</th> <th class="Tag">Tag</th> <th class="Price">Price</th> <th class="Quantity">Quantity</th> <th class="Amount">Amount</th> <th class="Source">Source</th> <th>Source Bal</th> <th class="Description">Description</th> <th class="Company">Company</th> <th class="Area">Area</th> <th class="Whoom">Whome to</th>'
			+'</tr>'
			+'</thead>'
			+'<tfoot>'
			+'<tr><th class="check_id"></th><th class="id"></th><th class="Date"></th><th class="Type"></th><th class="Name"></th><th class="Tag"></th><th class="Price"></th><th class="Quantity"></th><th class="Amount"></th><th class="Source"></th> <th></th> <th class="Description"></th><th class="Company"></th><th class="Area"></th><th class="Whoom"></th></tr>'
			+'</tfoot>'
			+'<tbody id="exampLarge"></tbody></table>');
	 $.ajax({
        type: "GET",
        url: urlDates,
        cache : false,
		dataType : "json",
        data: { date1: fdate, date2: tdate }, // parameters
			success : function(response) {
				// you could use the result.values dictionary here			 
				obj = response;		
			
				$.each(obj, function(key, name1) { //populate child options					
					var eachrow = "<tr>"
						+ "<td class='check_id'> <input type='checkbox' name='Id123' class='cheBoxForDelete' value='"+name1.id+"' /></td>"
						+"<input type='text' name='source_name' value='"+name1.source_Name+"' />"
		                 + "<td class='id'>"+name1.id+"</td>"
		                 + "<td class='Date'>"+name1.date+"</td>"
		                 + "<td class='more Type'>" + name1.unit_Type + "</td>"
		                 + "<td class='more Name'>" + name1.unit_Name + "</td>"
		                 + "<td class='more Tag'>" + name1.unit_Tag + "</td>"
		                 + "<td class='Price'>" + name1.unit_price + "</td>"
		                 + "<td class='Quantity'>" + name1.quantity + "</td>"
		                 + "<td class='Amount'>" + name1.amount + "</td>"
		                 + "<td class='more Source'>" + name1.source_Name+ "</td>"
		                 + "<td>" + name1.source_bal+ "</td>"
		                 + "<td class='Description'>" + name1.description + "</td>"
		                 + "<td class='more Company'>" + name1.sold_company + "</td>"
		                 + "<td class='more Area'>" + name1.area + "</td>"
		                 + "<td class='more Whoom'>" + name1.whome_to + "</td>"		                 
		                 + "</tr>";
		            var va =  name1.unit_Type;    
					$("#exampLarge").append(eachrow);
					manageColumns(tableCol);
				});
			},
			error : function(response, status, er) {
				alert("error: " + response + " status: " + status + " er:" + er);
			}    
   });
	
	 $(document).ajaxComplete(function () {
		 	$('#exampleTDates tfoot th.Date, #exampleTDates tfoot th.Type, #exampleTDates tfoot th.Name, #exampleTDates tfoot th.Tag').each( function () {
		        var title = $(this).text();
		        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
		    } );
		 	$('#exampleTDates tfoot th.Source, #exampleTDates tfoot th.Company, #exampleTDates tfoot th.Whoom').each( function () {
		        var title = $(this).text();
		        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
		    } );
		 
		    //var table = $('#exampleTDates').DataTable();
		    
		    var table =   $('#exampleTDates').DataTable( {
		    	retrieve: true,
		    	"pagingType": "full_numbers",
		    	"paging": true,
		    	"destroy": true,
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
		                .column( 8 )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );
		 
		            //alert(total);
		            // Total over this page
		            pageTotal = api
		                .column( 8, { page: 'current'} )
		                .data()
		                .reduce( function (a, b) {
		                    return intVal(a) + intVal(b);
		                }, 0 );

		            var totalTF = total.toFixed(2);
		            var pagetotalTF = pageTotal.toFixed(2);
		         // Update footer
		            $( api.column(7).footer() ).html('Total :');
		            $( api.column(8).footer() ).html(pagetotalTF);
		            $( api.column( 10).footer() ).html('Whole :');
		            $( api.column( 11 ).footer() ).html(totalTF);		
		            //$(".table > tfoot > tr > th.Amount").html(pagetotalTF);
		        }
		    } );	 
		    
		    table.columns().every( function () {
		        var that = this;
		 
		        $( 'input', this.footer() ).on( 'keyup change', function () {
		            if ( that.search() !== this.value ) {
		                that
		                    .search( this.value )
		                    .draw();
		            }
		        } );
		    } );
		    
		    $(".dataTables_paginate>span>a").addClass("hvr-pulse");
	    	$("#exampleTDates_paginate>a").addClass("hvr-pulse");
		    
		    $(".DatesColRez").colResizable({
		    	disable:true
		    });
		
		    $(".DatesColRez").colResizable({
		    	//disable:true,
				liveDrag:true, 
			    gripInnerHtml:"<div class='grip'></div>", 
			    draggingClass:"dragging", 
			    resizeMode:'fix'		// fix, flex, overflow
			});		    
		    
	 });
}

function manageColumns(tableCol){
	//alert(tableCol);
	if(tableCol === "small"){
		$(".Whoom").hide();
		$(".Price").hide();
		$(".Company").hide();
	}else if(tableCol === "tiny"){
		$(".Price").hide();
		$(".Company").hide();
		$(".Type").hide();
		$(".Whoom").hide();
		$(".Tag").hide();
	}else if(tableCol === "medium"){
		$(".Whoom").hide();
		$(".Company").hide();		
	}
}

function newID(){

}
