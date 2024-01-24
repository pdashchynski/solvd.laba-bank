package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Addresses;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AddressesMapper {

    @Select("SELECT * FROM addresses WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "country", column = "country"),
            @Result(property = "city", column = "city"),
            @Result(property = "postalCode", column = "postal_code"),
    })
    Addresses get(int id);

    @Select("SELECT * FROM addresses")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "country", column = "country"),
            @Result(property = "city", column = "city"),
            @Result(property = "postalCode", column = "postal_code"),
    })
    List<Addresses> getAll();

    @Insert("INSERT INTO addresses (id, country, city, postal_code) " +
            "VALUES (#{id}, #{country}, #{city}, #{postalCode}) AS new " +
            "ON DUPLICATE KEY " +
            "UPDATE country = new.country, city = new.city, postal_code = new.postal_code")
    void save(Addresses address);

    @Insert("INSERT INTO addresses (id, country, city, postal_code) " +
            "VALUES (#{id}, #{country}, #{city}, #{postalCode})")
    void insert(Addresses address);

    @Update("UPDATE addresses SET country = #{country}, city = #{city}, postal_code = #{postalCode} " +
            "WHERE id = #{id}")
    void update(Addresses address);

    @Delete("DELETE FROM addresses WHERE id = #{id}")
    void delete(int id);
}
