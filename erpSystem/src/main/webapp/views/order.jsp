<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/order.css">
<title>Insert title here</title>
</head>
<body>
	

	<h3 class="titles">${sessionScope.log.name}님의장바구니</h3>

	<c:set var="list" value="${pageScope.list }" />
	<c:choose>
		<c:when test="${not empty list}">
			<table class="tbl">
				<thead class="orderhead">
					<tr>
						<td>no.</td>
						<td>상품명</td>
						<td>수량</td>
						<td>가격</td>
						<td>취소</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${list}" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${product.name}</td>
							<td>${map[product.id]}</td>
							<td>${product.price * map[product.id]}</td>
							<td>
								<button onclick="dropItem('${product.id}')" class="drop">취소</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<p class="total">총 주문금액 : ${total}</p>
			<form method="POST" action="../service" class="ord">
				<input type="hidden" name="command" value="order">
				<div class="but">
					<input type="submit" value="주문하기" class="button">
				</div>
			</form>

		</c:when>
		<c:otherwise>
			<div class="tung">
				<img src="../resources/images/emptyJang.jpg" />
			</div >
		</c:otherwise>
	</c:choose>


	<script src="../resources/validation.js"></script>
</body>

</html>