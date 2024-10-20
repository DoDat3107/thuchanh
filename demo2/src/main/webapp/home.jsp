<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/11/2024
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Thông tin phòng</title>
</head>
<body>
<div class="container">
    <h3>THÔNG TIN PHÒNG</h3>
    <div class="main-info">
        <div class="search">
            <div class="add-button">
                <a href="http://localhost:8080/room?action=add">
                    <p>Thêm mới</p>
                </a>
            </div>

            <div class="search-input">
                <form action="http://localhost:8080/room?action=room" method="get">
                    <div class="input">
                        <input type="text" id="customerName" name="customerName" placeholder="Tìm kiếm" value="${param.customerName}" onchange="this.form.submit()">
                        <button type="submit">Tìm kiếm</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="info-product">
            <table border="1">
                <tr>
                    <th>STT</th>
                    <th>Mã phòng</th>
                    <th>Tên khách hàng</th>
                    <th>Số điện thoại</th>
                    <th>Thời gian đặt phòng</th>
                    <th>Kiểu thanh toán</th>
                    <th>Ghi chú</th>
                    <th>Hành động</th>
                </tr>
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">
                        <p>${errorMessage}</p>
                    </div>
                </c:if>
                <c:forEach var="item" items="${roomList}" varStatus="loop">
                    <tr>
                        <td class="small">${loop.index + 1}</td>
                        <td class="medium">${item.id}</td>
                        <td class="medium">${item.customerName}</td>
                        <td class="medium">${item.phone}</td>
                        <td class="medium">${item.time}</td>
                        <td class="medium">${item.paymentName}</td>
                        <td class="medium">${item.note}</td>
                        <td class="medium">
                            <a href="http://localhost:8080/room?action=edit&id=${item.id}"><button >sửa</button></a>
                            <a href="http://localhost:8080/room?action=delete&id=${item.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?')"><button >xóa</button></a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>
</body>
</html>