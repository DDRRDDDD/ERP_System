<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/productList.css">
<title>productList</title>
</head>
<body>
	<c:set var="list" value="${list}" />
	<div class="product-list">
		<h2 id="name">
			<c:out value="${result}" />
		</h2>
		<c:choose>
			<c:when test="${not empty list}">
				<div class="lists">
					<c:set var="target" value="${list }" />
					<c:forEach items="${list}" var="target">
						<div class="cards">
							<div class="card"
								onclick="location.href='../service?command=productdetail&productId=${target.id}'">
								<img src="${target.imageUrl}" width="200" height="200">
							</div>
							<div class="inform">
								<a
									href='../service?command=productdetail&productId=${target.id}'
									class="product"> ${target.name}</a>
								<p>${target.price}원</p>
								<p>수량:${target.stock}개</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<img src="../resources/images/emptyCart.jpg">
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>