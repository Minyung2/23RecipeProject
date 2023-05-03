<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬스인을 위한 요리 레시피</title>
<link rel="styleSheet" href="../css/userJoin.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../javascript/join.js"></script>

</head>
<body>
   <h2>회원가입 화면</h2>
   <form name="signup" action="joinProcess" method="post" onsubmit="return checkForm()">
		<input id="keyChack" type="hidden" name="keyChack" value="">
	   <input id="idChack" type="hidden" name="idChack" value="" >
	   <input id="nickChack" type="hidden" name="nickChack" value="" >
      <div class="form-box"><br>
      
      <div class="form-group">
         ＊아이디:       <input type="text" id="id" name="user_id" placeholder="5~15자리 소문자,숫자만 가능합니다" />
         <br>
         <span id="idStatus" style="font-size: 12px;"> </span>
         <br><br>
      </div>
      
      <div class="form-group">
         *비밀번호:       <input type="password" id="pw" name="user_pw" placeholder="8~16자리 영문,숫자,특수문자를 조합해주세요" />
         <br>
		<span id="pwStatus" style="font-size: 12px;"> </span>
      <br><br>
      </div>
      <div class="form-group">
         *재확인: <input type="password" id="pwDoubleCheck" name="pwDoubleCheck" placeholder="위 패스워드와 동일하게 입력해주세요." />
         <br>
		<span id="pwDoubleCheckStatus" style="font-size: 12px;"> </span>
      <br><br>
      </div>
      <div class="form-group">
         *이름:     <input type="text" id="name" name="user_name" placeholder="이름을 입력하세요" />
         <br>
		<span id="nameStatus" style="font-size: 12px;"> </span>
         <br><br>
      </div>
      
      <div class="form-group">
         *닉네임: <input type="text" id="nickname" name="user_nickname" placeholder="2~20자리 특수문자를 제외한 영문,숫자,한글만 가능합니다" />
         <br>
		<span id="nicknameStatus" style="font-size: 12px;"> </span>
         <br><br>
      </div>
      
      <div class="form-group">
         *Email:    <input type="text" id="emaildata" name="user_email" placeholder="이메일을 입력하세요" /><br><br>
         <button id="emailChackBtn" type="button">이메일보내기</button>
         <br><span id="emailStatus" style="color:green; font-size: 12px;">됨 </span>
          	<div id="NumChackView" style="display: none;">
      			인증번호 입력:<input id="keydata" type="text" name="random" placeholder="인증번호를 입력하세요"/><br><br>
      			<button id="keygo" type="button">인증확인</button>
     		</div>         
      </div>
      <div class="form-group">
      주소
				<input class="form-control" style="top: 5px;" name="user_address" id="m_addr" type="text" readonly>
				  <input type="button" class="btn btn-primary btn-sm" value="주소찾기" onclick="sample6_execDaumPostcode()">
				<span style="display: none; color:green;">dd</span>  
      </div>
      <div class="form-group">
      전화번호
          <input type="tel" id="phone" name="user_phone">
           <br>
			<span id="phoneStatus" style="font-size: 12px;"> </span>
         <br><br>
      </div>
      
      
      <input type="submit" value="회원가입" disabled="disabled"/>
      </div>
   </form>

</body>

<script>


</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
//주소관련 함수



</script>

</html>