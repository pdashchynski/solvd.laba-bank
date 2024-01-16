package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Services;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ServicesMapper {

    @Select("SELECT * FROM services WHERE id = #{}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price")
    })
    Services get(int id);

    @Select("SELECT * FROM services")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price")
    })
    List<Services> getAll();

    @Insert("INSERT INTO services (id, name, price) " +
            "VALUES (#{id}, #{name}, #{price}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE services SET name = new.name, price = new.price")
    void save(Services service);

    @Insert("INSERT INTO services (id, name, price) " +
            "VALUES (#{id}, #{name}, #{price}")
    void insert(Services service);

    @Update("UPDATE services SET " +
            "name = #{name}, " +
            "price = #{price}, " +
            "WHERE id = #{id}")
    void update(Services service);

    @Delete("DELETE FROM services WHERE id = #{id}")
    void delete(int id);
}
