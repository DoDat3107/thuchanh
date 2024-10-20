package controller;

import model.Room;
import service.IService;
import service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoomController", value = "/room")
public class RoomController extends HttpServlet {
    private final IService<Room> roomIService = new RoomService();
    private final RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "room":
                showRoom(req, resp);
                break;
            case "add":
                showFormAdd(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "delete":
                deleteRoom(req, resp);
                break;
            default:
                showRoom(req, resp);
                break;
        }
    }

    public void showRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Room> roomList;
        String customerName = req.getParameter("customerName");
        if (customerName!=null && !customerName.trim().isEmpty()){
            roomList = roomService.showRoomByName(customerName);
        } else {
            roomList = roomIService.getAll();
        }
        if (roomList.isEmpty()) {
            req.setAttribute("errorMessage", "Không có phòng nào.");
            req.setAttribute("roomList", roomList);
        } else {
            req.setAttribute("roomList", roomList);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req,resp);
    }

    public void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/add.jsp");
        dispatcher.forward(req, resp);
    }

    public void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Room room = roomIService.findById(idEdit);
        req.setAttribute("room",room);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(req,resp);
        resp.sendRedirect("/room?action=room");
    }

    public void deleteRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        roomIService.delete(idDelete);
        resp.sendRedirect("/room?action=room");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "add":
                addRoom(req, resp);
                break;
            case "edit":
                editRoom(req, resp);
                break;
        }
    }

    public void addRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String phone = req.getParameter("phone");
        int idPayment = Integer.parseInt(req.getParameter("idPayment"));
        String note = req.getParameter("note");

        if (roomService.checkExistPhone(phone)) {
            req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng!");
            showFormAdd(req, resp);
            return;
        }
        if (roomService.checkValidatePhone(phone)) {
            req.setAttribute("errorMessage", "Số địện thoại nhập không đúng định dạng!");
            showFormAdd(req, resp);
            return;
        }
        Room room = new Room(customerName,phone,idPayment,note);
        roomIService.add(room);
        resp.sendRedirect("/room?action=room");
    }

    public void editRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String customerName = req.getParameter("customerName");
        String phone = req.getParameter("phone");
        int idPayment = Integer.parseInt(req.getParameter("idPayment"));
        String note = req.getParameter("note");
        Room existRoom = roomIService.findById(id);
        if (!existRoom.getPhone().equals(phone)){
            if (roomService.checkExistPhone(phone)) {
                req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng!");
                showFormAdd(req, resp);
                return;
            }
            if (roomService.checkValidatePhone(phone)) {
                req.setAttribute("errorMessage", "Số địện thoại nhập không đúng định dạng!");
                showFormAdd(req, resp);
                return;
            }
        }
        Room room = new Room(id,customerName,phone,idPayment,note);
        roomIService.update(id,room);
        resp.sendRedirect("/room?action=room");
    }
}