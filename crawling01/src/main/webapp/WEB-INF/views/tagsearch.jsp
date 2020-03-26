<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>tagsearch</title>
</head>
<body>
<h1>tagsearch</h1>
<c:if test="${tag != null}">
<h2>${tag}</h2>
</c:if>

<c:if test="${taglist != null}">
	<c:forEach var="tage" items="${taglist}">
		${tage}<br>
	
	</c:forEach>
</c:if>


</body>
</html>
