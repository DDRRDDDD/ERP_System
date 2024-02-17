
function checkValues(htmlForm) {
	let url = "../?content=regist";

	const id = document.getElementById("id").value;
	const password = document.getElementById("password").value;
	const name = document.getElementById("name").value;
	const address = document.getElementById("address").value;
	const phone = document.getElementById("phone").value;
	const gender = document.getElementById("gender").value;
	const gradeId = document.getElementById("gradeId").value;

	let check = true;
	

	if (id !== "") { url += "&id=" + id; }
	if (password !== "") { url += "&password=" + password; }
	if (name !== "") { url += "&name=" + name; }
	if (address !== "") { url += "&address=" + address; }
	if (phone !== "") { url += "&phone=" + phone; }
	if (gender !== "") { url += "&gender=" + gender; }
	if (gradeId !== "") { url += "&gradeId=" + gradeId; }

	
	if (password === "") {
		alert('비밀번호를 입력해주세요.');
		check = false;
	}
	else if (name === "") {
		alert('이름을 입력해주세요.');
		check = false;
	}
	else if (address === "") {
		alert('주소를 입력해주세요.');
		check = false;
	}
	else if (phone === "") {
		alert('번호를 입력해주세요.');
		check = false;
	}

	if (check === true) {
		htmlForm.submit();	
	}
}

function findAddr() {
		new daum.Postcode({
        	oncomplete: function(data) {
            	
                var addr = '';
                            
                if (data.userSelectedType === 'R') { // 도로명 주소를 선택했을 경우(R)
                    addr = data.roadAddress;
                } else { // 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                

                $("#address").val(addr);
                
        	}
     	}).open();
}
function checkBox() {
	
	$(".checkbox").on("click", "#checkall", function() {
		$(this).parents(".checkbox").find('input').prop("checked", $(this).is(":checked"));
	});

	
	$(".checkbox").on("click", ".check", function() {
		var is_checked = true;

		$(".checkbox .check").each(function() {
			is_checked = is_checked && $(this).is(":checked");
		});

		$("#checkall").prop("checked", is_checked);
	});

}
function checkPopUp() {

	$(document).ready(function() {
		$(".button-open").click(function() {
			$(".popup-box").show();
			$("#mask").fadeIn(100);
		});
		$(".button-close").click(function() {
			$(".popup-box").hide();
			$("#mask").fadeOut(100);
		});

		// 팝업 중앙 정렬
		var $layerPopup = $(".popup-box");
		var left = ($(window).scrollLeft() + ($(window).width() - $layerPopup.width()) / 2);
		var top = ($(window).scrollTop() + ($(window).height() - $layerPopup.height()) / 2);
		$layerPopup.css({ "left": left, "top": top, "position": "absolute" });
		$("body").css("position", "relative").append($layerPopup);
	});


}
	
	