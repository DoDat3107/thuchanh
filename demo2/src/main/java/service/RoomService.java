package service;

import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RoomService implements IService<Room>{
    Connection connection = ConnectToMySQL.getConnection();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public RoomService(){

    }
    @Override
    public void add(Room room) {
        String sql = "insert into room (customerName, phone, idPayment, note) values (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,room.getCustomerName());
            preparedStatement.setString(2,room.getPhone());
            preparedStatement.setInt(3,room.getIdPayment());
            preparedStatement.setString(4, room.getNote());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Room room) {
        String sql = "update room set customerName = ?, phone = ?, idPayment = ?, note = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,room.getCustomerName());
            preparedStatement.setString(2,room.getPhone());
            preparedStatement.setInt(3,room.getIdPayment());
            preparedStatement.setString(4, room.getNote());
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        String sql = "delete from room where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Room findById(int id) {
        Room room = null;
        String sql = "select * from room where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("id");
                String customerName = resultSet.getString("customerName");
                String phone = resultSet.getString("phone");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formatTime = time.format(formatter);
                int idPayment = resultSet.getInt("idPayment");
                String note = resultSet.getString("note");
                room = new Room(id,customerName,phone,formatTime,idPayment,note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        String sql = "select a.*, b.name as paymentName from room a join paymentMethod b on a.idPayment = b.id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String customerName = resultSet.getString("customerName");
                String phone = resultSet.getString("phone");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formatTime = time.format(formatter);
                int idPayment = resultSet.getInt("idPayment");
                String note = resultSet.getString("note");
                String paymentName = resultSet.getString("paymentName");
                Room room = new Room(id,customerName,phone,formatTime,idPayment,note,paymentName);
                roomList.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public boolean checkValidatePhone (String phone){
        String phoneRegex = "^(0[3|5|7|8|9])+([0-9]{8})$";
        return !phone.matches(phoneRegex);
    }

    public boolean checkExistPhone(String phone){
        String sql = "select * from room where phone = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Room> showRoomByName(String customerName) {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE customerName LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + customerName + "%"); // Tìm kiếm với wildcard
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String phone = resultSet.getString("phone");
                int idPayment = resultSet.getInt("idPayment");
                String note = resultSet.getString("note");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formatTime = time.format(formatter);
                Room room = new Room(id, customerName, phone, formatTime, idPayment, note); // Thêm time vào đây
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return roomList;
    }

}
