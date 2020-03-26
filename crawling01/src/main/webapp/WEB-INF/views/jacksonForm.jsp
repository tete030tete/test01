<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<script type="text/javascript">
	
var form = { id: "123"}; 
$("#btn1").click(function(){
$.ajax({ 
	url: "http://localhost:8080/test", 
	method: "post", 
	type: "json", 
	contentType: "application/json; charset=utf-8", 
	data: JSON.stringify(form), 
	success: function(data) { console.log(data); } 
	});
})
</script>
<body>
<h1>json 관련</h1>



<form action="/crawling_search_list" method="post">
검색어 연관 리스트(ex. 동명동맛집) : <input type="text" name="search">
<input type="submit" value="검색어의 연관검색어 리스트 가져오기">
</form>
<hr>

<input type="button" value="json_test01" id="btn1">
<hr>

</body>
</html>
