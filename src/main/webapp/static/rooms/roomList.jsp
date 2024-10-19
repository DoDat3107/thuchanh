<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách phòng trọ</title>
</head>
<body>
<div class="shop">
    <table border="1">
        <tr>
            <form action="searchRoom" method="GET">
                <input type="text" name="searchTerm" placeholder="Search by Room ID or Tenant Name or Phone Number" />
                <button type="submit">Search</button>
            </form>
        </tr>
        <tr>
            <th colspan="8"><h1>Danh sách phòng trọ</h1></th>
        </tr>
        <tr>
            <th>STT</th>
            <th>Mã phòng trọ</th>
            <th>Tên người thuê trọ</th>
            <th>Số điện thoại</th>
            <th>Hình thức thanh toán</th>
            <th>Ghi chú</th>
            <th>Action</th>
            <th>
                <button class="login-btn"><a href="/rooms?action=create">Thêm mới</a></button>
            </th>
        </tr>
        <c:if test="${empty roomList}">
            <tr>
                <td colspan="8">Không tìm thấy phòng nào.</td>
            </tr>
        </c:if>
        <c:forEach var="room" items="${roomList}">
            <tr>
                <td><c:out value="${room.id}"/></td>
                <td><c:out value="${room.name}"/></td>
                <td><c:out value="${room.phone}"/></td>
                <td><c:out value="${room.paymentType}"/></td>
                <td><c:out value="${room.note}"/></td>
                <td><img src="<c:out value='${room.image}'/>" alt="" style="width: 100px;"></td>
                <td><button class="login-btn"><a href="<c:url value='/rooms?action=edit&id=${room.id}'/>">Edit</a></button></td>
                <td><button class="login-btn"> <a  href="<c:url value='/rooms?action=delete&id=${room.id}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">Xóa</a></button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
