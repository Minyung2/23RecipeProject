<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>


	<a href="recipeList">눌러</a>
	<sec:authorize access="isAuthenticated()">
	<a href="logout">로그아웃</a>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
	<a href="join">노예 계약</a>
	<a href="login">로그인</a>
	</sec:authorize>
</body>
</html>