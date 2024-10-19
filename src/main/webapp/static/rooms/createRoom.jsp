<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Phòng</title>
    <link rel="stylesheet" href="<c:url value='/static/css/style.css' />"> <!-- Kết nối CSS nếu cần -->
</head>
<body>
<h1>Thêm Phòng Mới</h1>

<!-- Khu vực hiển thị thông báo -->
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<c:if test="${not empty successMessage}">
    <div style="color: green;">${successMessage}</div>
</c:if>

<form action="${pageContext.request.contextPath}/rooms?action=add" method="post">
    <fieldset>
        <legend>Thông tin phòng</legend>
        <input type="text" name="maRoom" placeholder="Mã phòng" required>
        <input type="text" name="name" placeholder="Tên phòng" required>
        <input type="text" name="phone" placeholder="Số điện thoại" required>
        <input type="date" name="startDate" placeholder="Ngày bắt đầu" required>
        <select name="paymentType" required>
            <option value="">Chọn hình thức thanh toán</option>
            <option value="theo tháng">Theo tháng</option>
            <option value="theo quý">Theo quý</option>
            <option value="theo năm">Theo năm</option>
        </select>
        <input type="text" name="note" placeholder="Ghi chú" required>
        <input type="hidden" name="id" value="1">
        <button type="submit">Thêm</button>
    </fieldset>
</form>

<a href="${pageContext.request.contextPath}/rooms?action=home" class="button">Trở về Danh Sách Phòng</a>
</body>
</html>
