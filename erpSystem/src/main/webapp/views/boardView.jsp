<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/boardView.css">
</head>
<body>

	<c:set var="boardId" value="${param.id}" />
	<c:set var="boardDao" value="<%= board.controller.BoardDAO.getInstance() %>" />
	<c:set var="board" value="${boardDao.getBoardById(boardId)}" />
	<c:set var="categoryId" value="${board.categoryId}" />
	<c:set var="boardCategoryDao" value="<%= boardCategory.controller.BoardCategoryDAO.getInstance() %>" />
	<c:set var="category" value="${boardCategoryDao.getCategoryNameById(categoryId)}" />
	<c:set var="customer" value="${sessionScope.log}" />

	<section class="board">
		<h1>${category}</h1>
		<table>
			<thead>
				<tr>
					<th class="title">${board.title}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="nav">글쓴이 : ${board.writer} | 등록일 : ${board.registeredDate}</th>
				</tr>
				<tr>
					<th class="main">${board.main}</th>
				</tr>
			</tbody>
		</table>
		<c:if test="${not empty sessionScope.log}">
			<c:set var="customerId" value="${sessionScope.log.id}"/>
			<c:if test="${customerId == board.writer || customerId == 99999}">
				<div class="click">
					<form method="POST" action="../service">
						<input type="hidden" name="command" value="getboardmodify"/>
						<input type="hidden" name="id" value="${boardId}" />
						<div>
							<input type="submit" value="수정하기" />
						</div>
					</form>
					<form method="POST" action="../service">
						<input type="hidden" name="command" value="boardDelete" />
						<input type="hidden" id=id name="id" value="${boardId}" />
						<input type="hidden" id="categoryId" name="categoryId" value="${categoryId}" />
						<div>
							<input type="button" value="삭제하기" onclick="boardCheckDelete(form)" />
						</div>
					</form>
				</div>
			</c:if>
		</c:if>
	</section>

	<script src="resources/boardCheck.js"></script>
</body>
</html>
