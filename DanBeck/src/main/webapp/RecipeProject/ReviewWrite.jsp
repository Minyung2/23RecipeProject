<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.disk.*,org.apache.commons.fileupload.servlet.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 작성</title>
<style>
.fileList {
	list-style: none;
}
 .star-rating{
	font-size: 0;

}
.star-rating__wrap{
	display: inline-block;
	font-size: 1rem;
	padding-top:30px;
	line-height: 1.7rem;
}
.star-rating__wrap:after{
	content: "";
	display: table;
	clear: both;
}
.star-rating__ico{
	float: right;
	padding-left: 2px;
	cursor: pointer;
	color:#ffcc00;
}
.star-rating__ico:last-child{
	padding-left: 0;
}
.star-rating__input{
	display: none;
}
.star-rating__ico:hover:before,
.star-rating__ico:hover ~ .star-rating__ico:before,
.star-rating__input:checked ~ .star-rating__ico:before
{
	content: "\f005";
} 
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

</head>

<script>
	$(function() {
		$('#upFile').on('click', function() {
			$('#reviewPhoto').click();
		});

		$('#reviewPhoto')
				.change(
						function() {
							var fileList = this.files;
							var fileCount = fileList.length;
							for (var i = 0; i < fileCount; i++) {
								var file = fileList[i];
								var fileName = file.name;
								var fileSize = file.size;

								// Check if file already exists in list
								if ($('.fileList li:contains("' + fileName
										+ '")').length) {
									alert('"' + fileName
											+ '" already exists in the list.');
								} else {
									// Check file size
									var maxSize = 10 * 1024 * 1024; // 10 MB
									if (fileSize > maxSize) {
										alert('"'
												+ fileName
												+ '" is too large. Maximum file size is 10 MB.');
									} else {
										var listItem = $('<li class="fileItem"></li>');
										var nameDiv = $('<div name="review_img[]" class="fileName">'
												+ fileName + '</div>');
										var deleteButton = $('<div class="del_btn">x</div>');
										listItem.append(nameDiv);
										listItem.append(deleteButton);
										$('.fileList').append(listItem);
									}
								}
							}
						});

		$('.fileList').on('click', '.del_btn', function() {
			$(this).parent().remove();
		});
	});
</script>
<body>

		<div class="review" style="width: 95%; display: flex;">
		<form action="../project/writeReview.do" method="post" enctype="multipart/form-data">
		    <input name="user_idx" type="hidden" value="${sessionScope.user.user_idx}"/>
		    <input name="recipe_id" type="hidden" value="${recipe_id}"/>
		    <h3>Write review</h3>
				<span>맛 평점</span>
<!-- 				<span class="star-cb-group"> -->
     			    <div class="star-rating">
      <div class="star-rating__wrap">
        <input class="star-rating__input" id="star-rating-5" type="radio" name="rate" value="5" checked="checked">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5" ></label>
        <input class="star-rating__input" id="star-rating-4" type="radio" name="rate" value="4">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4" ></label>
        <input class="star-rating__input" id="star-rating-3" type="radio" name="rate" value="3">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3" ></label>
        <input class="star-rating__input" id="star-rating-2" type="radio" name="rate" value="2">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2" ></label>
        <input class="star-rating__input" id="star-rating-1" type="radio" name="rate" value="1">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1" ></label>
      </div>
    </div>

<!--   				</span> -->
			<hr>
			<textarea name="review_content" style="width: 35%; height: 150px"
				placeholder="리뷰를 입력하세요"></textarea>
			<br>
			<br>
			<p>이미지 업로드</p>
			<button type="button" id="upFile">파일첨부</button>
			<input type="file" name="fileUpload" class="reviewPhoto"
				id="reviewPhoto" style="display: none;" multiple>
			<ul class="fileList">
			</ul>
			<br>
			<br>
			<button type="submit">완료</button>
			<a href="../RecipeProject/Home.jsp">
			<button type="button">HOME</button>
			</a>
		</form>
	</div>
	
	

</body>
</html>