<%@page import="java.util.ArrayList"%>
<%@page import="productCategory.controller.ProductCategoryDAO"%>
<%@page import="productCategory.ProductCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<title>Insert title here</title>
<body>
	<%
	ProductCategoryDAO dao = ProductCategoryDAO.getInstance();
	ArrayList<ProductCategory> list = dao.getProductCategoryList();
	
	pageContext.setAttribute("list", list);
	%>
	<div class="category-list">

		<c:forEach items="${pageScope.list}" var="target">
			<div class="list">
				<a href="../service?command=productlist&code=${target.id}"><c:out
						value="${target.name}" /></a>
			</div>
		</c:forEach>
	</div>

</body>
</html>