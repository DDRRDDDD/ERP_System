<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/productDetail.css">
</head>

<body>
	<c:set var="product" value="${requestScope.targetProduct}"
		scope="session" />
	<c:set var="category" value="${requestScope.productCategory}"
		scope="session" />
	<c:set var="max" value="${requestScope.targetProduct.getStock()}" />

	<div class="product-detail-container">
		<img src="${requestScope.targetProduct.getImageUrl()}"
			class="product-img">

		<div class="detail-info">
			<span class="product-name"> <c:out
					value="상품 이름 : ${requestScope.targetProduct.getName() }" />
			</span> <span class="product-price"> <c:out
					value="상품 가격 : ${requestScope.targetProduct.getPrice() }" />
			</span> <span> <c:out
					value="제조회사 : ${requestScope.targetProduct.getPipeLine() }" />
			</span> <span> <c:out
					value="분류 : ${requestScope.productCategory.getName() }" />
			</span>
			<form method="POST" action="<c:url value='../service'/>">
				<input type="hidden" name="command" value="addcart"> <input
					type="hidden" name="product" value="${product.id}"> <input
					type="hidden" name="category" value="${category.id}"> <input
					type="number" name="count" value="1" min="0" max="${max}" required>
				<c:choose>
					<c:when test="${empty sessionScope.log }">
						<input type="button" value="장바구니 추가" onclick="send('login')">
						<input type="button" value="바로구매" onclick="send('login')">
					</c:when>
					<c:otherwise>
						<input type="submit" name="choose" value="장바구니 추가"
							onclick="insertCart()">
						<input type="button" name="choose" value="바로구매"
							onclick="purchase(${total})">
					</c:otherwise>
				</c:choose>
			</form>
		</div>
	</div>
	<script src="../resources/validation.js"></script>
</body>
</html>