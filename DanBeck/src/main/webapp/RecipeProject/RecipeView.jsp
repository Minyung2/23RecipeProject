<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<style>
/* .new_comment {
    background-color: #f7e6ff;
    animation-name: commentFadeIn;
    animation-duration: 3s;
    animation-timing-function: ease-out;
    animation-iteration-count: 1;
}

@keyframes commentFadeIn {
    from {
        background-color: #f7e6ff;
    }
    to {
        background-color: #ffffff;
    }
} */
</style>	
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
						요리 후기 <span>${ratingCount}</span>
					</h3>
					<c:forEach var="ratingList" items="${ratingList}">
					<div class="review_user_info">
						<h4>${ratingList.user_nickname}</h4>&nbsp&nbsp&nbsp&nbsp
						<span><fmt:formatDate value="${ratingList.rating_date}" pattern="yyyy-MM-dd hh:mm:ss"/></span>&nbsp&nbsp&nbsp&nbsp
						<span>
						평점 : <c:forEach var="i" begin="1" end="${ratingList.rating}"><i class="fa fa-star" aria-hidden="true" style=" padding:0.1em;"></i></c:forEach>
						</span>
					</div>
					<div class="review_content">
						<p>${ratingList.rating_content}</p>
					</div>
					</c:forEach>
				</div>
				<div class="writeReview">
					<a href="../project/recipeview.do"><button type="button" id="writeReview" class="btn btn-outline-secondary">리뷰 작성하기</button></a>
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
						</div>
						<div class="comment_content">${commentList.comment_content}</div>
						<hr class="divider"/>
						</c:forEach>
					</div>
				</div>
				<br><br>
				<div class="write_comment" >
					<input type="hidden" name="recipe_id" value="${recipeDto.recipe_id}"/>
						<div class="row">
							<textarea name="comment_content" class="form-control" rows="3" style="resize: none;" placeholder="무엇이 궁금하신가요? 댓글을 남겨주세요."></textarea>
							<button type="submit" id="comment_submit" class="btn btn-outline-secondary">등록</button>
						</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	$(function(){
	    $('#writeReview').on('click',function(){
	        var userNick = '${sessionScope.user.user_nickname}';
	        var url = '../project/reviewWrite.do?user_nickname=' + userNick; // GET 파라미터를 만듭니다.
	        window.open(url, '리뷰 작성', 'width=700, height=600, left=100, top=50, scrollbar=yes, resizable=yes');
	    });
	});
	
	$(document).ready(function() {
		  $('#comment_submit').click(function() {
		    var comment_content = $('.form-control').val();
		    if(comment_content == '') {
		      alert('댓글 내용을 입력해주세요.');
		      return false;
		    }
		    var data = {
		      recipe_id: '${recipeDto.recipe_id}',
		      user_idx: '2',
		      comment_content: $('.form-control').val()
		    };
		    $.ajax({
		      type: 'POST',
		      url: '../project/commentWrite.do',
		      data: data,
		      dataType: 'json',
		      success: function(response) {
		        // 댓글 갱신
		        var commentList = response;
		        var commentCount = commentList.length;
		        $('#recipe_comment .review_title span').text(commentCount);
		        $('#recipe_comment .comment_user_info').remove();
		        $('#recipe_comment .comment_content').remove();
		        $('.divider').remove();
		        for(var i=0; i<commentCount; i++) {
		          var comment = commentList[i];
		          var html = '<div class="comment_user_info"><h4>' + comment.user_nickname + '</h4></div>';
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
	</script>
</body>
</html>