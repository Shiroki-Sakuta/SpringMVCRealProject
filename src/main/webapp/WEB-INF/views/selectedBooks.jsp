<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <jsp:include page="header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
</head>
<body>
	<h2>Selected Books</h2>

<form action="placeOrder" method="post">
    <table class="table table-striped">
        <tr class="table-primary">
            <th>No</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Remove</th>
        </tr>
        <c:forEach var="book" items="${selectedBooks}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td>
                    <input type="checkbox" name="finalBooks" value="${book.id}" checked> Keep in Order
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <!-- Submit button to finalize the order -->
    <input type="submit" value="Place Order">
</form>
</body>
</html>
<jsp:include page="footer.jsp"></jsp:include>