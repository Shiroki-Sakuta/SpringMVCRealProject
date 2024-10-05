
<%@page import="javax.websocket.Decoder.Text"%>
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
<% 
    String displayStyle = "none";
    if (request.getAttribute("error") != null) {
        displayStyle = "block";
    } 

%>
 <span style="display: <%= displayStyle %>;"><%= request.getAttribute("error") %></span> 



	<form:form action="insert" method="post" modelAttribute="userObj">
		<label>Username : </label>
		<form:input path="name" /><br>
		<label>Email : </label>
		<form:input path="email" /><br>
		<label>Password : </label>
		<form:password path="password" /><br>
		<input type="submit" value="create" />
	</form:form>
	
</body>
</html>