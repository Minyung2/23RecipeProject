<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.6.4.js"
	integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
	crossorigin="anonymous"></script>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Gugi&display=swap');

@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&display=swap')
	;
</style>
<%-- <jsp:include page="../common/Top.jsp" /> --%>
<link rel="stylesheet" href="../css/top.css?v=3">

<header>
	<div id="headerbar" onscroll="headerbarToggle()"></div>
	
	<a class="home" href='../RecipeProject/Home.jsp'>집에 보내조</a> <a class="m-icon"><ion-icon 	name="person-outline"></ion-icon></a>
<!-- 	<form action="#" method="get" class="searchBar">
		<input type="text" id="searchRecipe"> 
		<div id="suggestion_keyword">		
		</div>
		<input type="submit" value="돋보기">
	</form> -->
	<br><br><br>
	<div>
		<section class="log">
			<c:if test="${sessionScope.user ==null }">
				<a class="login-out-reg" href="../project/loginCheck.do">로그인</a>
				<a class="login-out-reg" href="../project/join.do">회원가입</a>
			</c:if>
			<c:if test="${sessionScope.user !=null }">
				<a class="login-out-reg" href="../project/logout.do">로그아웃</a>
			</c:if>
		</section>

		<nav class="navMenu">
			<div class="nav-links">
				<a href="join">추천</a> <a href="rblist">분류</a>
				<a href="q_list">랭킹</a>
			</div>
		</nav>
		<div class="burger">
			<div class="line1"></div>
			<div class="line2"></div>
			<div class="line3"></div>
		</div>
	</div>

</header>
<script>
$(function(){
	var delayTimer;
	$('#searchRecipe').on('keyup', function() {
	  clearTimeout(delayTimer);
	  delayTimer = setTimeout(function() {
	    $.ajax({
	      url: "/jspstudy/autoSearch.do",
	      data: {
	        name: $("#searchRecipe").val()
	      },
	      type: "GET"
	    })
	    .done(function(data, textStatus) {
	      let temp = "<ul>";
	      $.each(data.suggestions, function(key, value) {
	        temp += "<li>" + value + "</li>";
	      });
	      temp += "</ul>";
	      $('#suggestion_keyword').html(temp);
	    })
	    .fail(function(data, textStatus) {
	      console.log('error', data, textStatus);
	    })
	  }, 500); // 500ms 이후에 요청을 보냄
	});
})
	//HeaderToggle
	var prevScrollPos = window.pageYOffset;
	console.log("first Y offset: " + prevScrollPos) //first value : 0
	window.onscroll = headerbarToggle
	function headerbarToggle() {
		var header = document.getElementById("headerbar");

		var currentScrollPos = window.pageYOffset; // current Y offset
		if (prevScrollPos < currentScrollPos) {
			header.style.opacity = '0.8';
		} else {
			header.style.opacity = '0';
		}
	}
	
	const logSlide = () =>{
		const mIcon = document.querySelector('.m-icon');
		const log = document.querySelector('.log');
		const logAll = document.querySelectorAll('.log a');

		mIcon.addEventListener('click', () => {
			log.classList.toggle('log-active');
			
			logAll.forEach((link, index) =>{
				if(link.style.animation){
					link.style.animation = '';
				}else{
					link.style.animation = `logLinkFade 0.5s ease forwards ${index /7 + 0.3}s`;
				}
			});
		});
	}
	
	logSlide();
	
	const navSlide = () => {
		const burger = document.querySelector('.burger');
		const nav = document.querySelector('.nav-links');
		const navLinks = document.querySelectorAll('.nav-links a');

		
		burger.addEventListener('click', () => {
	        //Toggle Nav
			nav.classList.toggle('nav-active');
	        
	        //Animate Links
	        navLinks.forEach((link, index) => {
	            
	            if(link.style.animation){
	                
	                link.style.animation = '';
	                
	            } else {
	                
	                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.3}s`;
	                
	            }
	        });
	        //Burger Animation
	        burger.classList.toggle('toggle');
	    });
	}

	navSlide();
</script>
<script type="module"
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


