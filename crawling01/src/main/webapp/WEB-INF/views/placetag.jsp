<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>placetag</title>
</head>
<body>
<h1>placetag</h1>
<c:if test="${placetag != null}">
사진 위치 정보 : <h2>${placetag}</h2>
</c:if>



</body>
</html>
