<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>태그 검색으로 관련 태그들 보기</h1>
<a href="ByMouse">가게 좋아요</a><hr>

<a href="/store">가게의 헤더, 포스트 </a>
<hr>


<a href="/store_header">가게의 헤더 </a>
<hr>


******<a href="/store_post">가게의 포스트 </a>
<hr>

















<br><br><br><br><br><br><br><br><br><br><br><br>


















<hr>
<a href="post_list_five">가게의 포스트 가져오기</a>
<hr>
<a href="followerCount">가게의 팔로워 가져오기</a>
<hr>


<form action="/crawling_tag_search" method="post">
tag(ex. 동명동맛집) : <input type="text" name="tag">
<input type="submit" value="태그를 검색해서 주소의 관련태그보기">
</form>
<hr>
<form action="/crawling_tag" method="post">
url(ex. https://www.instagram.com/tags/동명동맛집/) : <input type="text" name="base_url">
<input type="submit" value="태그 포함한 주소의 관련태그보기">
</form>
<hr>
<form action="/crawling_post" method="post">
url(ex. https://www.instagram.com/p/B9d5qJqgUIi/) : <input type="text" name="base_url">
<input type="submit" value="포스트 주소의 태그보기">
</form>
<hr>
<form action="/crawling_storename_search" method="post">
url(ex. kko.keki) : <input type="text" name="storename">
<input type="submit" value="가게 id받아 url로 검색">
</form>
<hr>
<form action="crawling_storename_getPlacetag" method="post">
url(ex. https://www.instagram.com/p/B9d5qJqgUIi/) : <input type="text" name="base_url">
<input type="submit" value="포스트 url로  이미지 위치 정보 검색 검색">
</form>
<hr>
<a href="ByPixel">스크롤 내리기</a>


<hr>
<a href="insert_istore">insta_id로 가게프로필, 최근 포스트 20여개 insert</a>

<hr>
<a href="crawling_setsearch">검색창에 동명동 검색하기</a>


<hr>
<form action="post_click" method="post">
url(ex. https://www.instagram.com/_randori/) : <input type="text" name="base_url">
<input type="submit" value="포스트 옆으로 넘기기">
</form>

<hr>
<a href="goJackson">json</a>




</body>
</html>
