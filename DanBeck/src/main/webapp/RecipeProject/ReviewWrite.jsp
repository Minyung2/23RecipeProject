<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../ProjectResources/css/ReviewRate.css?v=3">
</head>
<body>
	<div class="review" style="width: 95%; display: flex;">
		<form action="../project/writeRivew.do">
		<div class="left" style="width: 45%">
			<h3>Write review</h3>
			<div>
			<span>맛 평점</span>
				<div class="star-rating">
				<input type="radio" id="5-stars" name="rate" value="5" checked="checked" />
  				<label for="5-stars" class="star">&#9733;</label>
  				<input type="radio" id="4-stars" name="rate" value="4" />
  				<label for="4-stars" class="star">&#9733;</label>
  				<input type="radio" id="3-stars" name="rate" value="3" />
  				<label for="3-stars" class="star">&#9733;</label>
  				<input type="radio" id="2-stars" name="rate" value="2" />
  				<label for="2-stars" class="star">&#9733;</label>
  				<input type="radio" id="1-star" name="rate" value="1" />
  				<label for="1-star" class="star">&#9733;</label>
  				</div>
			</div>
			<hr>
			<div>
				<textarea name="content" style="width: 35%; height: 150px" placeholder="리뷰를 입력하세요"></textarea>
			</div>
			<hr>
		</div>
		<div class="middle" style="width: 10%; display: flex; justify-content: center; align-items: center;">
			<hr style="border: 1px solid black; height: 100%;">
		</div>
		<div class="right" style="width: 45%">
			<div class="right top">
			<span>난이도</span>
			<span>중</span>
			</div>
			<div class="right bottom">
				<img id="mainPhotoHolder" onclick="browseMainFile()" src="../projectResources/img/camera.jpg" style="width: 250px; height: 250px; cursor:pointer">
				<input type="file" name="mainPhotoUpload" class="mainPhotoUpload" id="mainPhotoUpload" style="display: none;">
			</div>
		</div>
			<button type="submit">완료</button>
			<a href="../RecipeProject/Home.jsp">
			<button type="button">HOME</button>
			</a>
		</form>
	</div>
</body>
</html>