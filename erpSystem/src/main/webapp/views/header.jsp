<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/header.css">
</head>
<body>
	<c:set var="main" value="${applicationScope.main}"/>
	<c:set var="customer" value="${sessionScope.log}"/>
	<div class="header">
		<a class="banner" href="index.jsp"> <img
			src="../resources/images/My project-1.jpg">
		</a>
	</div>

	<ul class="nav-list-main">
		<c:choose>
			<c:when test="${empty sessionScope.log}">
				<li><a id="regist" href="../?content=registTermsOfUse">회원가입</a>

				</li>
				<li><a id="login" href="../?content=login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${sessionScope.log.getId() != 99999 }">
						<li><a id="mypage"
							href="../service?command=getmypage&custid=${customer.id}">
								마이페이지</a></li>
					</c:when>
					<c:otherwise>
						<li><a id="adminpage" href="../?content=adminpage">
								관리자페이지</a></li>
					</c:otherwise>
				</c:choose>
				<li><a id="logout" onclick="sendCommand('logout')">로그아웃</a></li>
				<li><a id="basket" onclick="send('getcartlist')">장바구니</a></li>
			</c:otherwise>
		</c:choose>
		<c:set var="list" value="${main.boardList}"/>
		<c:forEach items="${list}" var="target">
			<li><a href="../service?command=boardlist&categoryId=${target.id }">${target.name }</a>

			</li>
		</c:forEach>
		<li>
			<form method="POST" action="../service" name="search">
				<input type="hidden" name="command" value="productlist">
				<section class="search-box">
					<input type="text" name="keyword" placeholder="주문할 것 pickka!">
					<button type="submit">Search</button>
				</section>
			</form>
		</li>
	</ul>
</body>
</html>