package com.example.ktth.model;

public class Payment {
    private int id;
    private String name;

    public Payment(String name) {
        this.name = name;
    }

    public Payment(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter và setter
    public int getId() {
        return id;
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
}
