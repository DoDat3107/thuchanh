<%--
  Created by IntelliJ IDEA.
  User: dat
  Date: 10/19/2024
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Chỉnh Sửa Thanh Toán</h2>
<form action="${pageContext.request.contextPath}/payments?action=update" method="post">
  <input type="hidden" name="id" value="${payment.id}"/>
  <div>
    <label for="name">Tên Thanh Toán:</label>
    <input type="text" id="name" name="name" value="${payment.name}" required>
  </div>
  <div>
    <button type="submit">Cập Nhật</button>
    <a href="${pageContext.request.contextPath}/payments?action=list">Hủy</a>
  </div>
</form>
<c:if test="${not empty errorMessage}">
  <div style="color:red;">${errorMessage}</div>
</c:if>
</body>
</html>
