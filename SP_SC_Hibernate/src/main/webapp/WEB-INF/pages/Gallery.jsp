<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Gallery</title>
<script> 
function val(){
        const fi = document.getElementById('file'); 
        // Check if any file is selected. 
        if (fi.files.length > 0) { 
            for (const i = 0; i <= fi.files.length - 1; i++) { 
  
                const fsize = fi.files.item(i).size; 
                const file = Math.round((fsize / 1024)); 
                // The size of the file. 
                if (file >= 99) { 
                    alert( 
                      "File too Big, please select a file less than 99kb"); 
                    return false;
                } else if (file < 50) { 
                    alert( 
                      "File too small, please select a file greater than 50kb");
                    return false;
                } else { 
                    document.getElementById('size').innerHTML = '<b>'
                    + file + '</b> KB'; 
                } 
            } 
        }else{
        	alert("select a file between 50kb and 99kb");
        	return false;
        } 
    } 
</script>
</head>
<body>
    
      <center>
       <div style="margin: 100px;border: 1px solid red;padding: 20px;">
    
    <form method="post" action="./saveGallery?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
    	<input type="file" name="file" value="" id="file" />
    	<input type="submit" value="submit" onClick="return val();" />
    </form>
    </div>
    </center>
   
</body>
</html>