<%@page import="java.util.List"%>
<%@page import="javax.websocket.Session"%>
<%@page import="com.spring.model.UserBean"%>
<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <jsp:include page="header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${name!=null}">
	Login user : ${name} 
	<a href="${pageContext.request.contextPath}/">Logout</a>
	</c:if>
	
	
	<div class="container">
  <div class="table-responsive">

    <form action="../book/showaddcard" method="post">
    	<table class="table table-striped">
      <tr class="table-primary">
        <th>No</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Update</th>
      </tr>
  <c:choose>
        <c:when test="${empty list}">
          <tr>
            <td colspan="5" class="text-center">No data available</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach var="book" items="${list}">
            <tr class="table-primary">
              <td>${book.id}</td>
              <td>${book.title}</td>
              <td>${book.author}</td>
              <td>${book.price}</td>
              <td>
                <input type="checkbox" name="selectedBooks" value="${book.id}">
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
    
    	<!-- Submit Button to Review Selected Books -->
				<input type="submit" value="Add to Cart">
    </form>
    
  </div>
</div>
	
	
</body>
</html>
<jsp:include page="footer.jsp"></jsp:include>