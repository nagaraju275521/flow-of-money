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
  
  <!-- hover styles -->
  <link rel="stylesheet" href="<c:url value="/resources/css/demo-page.css" />" />
  <link rel="stylesheet" href="<c:url value="/resources/css/hover.css" />" />
 <!--end  hover styles -->
  <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script src="<c:url value="/resources/js/paginga.jquery.js" />"></script>
  <script type="text/javascript" src="./resources/js/jquery-ui-1.10.4.min.js"></script>
   <script type="text/javascript" src="./resources/js/commonFunctions.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.sessionTimeout.js"></script>
   

  <script type="text/javascript">
  
  sessionTimeOUT('./sessionRedir');
  
  $(document).ready(function(){
  		$(".pageNumbers").click(function(){
			$(".pageNumbers>a").addClass("hvr-pulse");
		});
  
  		$(".pager").click(function(){
			$(".pageNumbers>a").addClass("hvr-pulse");		
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

function openPage(){
		window.open("./dairy", "_self");
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
           url: "./dairyResult",
           cache : false,
   		dataType : "json",
           data: { date1: date1}, // parameters
   			success : function(response) {
   				// you could use the result.values dictionary here
   				$.each(response, function(key, value) { //populate child options
   					//alert(value.all_Id+"--"+value.all_Perticuler+"--"+value.all_Deposite);
   					var str = value.dairyDes;
   					
   					var str1 = str.toString();
   					var str2;
   					if(str.length > 100){
   						str2 = str1.substring(0,100);
   						
   						}
   					 
   					//var str2 = '<p>'+str1+'</p>';
   					//alert(str2);
   	var s = "<div class='out'>  <div class='flo'><div class='two'>"+value.dairyId+"</div><div class='in'>"+value.dairyDate+"</div> </div> <div class='num'>"+str+"</div> </div>";
   		   	
   		            $("#dairy").append(s);  
   				

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
   		
   	});
   }

   function  pagingToAll(){
		$(function() {
			$(".paginate").paginga({
				// use default options
			});
		  
			$(".paginate-page-2").paginga({
				page: 2
			});

			$(".paginate-no-scroll").paginga({
				scrollToTop: false
			});
		});
		
		//var b = "hgfdghk hgfhdghfghgkjfh gkhgkdfhgkjfdhgkfdhgkfdhg kghfdkghfdkgh";
		//$("#xxx").append(b);
		//for(var a=22; a<= 30; a++){
		//	$("#dairy").append("<div>Item :"+a+"</div>");
		
		
		//}
		$(".pageNumbers>a").addClass("hvr-pulse");
		

	}

 
  </script>
<style>
* { font-family: 'Open Sans', sans-serif; }
body { background-color:#ffc080;}
h1 { margin:150px auto 30px auto;}



</style>


</head>
<body onload="return hidef();">

<center>
  <div style="width: 1150px;height:570px;border: 0px solid red;margin-top: 30px;">

    <table style="width: 100%;border: 0px solid red;">
      <tr>
        <td>
          <div class="headTableFloat">
            <strong>NotePad Or Dairy Records</strong>
              	<div><button type="submit" class="GrayBtn GrayBtn-primary">Delete</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" onclick="return openPage();">Add</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" >Send email</button></div>
         		<div><button class="GrayBtn GrayBtn-primary" >Excel</button></div>
          </div>
        </td>
      </tr>
      <tr>
        <td>
          <form action="deleteBank" method="post" name="admin" id="myForm">
            <table border="1" id="dairyExam"  cellspacing="0" width="100%">
              
              
   <div class="paginate 1">
         <div class="items" id="dairy">
    
  		</div>
  		<div class="forAlign">
    		<div class="pager">
    			<div class="hvr-pulse firstPage">&laquo;</div>
    			<div class="hvr-pulse previousPage">&lsaquo;</div>
    			<div class="pageNumbers"></div>
    			<div class="hvr-pulse nextPage">&rsaquo;</div>
    			<div class="hvr-pulse lastPage">&raquo;</div>
  			</div>
  		</div>	
   </div>
              
            </table>
          </form>
        </td>
      </tr>
    </table>
  </div>
</center>
	<div id="appendPoUp"></div>		
<div id="appendPoUpMail"></div>

<script>
			/**
			 * Used to demonstrate Hover.css only. Not required when adding
			 * Hover.css to your own pages. Prevents a link from being
			 * navigated and gaining focus.
			 
			 */
			 
			 
			var effects = document.querySelectorAll('.effects')[0];

			effects.addEventListener('click', function(e) {

				if (e.target.className.indexOf('pageNumbers') > -1) {
					e.preventDefault();
					e.target.blur();

				}
			});
		</script>
</body>
</html>