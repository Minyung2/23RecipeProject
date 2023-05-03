<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .b{
            border: 1px solid black;
        }

        #main-menu>.nav-item>.nav-link.active{
            background-color: #5cb85c;
            color: #fff; /* 선택된 링크 텍스트 색상 */
        }
        #main-menu>.nav-item>.nav-link{
            color:#5cb85c;
        }

        

    </style>
</head>

<body>
    <div class="d-flex justify-content-center"><img class="img-fluid" src="../projectResources/img/로고.png" style="width: 25rem; height: 15rem;" ></div>
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom" style="width: 60%; margin-left: 20%;">
        <div class="d-flex align-items-center input-goup mb-3 mb-md-0 me-md-auto text-decoration-none">
<!--            <input class="input-group" type="text"> -->
<!--            <button>검색</button> -->
        </div>
  
        <ul class="nav nav-pills" id="main-menu">
        
          <li class="nav-item"><a href="recipeList" class="nav-link active">Recipes</a></li>
          
		
		  <sec:authorize access="!isAuthenticated()">
		        <li class="nav-item"><a href="login" class="nav-link">Login</a></li>
        		<li class="nav-item"><a href="#" class="nav-link">Signup</a></li>
		  </sec:authorize>
		  <sec:authorize access="isAuthenticated()"> 
		    	<li class="nav-item"><a href="logout" class="nav-link">Logout</a></li>
		</sec:authorize>
       	   
        </ul>
    </header>


    
       
   


    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>