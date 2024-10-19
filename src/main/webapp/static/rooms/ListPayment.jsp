<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách loại thanh toán</title>
</head>
<body>
<h1>Danh sách loại thanh toán</h1>
<a href="payments?action=create">Thêm loại thanh toán mới</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên loại thanh toán</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="payment" items="${payments}">
        <tr>
            <td>${payment.id}</td>
            <td>${payment.name}</td>
            <td>
                <a href="payments?action=edit&id=${payment.id}">Sửa</a>
                <a href="payments?action=delete&id=${payment.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
