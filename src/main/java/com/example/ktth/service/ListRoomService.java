package com.example.ktth.service;

import com.example.ktth.model.ListRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListRoomService implements IService<ListRoom> {

    private Connection connection = ConnectToMySql.getConnection();

    @Override
    public boolean add(ListRoom listRoom) {
        String sql = "INSERT INTO ListRoom (name, phone, startDate, paymentType, note) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, listRoom.getName());
            pstmt.setString(2, listRoom.getPhone());
            pstmt.setDate(3, Date.valueOf(listRoom.getStartDate()));
            pstmt.setString(4, listRoom.getPaymentType());
            pstmt.setString(5, listRoom.getNote());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(ListRoom listRoom, int id) {
        String sql = "UPDATE ListRoom SET name = ?, phone = ?, startDate = ?, paymentType = ?, note = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, listRoom.getName());
            pstmt.setString(2, listRoom.getPhone());
            pstmt.setDate(3, Date.valueOf(listRoom.getStartDate()));
            pstmt.setString(4, listRoom.getPaymentType());
            pstmt.setString(5, listRoom.getNote());
            pstmt.setInt(6, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ListRoom WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ListRoom findById(int id) {
        String sql = "SELECT * FROM ListRoom WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ListRoom(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("MaRoom"),
                        rs.getString("phone"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getString("note"),
                        rs.getString("paymentType"),
                        rs.getInt("idPayment")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ListRoom> findAll() {
        List<ListRoom> listRooms = new ArrayList<>();
        String sql = "SELECT listroom.*, c.name AS namePaymen\n" +
                "FROM listroom\n" +
                "         JOIN listpaymen c ON c.id = listroom.idPayment;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                ListRoom room = new ListRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("MaRoom"),
                        resultSet.getString("phone"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getString("note"),
                        resultSet.getString("paymentType"),
                        resultSet.getInt("idPayment")
                );
                listRooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listRooms;
    }

    @Override
    public List<ListRoom> search(String rentalCode, String tenantName, String phoneNumber) {
        List<ListRoom> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ListRoom WHERE 1=1");

        if (rentalCode != null && !rentalCode.isEmpty()) {
            sql.append(" AND id = ?");
        }
        if (tenantName != null && !tenantName.isEmpty()) {
            sql.append(" AND name LIKE ?");
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            sql.append(" AND phone = ?");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            if (rentalCode != null && !rentalCode.isEmpty()) {
                pstmt.setInt(paramIndex++, Integer.parseInt(rentalCode));
            }
            if (tenantName != null && !tenantName.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + tenantName + "%");
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                pstmt.setString(paramIndex++, phoneNumber);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ListRoom room = new ListRoom(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("MaRoom"),
                        rs.getString("phone"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getString("note"),
                        rs.getString("paymentType"),
                        rs.getInt("idPayment")
                );
                result.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
