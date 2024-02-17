<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<title>Insert title here</title>
<body>
	<c:set var="main" value="${applicationScope.main}"/>
	<div class="category-list">
		<c:forEach items="${main.cateList}" var="target">
			<div class="list">
				<a href="../service?command=productlist&code=${target.id}"><c:out
						value="${target.name}" /></a>
			</div>
		</c:forEach>
	</div>

</body>
</html>