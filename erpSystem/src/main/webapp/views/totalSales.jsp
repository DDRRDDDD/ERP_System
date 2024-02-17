<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<!-- jQuery 라이브러리 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<!-- 
데이터를 받아와서, 그 데이터를 JSONARRAY 값에 다 넣어줘야해..
 -->
	<!-- AJAX 호출 -->

	<div class="container">
		<div class="button-container">
			<button type="button" id="btn">등급별 매출</button>
			<button type="button" id="btn2">월별 매출</button>
		</div>
		<div id="column_chart_div1" style="width: 900px; height: 500px;"></div>
	</div>

	<script src="../resources/drawGraph.js"></script>
</body>
</html>