package com.example.ktth.controller;

import com.example.ktth.model.ListRoom;
import com.example.ktth.model.Payment;
import com.example.ktth.service.ListRoomService;
import com.example.ktth.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ListRoomController", value = "/rooms")
public class ListRoomController extends HttpServlet {
    private final ListRoomService listRoomService = new ListRoomService();
    private final PaymentService paymentService = new PaymentService();
    private static final Logger LOGGER = Logger.getLogger(ListRoomController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            displayAllRooms(request, response);
        } else {
            switch (action) {
                case "search":
                    searchRoom(request, response);
                    break;
                case "edit":
                    showEditRoomForm(request, response);
                    break;
                case "delete":
                    deleteRoom(request, response);
                    break;
                case "create":
                    showCreateRoomForm(request, response);
                    break;
                default:
                    displayAllRooms(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addRoom(request, response);
                break;
            case "edit":
                editRoom(request, response);
                break;
            default:
                displayAllRooms(request, response);
                break;
        }
    }

    private void displayAllRooms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ListRoom> rooms = listRoomService.findAll();
        request.setAttribute("rooms", rooms);
        List<Payment> paymentsList = paymentService.findAll();
        request.setAttribute("paymentsList", paymentsList);

        request.getRequestDispatcher("/static/rooms/roomList.jsp").forward(request, response);
    }

    private void searchRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rentalCode = request.getParameter("rentalCode");
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        List<ListRoom> results = listRoomService.search(rentalCode, tenantName, phoneNumber);
        request.setAttribute("rooms", results);
        request.getRequestDispatcher("/static/rooms/roomList.jsp").forward(request, response);
    }

    private void addRoom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maRoom = request.getParameter("maRoom");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        String paymentType = request.getParameter("paymentType");
        String note = request.getParameter("note");
        int idPayment = Integer.parseInt(request.getParameter("id"));

        ListRoom room = new ListRoom(name, maRoom, phone, startDate,  paymentType,note, idPayment);
        listRoomService.add(room);
        response.sendRedirect(request.getContextPath() + "/rooms?action=home");
    }

    private void editRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ListRoom existingRoom = listRoomService.findById(id);
            if (existingRoom != null) {
                existingRoom.setName(request.getParameter("name"));
                existingRoom.setPhone(request.getParameter("phone"));
                existingRoom.setStartDate(LocalDate.parse(request.getParameter("startDate")));
                existingRoom.setPaymentType(request.getParameter("paymentType"));
                existingRoom.setNote(request.getParameter("note"));
                listRoomService.edit(existingRoom, id);
            }
            response.sendRedirect(request.getContextPath() + "/rooms?action=home");
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            LOGGER.log(Level.SEVERE, "ID hoặc định dạng ngày không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID hoặc định dạng ngày không hợp lệ.");
            displayAllRooms(request, response);
        }
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            listRoomService.delete(id);
            response.sendRedirect(request.getContextPath() + "/rooms?action=home");
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID phòng không hợp lệ.", e);
            response.sendRedirect(request.getContextPath() + "/rooms?action=home");
        }
    }

    private void showEditRoomForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ListRoom room = listRoomService.findById(id);
            if (room == null) {
                request.setAttribute("errorMessage", "Không tìm thấy phòng với ID: " + id);
                displayAllRooms(request, response);
                return;
            }
            request.setAttribute("room", room);
            request.getRequestDispatcher("/static/rooms/editRoom.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "ID phòng không hợp lệ.", e);
            request.setAttribute("errorMessage", "ID phòng không hợp lệ.");
            displayAllRooms(request, response);
        }
    }

    private void showCreateRoomForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/rooms/createRoom.jsp").forward(request, response);
    }
}
