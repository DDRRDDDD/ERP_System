<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/boardWrite.css">
</head>
<body>
	<c:set var="title" value="${requestScope.title}" />
	<c:set var="autofocus" value="" />
	<c:if test="${empty title}">
		<c:set var="title" value="" />
		<c:set var="autofocus" value="autofocus" />
	</c:if>
	
	<section>
		<h1>${requestScope.name}수정</h1>
		<form method="POST" action="../service">
			<div class="write">
				<input type="hidden" name="command" value="boardModify"> <input
					type="hidden" name="categoryId" id="categoryId"
					value="${requestScope.categoryId}"> <input type="hidden"
					name="id" id="id" value="${requestScope.id}">

				<table>
					<tr>
						<th>Title</th>
						<td><input type="text" id="title" name="title"
							value="${title}"></td>
					</tr>
					<tr>
						<th>Content</th>
						<td><textarea id="main" name="main">${requestScope.main}</textarea></td>
					</tr>

				</table>
			</div>
			<div class="butt">
				<input type="button" onclick="boardCheckModify(form)" value="수정"
					class="button">
			</div>
		</form>

	</section>

	<script src="resources/boardCheck.js"></script>

</body>
</html>