package com.example.ktth.service;

import java.util.List;

public interface IService<E> {
    boolean add(E e);
    boolean edit(E e, int id);
    boolean delete(int id);
    E findById(int id); // Chỉnh sửa
    List<E> findAll();
    List<E> search(String rentalCode, String tenantName, String phoneNumber);
}
