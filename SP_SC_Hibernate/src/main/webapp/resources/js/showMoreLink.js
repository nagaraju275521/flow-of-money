	   function ReadMore(){
		 
		   var showChar = 30;
			var ellipsestext = "...";
			var moretext = "&#10143;";
			var lesstext = "&#8672;";
			$(".more").each(function() {
				var content = $(this).html();
				
				if(content.length > showChar) {

					var c = content.substr(0, showChar);
					var h = content.substr(showChar-1, content.length - showChar);

					var html = c + '<span class="moreelipses">'+ellipsestext+'</span>&nbsp;<span class="morecontent"><span>' + h + 
					'</span>&nbsp;&nbsp;<a href="" style="border: 0px solid red;padding:0px;color:#949494;" class="morelink">'+moretext+'</a></span>';

					$(this).html(html);
				}

			});
			
			$(".morelink").click(function(){
				if($(this).hasClass("less")) {
					$(this).removeClass("less");
					$(this).html(moretext);
				} else {
					$(this).addClass("less");
					$(this).html(lesstext);
				}
				$(this).parent().prev().toggle();
				$(this).prev().toggle();
				return false;
			});
	   }
	   

