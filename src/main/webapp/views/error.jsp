<%--
  Created by IntelliJ IDEA.
  User: dat
  Date: 10/19/2024
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1>Đã có lỗi xảy ra</h1>
    <p>
        <strong>Thông báo:</strong> ${errorMessage}
    </p>
    <p>
        <a href="<c:url value='/payments?action=list'/>">Quay lại trang thanh toán</a> |
        <a href="<c:url value='/'/>">Về trang chính</a>
    </p>
</div>
</body>
</html>
