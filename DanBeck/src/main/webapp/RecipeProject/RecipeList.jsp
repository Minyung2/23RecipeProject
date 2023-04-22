<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ㅎㅇ</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

<style>
	a.button {
	font-family: 'Gowun Dodum', sans-serif;
	text-decoration: none;
	background-color:	white;
	color: black;
	cursor : pointer;
	text-align: center;
	border: 2px solid #aabb97;
	padding : 10px 20px;
	display: inline-block;
	margin-left: auto;
	border-radius: 5px;
	transition: all 0.3s ease;	
}


a.button:hover{
	background:	#aabb97;
	color: white;
	box-shadow: 0 3px 3px 0 rgba(0,0,0,0.24), 0 3px 3px 0 rgba(0,0,0,0.19);
}
.pagenum {
	font-family: 'Gowun Dodum', sans-serif;
    padding: 3px 5px;
    text-decoration: none;  /*  밑줄제거  */
    cursor: pointer;
    display: inline-block;
	text-align:center;
	font-size: 12pt;
	color : black;
	background: white;
	border: 2px solid #aabb97;
	border-radius: 5px;
	transition: all 0.3s ease;
}
.active{
	font-family: 'Gowun Dodum', sans-serif;
    padding: 3px 5px;
    text-decoration: none;  /*  밑줄제거  */
    cursor: pointer;
    display: inline-block;
	text-align:center;
	font-size: 12pt;
	color : black;
	background: white;
	border : 2px solid #aabb97;
	border-radius: 5px;
	transition: all 0.3s ease;
}



.pagenum:hover, .active:hover{
	background:	#aabb97;
	color: white;
	box-shadow: 0 3px 3px 0 rgba(0,0,0,0.24), 0 3px 3px 0 rgba(0,0,0,0.19);
}


.current {
	color : #c8c8c8;
	border : 1px solid gray;
	background: white;
}
</style>
</head>
<body>
<jsp:include page="../projectResources/Top.jsp"/>
<br>
<div>		
<form action="../project/recipeList.do" method="get">
 <div class="searchdiv">
		<select name="field" class="field">
			<option value="T" <c:if test="${field=='T'}">selected</c:if>>방법별</option>
			<option value="I" <c:if test="${field=='I'}">selected</c:if>>재료별</option>
		</select>
		<input placeholder="검색할 단어를 입력하세요." name="findText" value="${page.findText}" style="width: 300px; height: 40px; border: none; outline: none;">
		<button type="submit">대충버튼</button>
 </div>
 </form>

			<table class="table-primary" border="1" style="width: 90%;">
				<tr>
					<th>번호</th>
					<th>사진</th>
					<th>레시피명</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>			
				<c:forEach var="rec" items="${list}" varStatus="stat">
				<tr>
						<td>${page.totalCount-((page.currentPage-1)*page.pageSize)-stat.index}</td>
						<td><img src="${pageContext.request.contextPath}/Storage/${rec.recipe_image_url}" style="width: 150px;height: 150px;"/>
						<td><a href="../project/recipeview.do?recipe_id=${rec.recipe_id}">${rec.recipe_name}</a></td>
						<td>${rec.user_nickname}</td>
						<td>${rec.recipe_visitCount}</td>
				</tr>		
					</c:forEach>
			</table>
 	<a class="button" href="../project/recipeWrite.do">글쓰기</a>&nbsp;&nbsp;
 	<a class="button" href='../RecipeProject/Home.jsp'>홈 </a>
 	</div>

	<c:if test="${page.findText==null}">
 <div style="text-align: center;">
	<a class="pagenum" href="?page=1">&lt;&lt;</a>   <!-- 요청url은 동일하고 파라미터만 변경됩니다. -->
	<a class="${page.startPage>1? 'pagenum':'none'}" href="?page=${page.startPage-1 }">&lt;</a>  
	<!-- 현재페이지값을 변경 : blists.getStartPage() -1 -->
	
	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">  <!-- 페이지목록의 범위  -->
		<a class="pagenum" href="?page=${i}">${i}</a>     <!-- 현재페이지 i값으로 변경  -->
	</c:forEach>
	
	<!-- 현재페이지값을 변경 : blists.getEndPage() +1 -->
	<a class="${page.endPage!=page.totalPage? 'pagenum':'none'}" href="?page=${page.endPage+1 }">&gt;</a> 
	<a class="pagenum" href="?page=${page.totalPage }">&gt;&gt;</a>
</div>
</c:if>
<!-- 페이지 이동 :검색결과 보기 -->
 <c:if test="${page.findText!=null}">
 <div style="text-align: center;">
	<a class="pagenum" href="?page=1&field=${page.field}&findText=${page.findText}">&lt;&lt;</a>   <!-- 요청url은 동일하고 파라미터만 변경됩니다. -->
	<a class="${page.startPage>1? 'pagenum':'none'}" href="?page=${page.startPage-1 }&field=${page.field}&findText=${page.findText}">&lt;</a>  
	<!-- 현재페이지값을 변경 : blists.getStartPage() -1 -->
	
	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage}">  <!-- 페이지목록의 범위  -->
		<a class="pagenum" href="?page=${i}&field=${page.field}&findText=${page.findText}">${i}</a>     <!-- 현재페이지 i값으로 변경  -->
	</c:forEach>
	
	<!-- 현재페이지값을 변경 : blists.getEndPage() +1 -->
	<a class="${page.endPage!=page.totalPage? 'pagenum':'none'}" href="?page=${page.endPage+1 }&field=${page.field}&findText=${page.findText}">&gt;</a> 
	<a class="pagenum" href="?page=${page.totalPage }&field=${page.field}&findText=${page.findText}">&gt;&gt;</a>
</div>
</c:if>



</body>
</html>