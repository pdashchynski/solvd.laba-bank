package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Departments;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentsMapper {

    @Select("SELECT * FROM departments WHERE id = #{}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "address", column = "addresses_id"),
            @Result(property = "openTime", column = "open_time"),
            @Result(property = "closeTime", column = "close_time")
    })
    Departments get(int id);

    @Select("SELECT * FROM departments")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "address", column = "addresses_id"),
            @Result(property = "openTime", column = "open_time"),
            @Result(property = "closeTime", column = "close_time")
    })
    List<Departments> getAll();

    @Insert("INSERT INTO departments (id, addresses_id, open_time, close_time) " +
            "VALUES (#{id}, #{address.id}, #{openTime}, #{closeTime}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE departments SET addresses_id = new.addresses_id, " +
            "open_time = new.open_time, close_time = new.close_time")
    void save(Departments department);

    @Insert("INSERT INTO departments (id, addresses_id, open_time, close_time) " +
            "VALUES (#{id}, #{address.id}, #{openTime}, #{closeTime})")
    void insert(Departments department);

    @Update("UPDATE departments SET " +
            "addresses_id = #{address.id}, " +
            "open_time = #{openTime} " +
            "close_time = #{closeTime} " +
            "WHERE id = #{id}")
    void update(Departments department);

    @Delete("DELETE FROM departments WHERE id = #{id}")
    void delete(int id);
}
