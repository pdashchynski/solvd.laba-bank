package com.solvd.laba.services;

import java.util.List;

public interface GenericCRUDService<T> {
    T get(int id);

    List<T> getAll();

    void save(T t);

    void insert(T t);

    void update(T t);

    void delete(int id);
}
