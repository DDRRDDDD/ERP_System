<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/orderSuccess.css">
</head>
<body>

	<h2 class="message">주문이 성공적으로 완료되었습니다!</h2>

	<div class="quiz-box">
		<table class="cart-table">
			<thead class="cart-table-head">
				<tr>
					<td>상품명</td>
					<td>가격</td>
					<td>수량</td>
				</tr>
			</thead>
			<tbody class="cart-table-body">
				<c:forEach var="cart" items="${list}">
					<tr>
						<td>${cart.productName}</td>
						<td>${cart.price}</td>
						<td>${cart.quantity}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="info">
		<p id="info-total">총 금액 : ${total}</p>
		<p id="info-message">총알 배송! 항상 신선한 FRESH PICK-KA! 주문해주셔서 감사합니다</p>
	</div>
	<div class="button">
		<a href="/">확인</a>
	</div>
</body>
</html>
