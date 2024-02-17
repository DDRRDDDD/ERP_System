<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mypage</title>
</head>
<link rel="stylesheet" href="../resources/mypage.css">
<body>
	<div class="container1">

		<h2 class="pagename">MYPAGE</h2>

		<div class="cust_info">
			<ul>
				<li><h4 class="cust_name">${customer.name}(${customer.id})님
					</h4></li>
				<li>전화번호 : ${customer.phone}</li>
				<li>이메일 : ${customer.address}</li>
			</ul>
		</div>

		<h5>내 주문내역</h5>
		<div class="my-order">
			<table>
				<tr class="thead">
					<td>주문코드</td>
					<td>주문일자</td>
					<td>결제금액</td>
					<td>주문상세</td>
				</tr>
				<c:choose>
					<c:when test="${not empty list}">
						<c:forEach items="${list}" var="target">
							<tr class="order-tbody">
								<td>${target.id}</td>
								<td>${target.date}</td>
								<td>${target.total}원</td>
								<td><c:choose>
										<c:when test="${target.status eq 'Y'}">결제완료</c:when>
										<c:when test="${target.status eq 'D'}">배송중</c:when>
										<c:otherwise>결제 전</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<td>주문 내역이 없습니다.</td>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<h5>등록 게시글</h5>
		<div class="my-board">
			<table>
				<tr class="thead">
					<td>등록일자</td>
					<td>제목</td>
					<td>게시판</td>
				</tr>

				<c:choose>
					<c:when test="${not empty list}">
						<c:forEach items="${blist}" var="targets">
							<tr class="order-tbody">
								<td>${targets.registeredDate}</td>
								<td>${targets.title}</td>
								<c:choose>

									<c:when test="${targets.categoryId eq 11}">
										<td>NOTICE</td>
									</c:when>
									<c:when test="${targets.categoryId eq 12}">
										<td>REVIEW</td>
									</c:when>
									<c:otherwise>
										<td><c:out value="Q&A" /></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<td>주문 내역이 없습니다.</td>
					</c:otherwise>
				</c:choose>
			</table>
		</div>

		<div class="button-container">
			<input class="button" type="button" value="정보수정"
				onclick="location.href='../service?command=getregist'"> <input
				class="button" type="button" value="회원탈퇴"
				onclick="location.href='../?content=dropcustomer'">
		</div>
	</div>


</body>

</html>