<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

<title>JSP 게시판 웹 사이트</title>

</head>
<body>
	<%-- <c:url value="j_spring_security_check" var="loginUrl"/> --%>
   <div class="container">
    <div class="col-4 offset-4 d-flex justify-content-center align-items-center">
    <a class="navbar-brand"> <img src="../projectResources/img/로고.png" alt="헬창 레시피"
         width="300px;" height="200px;" src="../login.jsp"></a></div>
   
    <div class="col-4 offset-4">
    	<c:url var="loginProcess" value="loginProcess"/>
        <div class="jumbotron" style="padding-top: 20px;">
            <form method="post" action="${loginProcess}">
                <h3 style="text-align: center;">Login</h3>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="아이디" name="user_id" maxlength='20'>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control red-text"
                        placeholder="비밀번호" name="user_pw" maxlength='20'>
                </div>
                <div class="form-group d-flex justify-content-center align-items-center">
                    <input type="submit" class="btn btn-primary" value="로그인" style="margin: 10px;">
                    <input type="button" class="btn btn-primary" value="회원가입" style="margin: 10px;" onclick="window.location.href='join';">
                    <input type="button" class="btn btn-primary" value="ID/PW찾기" style="margin: 10px;" onclick="window.location.href='IdPw.jsp';">
                </div>
                <c:if test="${errorMsg!=null}">
 					<div> <span style="color:red;align-items: center;">${errorMsg} </span></div>
 				</c:if>
            </form>
        </div>
    </div>
</div>

</body>
</html>