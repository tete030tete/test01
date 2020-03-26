<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>store</title>
</head>
<body>
<h1>store</h1>
<c:if test="${storename != null}">
<h2>${storename}</h2>
</c:if>

<c:if test="${taglist != null}">
	<c:forEach var="tage" items="${taglist}" varStatus="cnt">
		${tage}<br>
		${cnt.count}
	</c:forEach>
	
</c:if>


</body>
</html>
