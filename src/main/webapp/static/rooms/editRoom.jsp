<%--
  Created by IntelliJ IDEA.
  User: dat
  Date: 10/19/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<body>
<h1>Chỉnh Sửa Thông Tin Phòng</h1>

<!-- Khu vực hiển thị thông báo -->
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<c:if test="${not empty successMessage}">
    <div style="color: green;">${successMessage}</div>
</c:if>

<form action="${pageContext.request.contextPath}/rooms?action=edit" method="post">
    <fieldset>
        <legend>Thông tin phòng</legend>
        <input type="hidden" name="id" value="${room.id}">
        <input type="text" name="maRoom" placeholder="Mã phòng" value="${room.maRoom}" required>
        <input type="text" name="name" placeholder="Tên phòng" value="${room.name}" required>
        <input type="text" name="phone" placeholder="Số điện thoại" value="${room.phone}" required>
        <input type="date" name="startDate" placeholder="Ngày bắt đầu" value="${room.startDate}" required>
        <select name="paymentType" required>
            <option value="">Chọn hình thức thanh toán</option>
            <option value="theo tháng" <c:if test="${room.paymentType == 'theo tháng'}">selected</c:if>>Theo tháng</option>
            <option value="theo quý" <c:if test="${room.paymentType == 'theo quý'}">selected</c:if>>Theo quý</option>
            <option value="theo năm" <c:if test="${room.paymentType == 'theo năm'}">selected</c:if>>Theo năm</option>
        </select>
        <input type="text" name="note" placeholder="Ghi chú" value="${room.note}" required>
        <button type="submit">Cập Nhật</button>
    </fieldset>
</form>

<a href="${pageContext.request.contextPath}/rooms?action=home" class="button">Trở về Danh Sách Phòng</a>
</body>
</html>
