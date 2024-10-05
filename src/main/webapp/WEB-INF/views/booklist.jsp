<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
  <div class="table-responsive">

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
                <a href="editbook/${book.id}">Edit</a>
                <a href="deletebook/${book.id}">Delete</a>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>