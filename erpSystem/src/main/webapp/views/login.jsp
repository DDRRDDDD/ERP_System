<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>log-in</title>
<link rel="stylesheet" href="../resources/login.css">
</head>
<body>

	<form action="../service" method="POST" class = "login">
		<input type="hidden" name="command" value="login"> 
		<h2 id="title">로그인</h2>
		<label for="id">아이디:</label> <input type="text" id="id" name="id">
		<label for="password">비밀번호:</label> <input type="password" id="password" name="password"> 
		<input type="submit" value="로그인" onclick="checkValues(form)">
		
		<c:set var="message" value="${requestScope.message }"/>
		<c:if test="${not empty message}">
			<p style="color:#AC7088;">${message}</p>		
		</c:if>
	</form>

	<script src="../resources/loginCheck.js"></script>
</body>
</html>