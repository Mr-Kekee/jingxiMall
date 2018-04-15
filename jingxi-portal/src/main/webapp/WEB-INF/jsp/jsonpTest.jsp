<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
keke!----------------
</body>
<script>
$(function() { 
	$.ajax({
		type:"get",
		async:false,
		url:"http://localhost:8082/rest/exchangeData?jsonpCallback=?",
		//url:"http://localhost:8082/rest/exchangeData",
		dataType:"jsonp",
		//jsonp:"jsonpCallback",//(request.getParam) 
		success:function(data) {
			alert("name是:"+data[0].name);
		}
	})
	 
	/*$.getJSON("http://localhost:8082/rest/exchangeData?callback=?"
		 , function(json){      
	        alert(json[0].name);     
    });*/    
}); 
</script>
</html>