package com.example.ktth.model;

import java.time.LocalDate;

public class ListRoom {
    private int id;
    private String maRoom;
    private String name;
    private String phone;
    private LocalDate startDate; // Đổi sang LocalDate
    private String note;
    private String paymentType;
    private static int nextId =1;
    private int idPayment;

    public ListRoom(String name, String maRoom, String phone, LocalDate startDate, String note, String paymentType, int idPayment) {
        this.id = nextId++;
        this.name = name;
        this.maRoom = maRoom;
        this.phone = phone;
        this.startDate = startDate;
        this.note = note;
        this.paymentType = paymentType;
        this.idPayment = idPayment;
    }

    public ListRoom(int id, String name, String maRoom, String phone, LocalDate startDate, String note, String paymentType, int idPayment) {
        this.id = id;
        this.name = name;
        this.maRoom = maRoom;
        this.phone = phone;
        this.startDate = startDate;
        this.note = note;
        this.paymentType = paymentType;
        this.idPayment = idPayment;
    }

    public ListRoom() {
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        ListRoom.nextId = nextId;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public String getMaRoom() {
        return maRoom;
    }

    public void setMaRoom(String maRoom) {
        this.maRoom = maRoom; // Sửa đổi ở đây
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getStartDate() { // Thay đổi kiểu trả về
        return startDate;
    }

    public void setStartDate(LocalDate startDate) { // Thay đổi kiểu tham số
        this.startDate = startDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
