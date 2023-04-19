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

</head>
<body>
<div>
			<table class="table-primary" border="1" style="width: 90%;">
				<tr>
					<th>번호</th>
					<th>레시피명</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>			
				<c:forEach var="rec" items="${list}" varStatus="stat">
				<tr>
						<td>${pageDto.totalCount-((pageDto.currentPage-1)*pageDto.pageSize)-stat.index}</td>
						<td><a href="../project/recipeview.do?recipe_id=${rec.recipe_id}">${rec.recipe_name}</a></td>
						<td>${rec.user_idx}</td>
						<td>${rec.visitCount}</td>
				</tr>		
					</c:forEach>
			</table>
 	<a class="button" href="../project/recipeWrite.do">글쓰기</a>&nbsp;&nbsp;
 	<a class="button" href="/index.do">홈 </a>
 	</div>

		<div style="text-align: center;">
			<c:if test="${pageDto.startPage !=1 }">
				<a class="pagenum" href="?page=1">&lt;&lt;</a>
				<a class="pagenum" href="?page=${pageDto.startPage-1}">&lt;</a>
			</c:if>
			<c:forEach var="i" begin="${pageDto.startPage }"
				end="${pageDto.endPage }">
				<a class="pagenum <c:if test="${pageDto.currentPage == i }">current</c:if>" href="?page=${i }">${i }</a>
			</c:forEach>

			<c:if test="${pageDto.endPage !=pageDto.totalPage }">
				<a class="pagenum" href="?page=${pageDto.endPage+1}">&gt;</a>
				<a class="pagenum" href="?page=${pageDto.totalPage }">&gt;&gt;</a>
			</c:if>
		</div>


</body>
</html>