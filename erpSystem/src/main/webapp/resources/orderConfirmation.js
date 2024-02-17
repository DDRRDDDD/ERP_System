/**
 * 
 */
function checkValues(orderId, orderStatus) {

	let url = `../service?command=orderconfirmation&salesOrderId=${orderId}
	&salesOrderStatus=${orderStatus}
	`;
	if (orderStatus === "D") {
		alert("배송처리 상태입니다.");
	}	
	else {
		var UP;
		UP = confirm("주문상태를 처리 하시겠습니까?");

		if (UP) {
			location.href = url;
		}

	}


}