<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../common/rate.css?v=3">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.6.4.js"
	integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
	crossorigin="anonymous"></script>
<script>
$(function(){
	var delayTimer;
	$('#searchRecipe').on('keyup', function() {
	  clearTimeout(delayTimer);
	  delayTimer = setTimeout(function() {
	    $.ajax({
	      url: "/project/autoSearch.do",
	      data: {
	        recipe_name: $("#searchRecipe").val()
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
	
</script>
</head>
<body>
	<form action="#" method="get">
		<input type="text" id="searchRecipe"> 
		<div id="suggestion_keyword">
			
		</div>
		<input type="submit" value="돋보기">
	</form>
	
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
</body>
</html>