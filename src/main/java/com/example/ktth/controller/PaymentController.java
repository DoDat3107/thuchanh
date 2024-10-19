package com.example.ktth.controller;

import com.example.ktth.model.Payment;
import com.example.ktth.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentController", value = "/payments")
public class PaymentController extends HttpServlet {
    private final PaymentService paymentService = new PaymentService();
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            displayAllPayments(request, response);
        } else {
            switch (action) {
                case "list":
                    displayAllPayments(request, response);
                    break;
                case "edit":
                    showEditPaymentForm(request, response);
                    break;
                case "delete":
                    deletePayment(request, response);
                    break;
                case "create":
                    showCreatePaymentForm(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addPayment(request, response);
                break;
            case "update":
                updatePayment(request, response);
                break;
            default:
                displayAllPayments(request, response);
                break;
        }
    }

    private void displayAllPayments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> payments = paymentService.findAll();
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("/static/rooms/ListPayment.jsp").forward(request, response);
    }

    private void addPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        if (isValidPaymentName(name)) {
            Payment payment = new Payment(name);
            if (paymentService.add(payment)) {
                request.getSession().setAttribute("successMessage", "Thêm loại thanh toán thành công.");
                response.sendRedirect(request.getContextPath() + "/payments?action=list");
            } else {
                handleError(request, response, "Thêm loại thanh toán thất bại.");
            }
        } else {
            handleError(request, response, "Tên loại thanh toán không thể để trống.");
        }
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            if (isValidPaymentName(name)) {
                Payment payment = new Payment(name);
                paymentService.edit(payment, id);
                response.sendRedirect(request.getContextPath() + "/payments?action=list");
            } else {
                handleError(request, response, "Tên loại thanh toán không thể để trống.");
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid payment ID format.", e);
            handleError(request, response, "ID loại thanh toán không hợp lệ.");
        }
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            paymentService.delete(id);
            response.sendRedirect(request.getContextPath() + "/payments?action=list");
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid payment ID format.", e);
            response.sendRedirect(request.getContextPath() + "/payments?action=list");
        }
    }

    private void showEditPaymentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Payment payment = paymentService.findById(id);
            if (payment != null) {
                request.setAttribute("payment", payment);
                request.getRequestDispatcher("/static/rooms/editPayment.jsp").forward(request, response);
            } else {
                handleError(request, response, "Không tìm thấy loại thanh toán với ID: " + id);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid payment ID format.", e);
            handleError(request, response, "ID loại thanh toán không hợp lệ.");
        }
    }

    private void showCreatePaymentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/rooms/createPayment.jsp").forward(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }

    private boolean isValidPaymentName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
