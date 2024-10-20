<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm phòng</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Liên kết đến file CSS nếu cần -->
</head>
<body>
<h1>Thêm phòng</h1>
<form action="room?action=add" method="post">
    <label for="customerName">Tên khách hàng:</label>
    <input type="text" id="customerName" name="customerName" required><br>

    <label for="phone">Số điện thoại:</label>
    <input type="text" id="phone" name="phone" required><br>

    <label for="idPayment">Phương thức thanh toán:</label>
    <select id="idPayment" name="idPayment">
        <option value="1">Thanh toán hàng tháng</option>
        <option value="2">Thanh toán hàng quý</option>
        <option value="3">Thanh toán hàng năm</option>
    </select><br>

    <label for="note">Ghi chú:</label>
    <textarea id="note" name="note"></textarea><br>

    <input type="submit" value="Thêm phòng">
</form>

<nav>
    <ul>
        <li><a href="room?action=room">Quay lại danh sách phòng</a></li>
    </ul>
</nav>
</body>
</html>
