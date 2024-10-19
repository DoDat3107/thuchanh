<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tạo Thanh Toán Mới</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>"> <!-- Thêm đường dẫn đến CSS của bạn -->
</head>
<body>
<h2>Tạo Thanh Toán Mới</h2>
<form action="${pageContext.request.contextPath}/payments?action=add" method="post">
    <div>
        <label for="name">Tên Thanh Toán:</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div>
        <button type="submit">Tạo</button>
        <a href="${pageContext.request.contextPath}/payments?action=list">Hủy</a>
    </div>
</form>
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>
</body>
</html>
