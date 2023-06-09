<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개발중임</title>
<link rel="styleSheet" href="../projectResources/css/RecipeView.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">	
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>	
</head>
<body>
	<div class="recipeView">
		<div class="recipe_summary">
			<h3>${recipeDto.recipe_name}</h3>
			<div class="summary_in">${recipeDto.recipe_desc}</div>
			<div class="summary_info">
				<span>${recipeDto.recipe_people}</span>&nbsp&nbsp <span>${recipeDto.recipe_time}</span>&nbsp&nbsp
				<span>${recipeDto.recipe_difficulty}</span>
			</div>

			<div class="recipe_ingredient">
				<div class="recipe_title">재료</div>
				<div class="recipe_type">
					<ul>
						<c:set var="has1" value="false" />
						<c:set var="has2" value="false" />
						<c:forEach var="ingre" items="${ingreList}">
						<%-- 	<c:if test="${ingre.ingredient_type=='재료' && !has1}">
								<c:set var="has1" value="true" />
								<span>재료</span>
							</c:if>
							<c:if test="${ingre.ingredient_type=='양념' && !has2}">
								<c:set var="has2" value="true" />
								<span>양념</span>
							</c:if> --%>
							<li style="list-style-type: none;">${ingre.ingredient_name}</li>
							<li style="list-style-type: none;">${ingre.ingredient_amount}</li>
							<hr class="divider"/>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="recipe_step">
				<div class="step_title">조리순서</div>
					<div class="stepDiv1">
						<ul>
							<c:forEach var="stepList" items="${stepList}">
								<div class="step_middle">${stepList.step_desc}</div>
								<div class="step_right"><img src="${pageContext.request.contextPath}/Storage/${stepList.step_image_url}" style="width: 150px;height: 150px;"/></div>
							</c:forEach>
						</ul>
					</div>
			</div>
			<br> <br>

			<div class="recipe_review">
				<div class="review_title">
					<h3>
						요리 후기 <span>${reviewCount}</span>
					</h3>
					<c:forEach var="reviewList" items="${reviewList}">
					<div class="review_user_info">
						<h4>${reviewList.user_nickname}</h4>&nbsp&nbsp&nbsp&nbsp
						<span><fmt:formatDate value="${reviewList.review_date}" pattern="yyyy-MM-dd hh:mm:ss"/></span>&nbsp&nbsp&nbsp&nbsp
						<span>
						평점 : <c:forEach var="i" begin="1" end="${reviewList.review_rating}"><i class="fa fa-star" aria-hidden="true" style=" padding:0.1em;"></i></c:forEach>
						</span>
						<c:if test="${currentUser.user_idx==reviewList.user_idx}">
							<button type="button" onclick="location.href='../project/updateReview.do?recipe_id=${recipeDto.recipe_id}&review_id=${reviewList.review_id}';">수정</button>
						</c:if>
						<c:if test="${currentUser.user_idx==reviewList.user_idx}">
							<button type="button" onclick="deleteReview(${reviewList.review_id}, ${reviewList.recipe_id})">삭제</button>
						</c:if>
					</div>
					<div class="review_content">
						<p>${reviewList.review_content} 
						<c:forEach var="reviewImgList" items="${reviewImgList}">
						<c:if test="${reviewList.review_id==reviewImgList.review_id}"> 
							<img src="${pageContext.request.contextPath}/Storage/${reviewImgList.img_image_url}" style="width:50px; height: 50px;"/>
						</c:if>
						</c:forEach></p>
					</div>
					</c:forEach>
				</div>
				<div class="writeReview">
				<input type="hidden" id="recipe_id" name="recipe_id" value="${recipeDto.recipe_id}"/>
				<button type="button" onclick="location.href='../project/writeReview.do?recipe_id=${recipeDto.recipe_id}'" type="button" id="writeReview" class="btn btn-outline-secondary">리뷰 작성하기</button>
				</div>
			</div>
			<br> <br>

			<div class="recipe_comment" id="recipe_comment">
				<div class="comment_title">
					<div class="review_title">
						<h3>
							댓글<span>${commentCount}</span>
						</h3>
						<hr class="divider"/>
						<c:forEach var="commentList" items="${commentList}">
						<div class="comment_user_info">
							<h4>${commentList.user_nickname}</h4>
						<sec:authorize access="isAuthenticated()">
						<c:if test="${commentList.user_idx == currentUser.user_idx}">
							<button type="button" class="delete_comment" data-comment-id="${commentList.comment_id}">삭제</button>
						</c:if>
						</sec:authorize>
						</div>
						<div class="comment_content">${commentList.comment_content}</div>
						<hr class="divider"/>
						</c:forEach>
					</div>
				</div>
				<br><br>
				<div class="write_comment" >
					
						<div class="row">
							<textarea name="comment_content" class="form-control" rows="3" style="resize: none;" placeholder="무엇이 궁금하신가요? 댓글을 남겨주세요."></textarea>
							<button type="submit" id="comment_submit" class="btn btn-outline-secondary">등록</button>
						</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	
/* 	$(function(){
	    $('#writeReview').on('click',function(){
	        var url = '../project/writeReview.do?recipe_id=${recipeDto.recipe_id}'; 
	        window.open(url, '리뷰 작성', 'width=700, height=600, left=100, top=50, scrollbar=yes, resizable=yes');
	    });
	}); */
	
	$(document).ready(function() {
		  $('#comment_submit').click(function() {
		    var comment_content = $('.form-control').val();
		    if(comment_content == '') {
		      alert('댓글 내용을 입력해주세요.');
		      return false;
		    }
		    var data = {
		      recipe_id: '${recipeDto.recipe_id}',
		      comment_content: $('.form-control').val()
		    };
		    $.ajax({
		      type: 'POST',
		      url: 'commentWrite',
		      data: JSON.stringify(data),
		      dataType: 'json',
		      contentType: 'application/json',
		      success: function(response) {
		        // 댓글 갱신
		        var commentList =  response.commentList;
		        var commentCount = response.commentCount;
		        $('#recipe_comment .review_title span').text(commentCount);
		        $('#recipe_comment .comment_user_info').remove();
		        $('#recipe_comment .comment_content').remove();
		        $('#recipe_comment .delete_comment').remove();
		        $('.divider').remove();
		        for(var i=0; i<commentCount; i++) {
		          var comment = commentList[i];
		          var html = '<div class="comment_user_info"><h4>' + comment.user_nickname + '</h4></div>';
		        if (comment.user_idx == '${currentUser.user_idx}') {
		  			html += '<button type="button" class="delete_comment" data-comment-id="' + comment.comment_id + '">삭제</button>';
		  		}
		          html += '<div class="comment_content">' + comment.comment_content + '</div>';
		          html += '<hr class="divider">';
		          $('#recipe_comment .review_title').append(html);
		        }
		        $('textarea[name=comment_content]').val('');
		      },
		      error: function(xhr, status, error) {
		        console.log(error);
		      }
		    });
		    return false;
		  });
	});
	
	$(document).on('click', '.delete_comment', function() {
		var commentId = $(this).data('comment-id');
		var data = {
			comment_id: commentId,
			recipe_id: '${recipeDto.recipe_id}',
			user_idx: '${currentUser.user_idx}',
		};
		$.ajax({
			type: 'POST',
			url: '../project/commentDelete.do',
			data: data,
			dataType: 'json',
		success: function(response) {
		// 댓글 갱신
		var commentList = response;
		var commentCount = commentList.length;
		$('#recipe_comment .review_title span').text(commentCount);
		$('#recipe_comment .comment_user_info').remove();
		$('#recipe_comment .comment_content').remove();
		$('#recipe_comment .delete_comment').remove();
		$('.divider').remove();
		console.log(commentCount);
		for (var i = 0; i < commentCount; i++) {
			var comment = commentList[i];
			var html = '<div class="comment_user_info"><h4>' + comment.user_nickname + '</h4>';
			if (comment.user_idx == '${currentUser.user_idx}') {
			html += '<button type="button" class="delete_comment" data-comment-id="' + comment.comment_id + '">삭제</button>';
		}
			html += '</div>';
			html += '<div class="comment_content">' + comment.comment_content + '</div>';
			html += '<hr class="divider">';
			$('#recipe_comment .review_title').append(html);
		}
			$('textarea[name=comment_content]').val('');
		},
			error: function(xhr, status, error) {
			console.log(error);
		}
		});
		});
	</script>
	<script>
	function deleteReview(review_id, recipe_id) {
		  if (confirm('삭제하시겠습니까?')) {
		    location.href ='../project/deleteReview.do?review_id='+review_id+'&recipe_id='+recipe_id;
		  }
	}
	</script>
</body>
</html>