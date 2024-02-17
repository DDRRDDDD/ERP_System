<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../resources/main.css">
<jsp:useBean id="main" class="model.MainVariables" scope="application"/>
<c:set var="main" value="${applicationScope.main}"/>
</head>
<c:import url="header" />
<body>
	<div class="container">
		<div class="category-list">
			<c:import url="category" />
			<img id="category-img" src="../resources/images/salesBanner.jpg">
		</div>
		<div class="main-banner">
			<c:choose>
				<c:when test="${not empty param.content }">
					<c:import url="${param.content }" />
				</c:when>
				<c:when test="${not empty requestScope.content }">
					<c:import url="${requestScope.content }" />
				</c:when>
				<c:otherwise>
					<img id="event-banner" src="../resources/images/event-banner.jpg">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script src="../resources/validation.js"></script>
</body>
<c:import url="footer" />
</html>