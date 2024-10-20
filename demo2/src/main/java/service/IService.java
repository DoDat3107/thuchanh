package service;

import java.util.List;

public interface IService<E> {
    void add(E e);
    void update(int id, E e);
    void delete(int id);
    int findIndexById(int id);
    E findById (int id);
    List<E> getAll();
}