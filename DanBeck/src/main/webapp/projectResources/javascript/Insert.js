
// 재료 추가 배열
var ingredients = [
	{
		name: "돼지고기",
		amount: "300g"
	},
	{
		name: "양배추",
		amount: "1/2개"
	},
	{
		name: "참기름",
		amount: "1T"
	},
	{
		name: "소금",
		amount: "1/4T"
	}
];
var currentIngredientIndex = 0;
var currentIdIndex = 5;


// 재료 추가 버튼 기능
$(function() {
	$('#addIngredientGroup').on('click', function() {
		var currentIngredient = ingredients[currentIngredientIndex];
		var newListItem = $('<li class="ingreTextList"><input name="ingredient_name[]" type="text" class="ingreInput" placeholder="예)' + currentIngredient.name + '"></input> <input name="ingredient_amount[]" type="text" class="ingreInput" placeholder="예)' + currentIngredient.amount + '"></input> <img class="ingreDelBtn" src="../projectResources/img/x-mark.png"></li>');
		newListItem.attr('id', 'ingredient_text_list' + currentIdIndex);
		$('.delBtn', newListItem).attr('id', 'ingredient_text_list'+currentIdIndex+'_delBtn');
		currentIdIndex++;
		$('#ingredient_group_list').append(newListItem);
		currentIngredientIndex = (currentIngredientIndex + 1) % ingredients.length;
	});
});

// 재료에 마우스 올라오면 삭제 버튼 생기기
$(function(){
	$('#ingreTextList').hover(
	function(){
      var id = $(this).attr('id');
      $('img[id="'+id+'_delBtn"]').css('display', 'inline-block');
    },
    function(){
      var id = $(this).attr('id');
      $('img[id="'+id+'_delBtn"]').css('display', 'none');
  })
});

// 재료 삭제 버튼 기능
$(function(){
  $(document).on('click', '.ingreDelBtn', function(){
    $(this).parent().remove(); 
  })
})

var currentStepIndex = 0;
var currentStepIdIndex = 2;

// 요리 순서 추가 버튼
var steps = [
	"예) 준비된 양념으로 먼저 고기를 조물조물 재워 둡니다.",
	"예) 그 사이 양파와 버섯, 대파도 썰어서 준비하세요.",
	"예) 고기가 반쯤 익어갈 때 양파를 함께 볶아요.",
	"예) 소고기는 기름기를 떼어내고 적당한 크기로 썰어주세요."
];

// step 추가 버튼
$(function(){
	$('#addStepDetailGroup').on('click',function(){
		var currentStepString = steps[currentStepIndex];
		var newDivItem = $('<div class="step_div"><p class="step_p">🔻</p><div id="step_div_text'+currentStepIdIndex+'"><textarea name="step_text[]" class="form-control step_cont" placeholder="'+currentStepString+'"></textarea></div><div id="step_div_photo'+currentStepIdIndex+'"><img id="step_img_photo'+currentStepIdIndex+'" class="stepDivPhoto" src="../projectResources/img/imageAdd.png"><input type="file" name="fileUpload" class="fileUpload" id="fileUpload'+currentStepIdIndex+'" style="display: none;"></div><img id="step_div_text'+currentStepIdIndex+'_delBtn" class="stepDelBtn" src="../projectResources/img/file-x.svg"><br></div>');
		newDivItem.attr('id',"step_div"+currentStepIdIndex);
		currentStepIdIndex++;
		$('.step_row').append(newDivItem);
		currentStepIndex = (currentStepIndex + 1) % steps.length;
	});
});	

$(function(){
  $(document).on('click', '.stepDelBtn', function(){
    $(this).parent().remove(); 
  })
})

// step 에 hover 시 삭제 버튼 생기기
$(document).on('mouseenter', '.step_row .step_div', function() {
  $(this).find('.stepDelBtn').css('display', 'inline-flex');
});

$(document).on('mouseleave', '.step_row .step_div', function() {
  $(this).find('.stepDelBtn').css('display', 'none');
});

// 사진 첨부 기능
$(document).on('click', '.stepDivPhoto', function(){
  $(this).siblings('.fileUpload').trigger('click');
});

$(document).on('change', '.fileUpload', function(){
  var input = this;
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $(input).siblings('.stepDivPhoto').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
});
    
