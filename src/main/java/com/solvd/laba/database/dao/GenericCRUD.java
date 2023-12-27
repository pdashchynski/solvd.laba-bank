package com.solvd.laba.database.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericCRUD<T> {

    T get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void insert(T t) throws SQLException;

    void update(T t, String[] params) throws SQLException;

    void delete(T t) throws SQLException;
}