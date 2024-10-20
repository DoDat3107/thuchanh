<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chỉnh Sửa Thông Tin Phòng</title>
</head>
<body>
<h1>Chỉnh Sửa Thông Tin Phòng</h1>

<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>
<c:if test="${not empty message}">
    <div style="color: green;">${message}</div>
</c:if>

<form action="/room?action=edit" method="post">
    <fieldset>
        <legend>Thông tin phòng</legend>
        <input type="hidden" name="id" value="${room.id}">
        <input type="text" name="name" placeholder="Tên người thuê" value="${room.customerName}" required>
        <input type="text" name="phone" placeholder="Số điện thoại" value="${room.phone}" required>
        <input type="date" name="startDate" value="${room.time}" required>
        <select name="idPayment" required>
            <option value="">Chọn hình thức thanh toán</option>
            <option value="1" <c:if test="${room.idPayment == 1}"></c:if>>Theo tháng</option>
            <option value="2" <c:if test="${room.idPayment == 2}"></c:if>>Theo quý</option>
            <option value="3" <c:if test="${room.idPayment == 3}"></c:if>>Theo năm</option>
        </select>
        <input type="text" name="note" placeholder="Ghi chú" value="${room.note}" required>

        <button type="submit"   >Cập nhật</button>
    </fieldset>
</form>

<a href="/room?action=home" class="button">Trở về Danh Sách Phòng</a>
</body>
</html>
