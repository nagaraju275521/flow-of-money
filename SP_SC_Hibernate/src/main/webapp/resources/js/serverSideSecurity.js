

  function serverSideSecurity(){

		$.ajax({
	        type: "GET",
	        url: "./serverSideSecurity",
	        cache : false,
			dataType : "json",
	         // parameters
				success : function(response) {
					//alert(response);
					

				},
				error : function(response, status, er) {
					//alert("error: " + response + " status: " + status+ " er:" + er);
					window.open("./Session_Invalid", "_top");
					
				}
	    
	   });
		
	}
