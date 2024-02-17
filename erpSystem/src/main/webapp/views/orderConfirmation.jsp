<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/orderConfirm.css">
</head>
<body>

	<section class="board">
		<form method="POST" action="../service">
			<h1>주문처리상태페이지</h1>
			<table>
				<thead>
					<tr>
						<td>주문번호</td>
						<td>구매아이디</td>
						<td>결제금액</td>
						<td>주문일자</td>
						<td>결제상태</td>
					</tr>
				</thead>

				<c:forEach items="${list}" var="target">
					<tbody>
						<tr>
							<td>${target.id}</td>
							<td>${target.customerId}</td>
							<td>${target.total}</td>
							<td>${target.date}</td>
							<td><a id="check-values"
								onclick="checkValues('${target.id}','${target.status}')"> <c:choose>
										<c:when test="${target.status eq 'Y'}">주문완료</c:when>
										<c:when test="${target.status eq 'D'}">배송중</c:when>
										<c:otherwise>결제전</c:otherwise>
									</c:choose>
							</a></td>
						</tr>
					</tbody>
				</c:forEach>

			</table>
		</form>
		<div style="width: 600px; text-align: center; margin-top: 30px;"
			class="number">

			<c:forEach begin="1" end="${lastPage}" varStatus="info">
				<a
					href="../service?command=getorderconfirmation&vpage=${info.index}">${info.index}</a>
			</c:forEach>
		</div>
	</section>

</body>
<script src="resources/orderConfirmation.js"></script>
</html>