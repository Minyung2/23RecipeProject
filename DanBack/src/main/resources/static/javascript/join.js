function sample6_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			var fullAddr = '';
			var extraAddr = '';
			if (data.userSelectedType === 'R') {
				fullAddr = data.roadAddress;
			} else {
				fullAddr = data.jibunAddress;
			}
			if (data.userSelectedType === 'R') {
				if (data.bname !== '') {
					extraAddr += data.bname;
				}
				if (data.buildingName !== '') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
			}
			document.getElementById('m_addr').value = fullAddr;
			function handleClick() {
				sample6_execDaumPostcode();
				window.close(); // 팝업창에서 호출하는 경우에만 동작합니다.
			}
		}
	}).open();
}


function errFunc(e) {
	alert("실패: " + e.status);
}


$(function() {

	// 아이디 유효성
	$('#id').on('keyup', function() {
		let user_id = $('#id').val();
		if (user_id.trim() != "") {
			$.ajax({
				url: "idCheck",
				type: "post",
				contentType: "text/plain",
				data: user_id,
				success: function(response) {
					if (response.idValid == "사용 가능한 아이디 입니다.") {
						$('#idStatus').text(response.idValid).css('color', 'green');
					} else {
						$('#idStatus').text(response.idValid).css('color', 'red');
					}
				},
				error: function(error) {
					console.log(error);
				}
			});
		}else{
			$('#idStatus').text("필수 입력 정보입니다.").css('color', 'red');
		}
	});

	// 패스워드 유효성
	$('#pw').on('focusout', function() {
		let user_pw = $('#pw').val();
		if (user_pw.trim() != "") {
		$.ajax({
			url: "pwCheck",
			type: "post",
			contentType: "text/plain",
			data: user_pw,
			success: function(response) {
				if (response.pwValid == "사용 가능한 패스워드 입니다.") {
					$("#pwStatus").text(response.pwValid).css('color', 'green');
				} else {
					$("#pwStatus").text(response.pwValid).css('color', 'red');
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
		}else{
			$('#pwStatus').text("필수 입력 정보입니다.").css('color', 'red');
		}
	});
	
	$('#pwDoubleCheck').on('focusout',function(){
		let user_pw = $('#pw').val();
		let double_pw = $('#pwDoubleCheck').val();
		if(user_pw.trim()!="" && double_pw.trim()!=""){
			if(user_pw == double_pw){
				$('#pwDoubleCheckStatus').text("패스워드가 일치합니다.").css('color', 'green');
			}else{
				$('#pwDoubleCheckStatus').text("패스워드가 일치하지 않습니다.").css('color', 'red');
			}
		}else{
			$('#pwDoubleCheckStatus').text("필수 입력 정보입니다.").css('color', 'red');
		}
	})
	
	
	// 이름 유효성
	$('#name').on('keydown', function() {
		let user_name = $('#name').val();
		if (user_name.trim() != "") {
		$.ajax({
			url: "nameCheck",
			type: "post",
			contentType: "text/plain",
			data: user_name,
			success: function(response) {
				if (response.nameValid == "사용 가능한 이름입니다.") {
					$("#nameStatus").text(response.nameValid).css('color', 'green');
				} else {
					$("#nameStatus").text(response.nameValid).css('color', 'red');
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
		}else{
			$('#nameStatus').text("필수 입력 정보입니다.").css('color', 'red');
		}
	});
	
	// 닉네임 유효성
	$('#nickname').on('keyup', function() {
		let user_nickname = $('#nickname').val();
		if (user_nickname.trim() != "") {
			$.ajax({
				url: "nicknameCheck",
				type: "post",
				contentType: "text/plain",
				data: user_nickname,
				success: function(response) {
					if (response.nicknameValid == "사용 가능한 닉네임입니다.") {
						$('#nicknameStatus').text(response.nicknameValid).css('color', 'green');
					} else {
						$('#nicknameStatus').text(response.nicknameValid).css('color', 'red');
					}
				},
				error: function(error) {
					console.log(error);
				}
			});
		}else{
			$('#nicknameStatus').text("필수 입력 정보입니다.").css('color', 'red');
		}
	});
	
	// 핸드폰 유효성
	$('#phone').on('keydown', function() {
		let user_phone = $('#phone').val();
		if (user_phone.trim() != "") {
			$.ajax({
				url: "phoneCheck",
				type: "post",
				contentType: "text/plain",
				data: user_phone,
				success: function(response) {
					if (response.phoneValid == "사용 가능한 번호입니다.") {
						$('#phoneStatus').text(response.phoneValid).css('color', 'green');
					} else {
						$('#phoneStatus').text(response.phoneValid).css('color', 'red');
					}
				},
				error: function(error) {
					console.log(error);
				}
			});
		}else{
			$('#phoneStatus').text("필수 입력 정보입니다.").css('color', 'red');
		}
	});
	
	function checkAllInputs() {
    let allGreen = true;
    $('span').each(function() {
        if ($(this).css('color') !== 'rgb(0, 128, 0)') {
            allGreen = false;
            return false;
        }
    });

    if (allGreen) {
        $('input[type="submit"]').prop('disabled', false);
    } else {
        $('input[type="submit"]').prop('disabled', true);
    }
}

// 일정 시간 간격으로 입력 상태를 확인하고 submit 버튼 활성화 결정
setInterval(checkAllInputs, 100);
});


