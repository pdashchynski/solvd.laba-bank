package com.solvd.laba.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericCRUD<T> {

    T get(int id);

    List<T> getAll();

    void save(T t);

    void insert(T t);

    void update(T t);

    void delete(int id);
}