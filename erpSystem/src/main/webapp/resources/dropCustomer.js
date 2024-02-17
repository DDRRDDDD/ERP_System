/**
 * 
 */
function checkValues(htmlForm) {
	let url = "dropcustomer?";



	const password = document.getElementById("password").value;
	
	
	let check = true;


	if (password !== "") { url += "&password=" + password; }


	if (password === "") {
		alert('비밀번호를 입력해주세요.');
		check = false;
	}

	if (check === false) {
		location.href = url;
	}
	else {
		var UP;
		UP = confirm("정말 탈퇴 하시겠습니까?");
	}

	if (UP) {
		htmlForm.submit()
	}
}