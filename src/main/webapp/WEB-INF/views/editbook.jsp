<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
       <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Edit Book</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Edit Book</h1>

    <!-- Error message display -->
    <c:if test="${not empty errorMessage}">
        <div class="error">
            ${errorMessage}
        </div>
    </c:if>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/book/updatebook" method="post" modelAttribute="obj">
		Id<form:input path="id" /><br>
		Title<form:input path="title"/><br>
		Author<form:input path="author"/><br>
		price<form:input path="price"/><br>
		<input type="submit" value="update">
	</form:form>
</body>
</html>