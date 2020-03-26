<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>post</title>
</head>
<body>
<h1>post 내 태그</h1>
<c:if test="${taglist != null}">
	<c:forEach var="tag" items="${taglist}">
		${tag}<br>
	
	</c:forEach>
</c:if>
<c:if test="${postdate != null}">
	${postdate}
</c:if>

</body>
</html>
