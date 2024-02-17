<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>regist</title>
<link rel="stylesheet" href="../resources/regist.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<section class="regist-box">
		<form method="POST" action="../service">
			<h1 id="info-title">회원정보 입력</h1>
			<input type="hidden" name="command" value="regist">
			<c:choose>
				<c:when test="${not empty gradeId}">
					<input type="hidden" id="gradeId" name="gradeId" value="${gradeId}">
				</c:when>
				<c:otherwise>
					<input type="hidden" id="gradeId" name="gradeId"
						value="${param.gradeId}">
				</c:otherwise>
			</c:choose>
			<table class="table">
				<tr>
					<th id="th">아이디</th>
					<td id="td"><c:choose>
							<c:when test="${not empty id}">
								<input type="text" id="id" name="id" value="${id}" readonly>
							</c:when>
							<c:otherwise>
								<input type="text" id="id" name="id" value="${param.id}"
									readonly>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th id="th">비밀번호</th>
					<td id="td"><input type="text" id="password" name="password"
						placeholder="비밀번호를 입력해주세요"></td>
				</tr>
				<tr>
					<th id="th">이름</th>
					<td id="td"><input type="text" id="name" name="name"
						placeholder="이름을 입력해주세요"></td>
				</tr>
				<tr>
					<th id="th">주소</th>
					<td id="td">
						<div class="address">
							<input type="text" id="address" name="address"
								placeholder="주소를 입력하세요" readonly>
							<button type="button" id="addressSearch" onclick="findAddr()">주소검색</button>
						</div>
					</td>

				</tr>
				<tr>
					<th id="th">핸드폰 번호</th>
					<td id="td"><input type="text" id="phone" name="phone"
						placeholder="번호를 입력해주세요"></td>
				</tr>
				<tr>
					<th id="th">성별</th>
					<td id="td"><select id="gender" name="gender">
							<option value="Male">남자</option>
							<option value="Female">여자</option>
					</select></td>
				</tr>
			</table>
			<div class="button">
				<input type="button" value="등록" onclick="checkValues(form)"
					class="regist">
			</div>

		</form>
	</section>

	<script src="resources/registCheck.js"></script>
</body>
</html>