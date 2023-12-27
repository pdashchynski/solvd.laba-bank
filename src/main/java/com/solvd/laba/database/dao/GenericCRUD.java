package com.solvd.laba.database.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericCRUD<T> {

    T get(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t);

    void insert(T t);

    void update(T t, String[] params);

    void delete(T t);
}