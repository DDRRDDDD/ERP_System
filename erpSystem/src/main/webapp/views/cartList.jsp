<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/cartList.css">
</head>
<body>

	<c:set var="customer" value="${sessionScope.log}" scope="session" />

	<h3 class=title>${sessionScope.log.name}님의장바구니</h3>

	<div class="cartList">
		<c:choose>
			<c:when test="${not empty list}">
				<table class="cart-table">
					<thead class="cart-table-head">
						<tr>
							<td>상품명</td>
							<td>가격</td>
							<td>수량</td>
							<td>취소</td>
						</tr>
					</thead>
					<tbody class="cart-table-body">
						<c:forEach var="cart" items="${list2}">
							<tr>
								<td>${cart.productName}</td>
								<td>${cart.price}</td>
								<td>${cart.quantity}</td>
								<td><a
									 id = "cancel" href="../service?command=deletecart&product=${cart.pruductId}&cust=${sessionScope.log.id}&count=${cart.quantity}&total=${total}">취소</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="purchase">
					<p>총 금액 : ${total} 원</p>
					<button onclick="orderComplete(${total})">주문하기</button>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tung">
					<img src="../resources/images/emptyJang.jpg" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>