<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form:form action="${pageContext.request.contextPath}/book/addbookp" method="post" modelAttribute="bookObj">
		<label>title : </label>
		<form:input path="title" /><br>
		<label>author : </label>
		<form:input path="author" /><br>
		<label>price : </label>
		<form:input path="price" /><br>
		<label>status : </label>
		<form:input path="status" /><br>
		<input type="submit" value="add" />
	</form:form>
</body>
</html>